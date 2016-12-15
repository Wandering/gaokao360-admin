/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoCourseController.java 2015-12-04 13:22:05 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.common.ImportExcelUtil;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.dao.IDataDictDAO;
import cn.thinkjoy.gaokao360.dao.ex.UnivercityDAO;
import cn.thinkjoy.gaokao360.domain.DataDict;
import cn.thinkjoy.gaokao360.service.common.ex.IAuditoriumService;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoClassifyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.SchedulerConfigException;
import org.quartz.impl.DefaultThreadExecutor;
import org.quartz.simpl.SimpleThreadPool;
import org.quartz.spi.ThreadExecutor;
import org.quartz.spi.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/gaokao360/ex/import")
public class UniversityImportController{
    @Autowired
    private UnivercityDAO univercityDAO;

    private static int MAX_BATCH_COUNT = 20000;
    private static final Logger LOG = LoggerFactory.getLogger(UniversityImportController.class);
    private static ThreadPool POOL=new SimpleThreadPool(3,Thread.NORM_PRIORITY);
    @PostConstruct
    void initThread(){
        try {
            POOL.initialize();
        } catch (SchedulerConfigException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入院校专业录取信息用时
     * @return
     */
    @RequestMapping(value="/importMajorData")
    @ResponseBody
    public void importMajorData(@RequestParam("file") final MultipartFile file,HttpServletRequest request){

            try {
                final String path=filetransferTo(file,request);
                Runnable task=new Runnable() {
                    @Override
                    public void run() {
                        try {
                            createTaskFile(getFileName(path));
                            long start = System.currentTimeMillis();
                            insertMajorData(path);
                            univercityDAO.updateUniversityIdToImportMajor();
                            univercityDAO.updateMajorIdToImportMajor();

                            List<String> ll = univercityDAO.getUniversityNameIsNullMajor();
                            if (ll.isEmpty()) {
                                univercityDAO.insertImportToMajorFormal();
                                String time = (System.currentTimeMillis() - start) / 60 * 1000 + "";
                                updateSuccessTaskFile(time, getFileName(path));
                                LOG.info("导入院校专业录取信息用时：" + time);
                            }else {
                                LOG.info("执行失败");
                                updateFailTaskFile(ll,getFileName(path));
                            }
                        } catch (IOException e) {
                            errorTaskFile(e,getFileName(path));
                            e.printStackTrace();
                        }finally {
                            File file1=new File(path);
                            if(file1.exists()){
                                file1.delete();
                            }
                            file1=null;
                        }
                    }
                };
                POOL.runInThread(task);
            } catch (Exception e) {
                throw  new BizException("","读取数据异常");
            }
        }



    /**
     * 导入院校专业录取计划用时
     * @return
     */
    @RequestMapping(value="/importMajorPlanData")
    @ResponseBody
    public void importMajorPlanData(@RequestParam("file") MultipartFile file,HttpServletRequest request){
            try {
                final String path=filetransferTo(file,request);

                Runnable task=new Runnable() {
                    @Override
                    public void run() {
                        try {
                            createTaskFile(getFileName(path));
                            long start = System.currentTimeMillis();
                            insertMajorPlanData(path);
                            univercityDAO.updateUniversityIdToImport();
                            univercityDAO.updateMajorIdToImport();
                            univercityDAO.updateBatch1();
                            univercityDAO.updateBatch2();
                            univercityDAO.updateBatch3();
                            univercityDAO.updateBatch4();
                            univercityDAO.updateBatch5();
                            univercityDAO.updateBatch6();
                            univercityDAO.updateBatch7();
                            univercityDAO.updateBatch8();
                            univercityDAO.updateBatch9();
                            univercityDAO.updateBatch10();
                            univercityDAO.updateBatch11();
                            univercityDAO.updateBatch12();
                            univercityDAO.updateBatch13();
                            univercityDAO.updateBatch14();
                            univercityDAO.updateBatch15();
                            List<String> ll = univercityDAO.getUniversityNameIsNull();
                            if (ll.isEmpty()) {
//                                univercityDAO.truncateUniq();
                                univercityDAO.insertImportToUniq();
                                univercityDAO.insertUniversityPlanToFormal();
                                univercityDAO.insertMajorPlanToFormal();
                                String time = ((System.currentTimeMillis() - start) / 60 * 1000) + "";
                                LOG.info("导入院校专业录取信息用时：" + time);
                                updateSuccessTaskFile(time,getFileName(path));
                            } else {
                                LOG.info("执行失败");
                                updateFailTaskFile(ll,getFileName(path));
                            }
                        } catch (Exception e) {
                            errorTaskFile(e,getFileName(path));
                            e.printStackTrace();
                        } finally {
                            File file1 = new File(path);
                            if (file1.exists()) {
                                file1.delete();
                            }
                            file1 = null;
                        }
                    }
                };
                POOL.runInThread(task);
            } catch (Exception e) {
                throw  new BizException("","读取数据异常");
            }
    }

    /**
     * 导入院校专业录取计划用时
     * @return
     */
    @RequestMapping(value="/delMajorPlanData")
    @ResponseBody
    public boolean delMajorPlanData(String[] areaIds,String[] years,String[] batchs,String[] majorTypes){
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("areaIdList", areaIds);
            map.put("yearList", years);
            map.put("batchList", batchs);
            map.put("majorTypeList", majorTypes);

            univercityDAO.delMajorEnrollingPlan(map);
            univercityDAO.delByBatchYearAreaId(map);
            univercityDAO.delUnivsersityByBatchYearAreaId(map);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("error","删除失败！");
        }
        return true;
    }

    //    SELECT batch FROM zgk_university_major_enrolling_plan WHERE batch IS NOT NULL GROUP BY batch
    /**
     * 导入院校专业录取计划用时
     * @return
     */
    @RequestMapping(value="/getMajorPlanBatch")
    @ResponseBody
    public List<Map<String,Object>> getMajorPlanBatch(){
        return univercityDAO.getDelBatch();
    }
    /**
     * 导入院校录取信息用时
     * @return
     */
    @RequestMapping(value="/importUniversityData")
    @ResponseBody
    public void importUniversityData(@RequestParam("file") MultipartFile file,HttpServletRequest request){
            try {
                final String path=filetransferTo(file,request);
                Runnable task=new Runnable() {
                    @Override
                    public void run() {
                        try {
                            long start = System.currentTimeMillis();
                            createTaskFile(getFileName(path));
                            insertUniversityData(path);
                            univercityDAO.updateUniversityIdToImportUniversity();
                            List<String> ll =  univercityDAO.getUniversityNameIsNullUniversity();
                            if (ll.isEmpty()) {
                                univercityDAO.insertImportToUniversityFormal();
                                String time = (System.currentTimeMillis() - start) / 60 * 1000 + "";
                                updateSuccessTaskFile(time, getFileName(path));
                                LOG.info("导入院校专业录取信息用时：" + time);
                            }else {
                                LOG.info("执行失败");
                                updateFailTaskFile(ll,getFileName(path));
                            }
                        } catch (IOException e) {
                            errorTaskFile(e,getFileName(path));
                            e.printStackTrace();
                        }finally {
                            File file1=new File(path);
                            if(file1.exists()){
                                file1.delete();
                            }
                            file1=null;
                        }
                    }
                };
                POOL.runInThread(task);
            } catch (Exception e) {
                throw  new BizException("","读取数据异常");
            }
    }

    /**
     * 插入院校专业数据
     * @throws IOException
     */
    private void insertMajorData(String path) throws IOException {
        File file = new File(path);
        List<Map<String, Object>> list = new ArrayList();
        univercityDAO.cleanMajorData();
            Sheet sheet = null;
            sheet = getSheet(file.getAbsolutePath());
            list.addAll(getMajorDataList(sheet,file.getAbsolutePath()));

        int count = list.size()/MAX_BATCH_COUNT;
        for (int i = 1; i <= count; i++) {
            try {
                univercityDAO.insertMajorEnBatch(list.subList((i-1)*MAX_BATCH_COUNT, i*MAX_BATCH_COUNT));
                System.out.println("插入："+ i *MAX_BATCH_COUNT);
            } catch (Exception e) {
                System.out.println(list.get(i).get("batch"));
                e.printStackTrace();
            }
        }

        if(count * MAX_BATCH_COUNT < list.size())
        {
            univercityDAO.insertMajorEnBatch(list.subList(count * MAX_BATCH_COUNT, list.size()));
            System.out.println("插入："+ list.size());
        }
    }

    /**
     * 插入院校专业招生计划数据
     * @throws IOException
     */
    private void insertMajorPlanData(String path) throws IOException {
        File file = new File(path);
        List<Map<String, Object>> list = new ArrayList();
        univercityDAO.cleanMajorPlanData();
            Sheet sheet = null;
            sheet = getSheet(file.getAbsolutePath());
            list.addAll(getMajorPlanDataList(sheet));

        int count = list.size()/MAX_BATCH_COUNT;
        for (int i = 1; i <= count; i++) {
            try {
                univercityDAO.insertMajorPlanEnBatch(list.subList((i - 1) * MAX_BATCH_COUNT, i * MAX_BATCH_COUNT));
                System.out.println("插入："+ i *MAX_BATCH_COUNT);
            } catch (Exception e) {
                System.out.println(list.get(i).get("batch"));
                e.printStackTrace();
            }
        }

        if(count * MAX_BATCH_COUNT < list.size())
        {
            univercityDAO.insertMajorPlanEnBatch(list.subList(count*MAX_BATCH_COUNT, list.size()));
            System.out.println("插入："+ list.size());
        }
    }
    /**
     * 插入院校招生人数数据
     * @throws IOException
     */
    private void insertUniversityData(String path) throws IOException {
        File file = new File(path);
        List<Map<String, Object>> list = new ArrayList();
        //清空 导入零时表
        univercityDAO.cleanUniversityData();
            Sheet sheet = null;
            sheet = getSheet(file.getAbsolutePath());
            list.addAll(getUniversityDataList(sheet));

        int count = list.size()/MAX_BATCH_COUNT;
        for (int i = 1; i <= count; i++) {
            try {
                univercityDAO.insertUniversityEnBatch(list.subList((i-1)*MAX_BATCH_COUNT, i*MAX_BATCH_COUNT));
                System.out.println("插入："+ i *MAX_BATCH_COUNT);
            } catch (Exception e) {
                System.out.println(list.get(i).get("batch"));
                e.printStackTrace();
            }
        }

        if(count * MAX_BATCH_COUNT < list.size())
        {
            univercityDAO.insertUniversityEnBatch(list.subList(count * MAX_BATCH_COUNT, list.size()));
            System.out.println("插入："+ list.size());
        }
    }
    private static List<File> getFileList(String path) {
        List<File> fileList = new ArrayList();
        File dir = new File(path);
        File[] dirs = dir.listFiles();
        for (int i = 0; i < dirs.length ; i++) {
            File subDir = dirs[i];
            File[] files = subDir.listFiles();
            if(null != files)
            {
                for (int j = 0; j <files.length ; j++) {
                    if(files[j].isFile()&& !files[j].getName().contains(".DS"))
                    {
                        fileList.add(files[j]);
                    }
                }
            }
        }
        return fileList;
    }


    private static Sheet getSheet(String path) throws IOException {
        System.out.println(path);
        InputStream in = new FileInputStream(new File(path));
        Workbook wb = null;
        String type = path.substring(path.lastIndexOf(".")+1);
        if("xlsx".equals(type))
        {
            wb = new XSSFWorkbook(in);
        }else if("xls".equals(type))
        {
            wb = new HSSFWorkbook(in);
        }
        Sheet sheet1 = wb.getSheetAt(0);
        return sheet1;
    }

    /**
     * 院校专业录取信息
     * @param sheet1
     * @return
     */
    private static List<Map<String, Object>> getMajorDataList(Sheet sheet1,String filepath) {
        List<Map<String, Object>> dataList = new ArrayList();
        for (Row row : sheet1) {
            if(row.getRowNum()>0)
            {//universityName	province	year	majorTypeBatch	type	majorName	highScore	avgScore	lowScore	enrollingNumber
                Map<String, Object> dataMap = new HashMap<String, Object>();
                String univisityName = getCellValue(row,0);
                String areaId = getCellValue(row,1);
                String year = getCellValue(row,2);
                String batch = getCellValue(row,3);
                if("本科第三批".equals(batch))
                    System.out.println("........"+filepath);
                String majorType = getCellValue(row,4);
                String majorName = getCellValue(row,5);
                String highScore = getCellValue(row,6);
                String avgScore = getCellValue(row,7);
                String lowScore = getCellValue(row,8);
                String enrollingNumber = getCellValue(row,9);
                if(StringUtils.isNotEmpty(areaId)&&(!univisityName.equals("0")))
                {
                    //System.out.println( areaId + ":" + batch + ":" + majorType + ":" + majorName + ":" + highScore + ":" + avgScore + ":" + lowScore + ":" + enrollingNumber + ":");
                    dataMap.put("univisityName", univisityName);
                    dataMap.put("areaId", areaId);
                    dataMap.put("batch", batch);
                    dataMap.put("majorType", majorType);
                    dataMap.put("year", year);
                    dataMap.put("majorName", majorName);
                    dataMap.put("highScore", highScore);
                    dataMap.put("avgScore", avgScore);
                    dataMap.put("lowScore", lowScore);
                    dataMap.put("enrollingNumber", enrollingNumber);
                    dataList.add(dataMap);
                }
            }
        }
        return dataList;
    }
    /**
     * 院校专业计划录取信息
     * @param sheet1
     * @return
     */
    private static List<Map<String, Object>> getMajorPlanDataList(Sheet sheet1) {
        List<Map<String, Object>> dataList = new ArrayList();
        for (Row row : sheet1) {
            if(row.getRowNum()>0)
            {//universityName	province	year	batch	majorType	majorName	planEnrolling	lengthOfSchooling	schoolFee
                Map<String, Object> dataMap = new HashMap<String, Object>();
                String univisityName = getCellValue(row,0);
                String areaId = getCellValue(row,1);
                String year = getCellValue(row,2);
                String batch = getCellValue(row,3);
                String enrollType = getCellValue(row,4);
                String majorType = getCellValue(row,5);

                String majorName = getCellValue(row,6);
                String planEnrolling = getCellValue(row,7);
                String lengthOfSchooling = getCellValue(row,8);
                String schoolFee = getCellValue(row,9);
                LOG.debug(univisityName + ":" + areaId + ":" + enrollType + ":" + majorType + ":" + year + ":" + majorName + ":" + planEnrolling + ":" + lengthOfSchooling + ":" + schoolFee);
                if(StringUtils.isNotEmpty(areaId)&&(!univisityName.equals("0")))
                {
                    dataMap.put("univisityName", univisityName);
                    dataMap.put("areaId", areaId);
                    dataMap.put("batch", batch);
                    dataMap.put("enrollType", enrollType);
                    dataMap.put("majorType", majorType);
                    dataMap.put("year", year);
                    dataMap.put("majorName", majorName);
                    dataMap.put("planEnrolling", planEnrolling);
                    dataMap.put("lengthOfSchooling", lengthOfSchooling);
                    dataMap.put("schoolFee", schoolFee);
                    dataList.add(dataMap);
                }
            }
        }
        return dataList;
    }
    /**
     * 院校录取人数信息
     * @param sheet1
     * @return
     */
    private static List<Map<String, Object>> getUniversityDataList(Sheet sheet1) {
        List<Map<String, Object>> dataList = new ArrayList();
        for (Row row : sheet1) {
            if(row.getRowNum()>0)
            {// universityName	province	year	batch	majorType	highScore	avgScore	lowScore	enrollingNumber
                Map<String, Object> dataMap = new HashMap<String, Object>();
                String univisityName = getCellValue(row,0);
                String areaId = getCellValue(row,1);
                String year = getCellValue(row,2);
                String batch = getCellValue(row,3);
                String majorType = getCellValue(row,4);
                String highScore = getCellValue(row,5);
                String avgScore = getCellValue(row,6);
                String lowScore = getCellValue(row,7);
                String enrollingNumber = getCellValue(row,8);
                if(highScore.length()>5)
                    System.out.println(univisityName + ":" + areaId + ":" + batch + ":" + majorType + ":" + year + ":" + highScore + ":" + avgScore + ":" + lowScore + ":" + enrollingNumber + ":");

                if(StringUtils.isNotEmpty(areaId)&&(!univisityName.equals("0")))
                {
                    dataMap.put("univisityName", univisityName);
                    dataMap.put("areaId", areaId);
                    dataMap.put("batch", batch);
                    dataMap.put("majorType", majorType);
                    dataMap.put("year", year);
                    dataMap.put("highScore", highScore);
                    dataMap.put("avgScore", avgScore);
                    dataMap.put("lowScore", lowScore);
                    dataMap.put("enrollingNumber", enrollingNumber);
                    dataList.add(dataMap);
                }
            }
        }
        return dataList;
    }

    public static String getCellValue(Row row, int columnNumber) {
        String result = "";
        try {
            result = row.getCell(columnNumber).getStringCellValue();
            //result = row.getCell(columnNumber).getRichStringCellValue().toString();
        } catch (Exception e) {
            try {

                result = (int) row.getCell(columnNumber).getNumericCellValue() + "";

            } catch (Exception e1) {
                result = "0";
            }
        }
        if (columnNumber == 0) {
            result = result.toString().replaceAll("无数据", "").trim();
        } else {
            result =result.toString().replaceAll("-", "").replaceAll("无数据", "").trim();
        }
        return result;
    }
    public static void createTaskFile(String fileName){
            writerFile("\n当前任务添加成功，正在执行任务中，请稍后..\n当前读写文件："+fileName+"\n");
    }

    public static void updateFailTaskFile(List<String> list,String fileName){
        StringBuffer buffer=new StringBuffer();
        buffer.append("\n文件"+fileName+"添加失败的院校有：" + "\n");
            for(String str:list) {
                buffer.append("\n"+str + "\n");
            }
        writerFile(buffer.toString());
    }
    public static void updateSuccessTaskFile(String time,String fileName){
        writerFile("\n"+fileName+"所有院校添加成功，任务已经执行完成,任务用时："+time+"\n");
    }

    public static void errorTaskFile(Exception ep,String fileName){
        writerFile("\n"+fileName+"添加院校异常："+ep.getMessage()+"\n");
    }

    public static synchronized void  writerFile(String content){
        String contextPath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path=contextPath.substring(0,contextPath.length()-16)+"/temp/"+"taskFile.json";
        RandomAccessFile randomFile =null;
        try {
            Writer writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
            writer.write(content);
            writer.flush();
            writer.close();
//            // 打开一个随机访问文件流，按读写方式
//            randomFile = new RandomAccessFile(path, "rw");
//
//            // 文件长度，字节数
//            long fileLength = randomFile.length();
//            //将写文件指针移到文件尾。
//            randomFile.seek(fileLength);
////            String s = new String(content.getBytes("GBK"),"UTF-8");
////            randomFile.writeBytes(s);
//            randomFile.writeUTF(content);
//            randomFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(randomFile!=null){
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                randomFile=null;
            }
        }
    }
    public static synchronized String filetransferTo(final MultipartFile file,HttpServletRequest request){
        String originalFilename = file.getOriginalFilename();
        // FIXME
        // 避免使用request session
        String filePath = request.getSession().getServletContext().getRealPath("/") + "/upload/"
                + originalFilename;
        //转存文件
        if (!file.isEmpty()) {
            try {
                //Map<String, Object> datas =  OldExcelUtil;
                // 转存文件
                file.transferTo(new File(filePath));
                //daoMaps.get(mainObj).insert();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return filePath;
        }
        return null;
    }
    private static String getFileName(String path){
        return path.substring(path.lastIndexOf("/")+1);
    }
}
