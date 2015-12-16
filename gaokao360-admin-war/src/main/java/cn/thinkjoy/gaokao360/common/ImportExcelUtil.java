package cn.thinkjoy.gaokao360.common;


import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 批量导入数据库
 * Created by yyp on 15-8-17.
 */
public class ImportExcelUtil {

    // 声明一个工作薄
    private static HSSFWorkbook workbook;
    //声明一个表格
    private static HSSFSheet sheet;
    //声明样式
    private static HSSFCellStyle style;
    //声明字体
    private static HSSFFont font;
    private static List list=null;

    private static int titleRow=0;

    public static List impExcel(InputStream input,Integer tabcount) throws Exception {
        //标题对应哪一行
        Map<String,Integer> valuemap = new HashMap<>();
        list = Lists.newArrayList();
        //用于返回的字符串
        String sb = null;
        //创建文件输入流
        POIFSFileSystem fs = new POIFSFileSystem(input);
        //声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        //获取第一个表格
        HSSFSheet sheet = wb.getSheetAt(tabcount);
        //迭代表格中所有行
        Iterator rows = sheet.rowIterator();
        //循环所有行
        int tt=0;
        while (rows.hasNext()) {
            String[] stringTitles=null;
            //获取行的单位格
            HSSFRow row = (HSSFRow) rows.next();
            if(row.getRowNum() == titleRow){
                Iterator cells = row.cellIterator();
                //获取表头
                stringTitles =  excelLineData(cells);
            }
            //通常情况第一行为表头过滤不读
            if (row.getRowNum() > titleRow) {
                String[] stringRows=null;
                //标题对应值
                Map<String,String> valueContentMap = new HashMap<>();
                //迭代所有单位格
                Iterator cells = row.cellIterator();
                    stringRows = excelLineData(cells);
                    if (stringRows != null && stringRows.length != 0) {
                        int i=0;
                        if (stringRows != null && stringRows.length != 0) {
                                for (String s: stringTitles) {
                                    if(i<stringRows.length){
                                        valueContentMap.put(s, stringRows[i++]);
                                    }
                                }
                            }
                        if(valueContentMap.size()!=0) {
                            list.add(valueContentMap);
                        }
                    }
            }
        }
        return list;
    }
    //扩展excel导入(支持office2010导入)
    public static List impExcelExt(InputStream input,Integer tabcount) throws Exception {
        //标题对应哪一行
        Map<String,Integer> valuemap = new HashMap<>();
        list = Lists.newArrayList();
        //用于返回的字符串
        String sb = null;
        //声明一个工作薄
        XSSFWorkbook wb = new XSSFWorkbook(input);
        //获取第一个表格
        XSSFSheet sheet = wb.getSheetAt(tabcount);
        //迭代表格中所有行
        Iterator rows = sheet.rowIterator();
        //循环所有行
        int tt=0;
        String[] stringTitles=null;
        while (rows.hasNext()) {
            //获取行的单位格
            XSSFRow row = (XSSFRow) rows.next();
            if(row.getRowNum() == titleRow){
                Iterator cells = row.cellIterator();
                stringTitles =  excelLineDataExt(cells);
            }
            //通常情况第一行为表头过滤不读
            if (row.getRowNum() > titleRow) {
                String[] stringRows=null;
                //标题对应值
                Map<String,String> valueContentMap = new HashMap<>();
                //迭代所有单位格
                Iterator cells = row.cellIterator();
                stringRows = excelLineDataExt(cells);
                int i=0;
                if (stringRows != null && stringRows.length != 0) {
                    for (String s : stringTitles) {
                        if(i<stringRows.length){
                            valueContentMap.put(s, stringRows[i++]);
                        }
                    }
                }
                if(valueContentMap.size()!=0) {
                    list.add(valueContentMap);
                }
            }
        }
        return list;
    }
    /**
     * 获取单行数据
     */
    private static String[] excelLineData(Iterator cells){
        //用户拼接所有行的列
        StringBuffer sbvalue = new StringBuffer();
        int count=0;
        //循环所有列
        while (cells.hasNext()) {
            HSSFCell cell = (HSSFCell) cells.next();
            int mm=cell.getColumnIndex();
            //判断单元格中值类型
            String value = null;
                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    //判断数字是时间类型进行转换
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue()+"";
                    }
                    else{
                        //该处强制转换为long型确保数据准确性(在Excel中输入13个数字获取含小数)
                        value = String.valueOf((long) cell.getNumericCellValue());
                    }
                }
                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    value = String.valueOf(cell.getStringCellValue());
                }
                if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                    value = String.valueOf(cell.getBooleanCellValue());
                }
                if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {

                    value = String.valueOf(cell.getCellFormula());
                }
                if (cell.getCellType() == HSSFCell.CELL_TYPE_ERROR) {
                    value = "";
                }
                if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    value = "";
                }
                //如果Excel中某列的值为空，默认赋值
                if (value == null) {
                    value = "";
                }
                for(int i =1;i<mm-count;i++){
                    sbvalue.append(",");
                }
            count=mm;
                    //将Excle中的数据拼接起来用","隔开
                sbvalue.append(value).append(",");
            }
        return sbvalue.toString().split(",");
    }

    private static String[] excelLineDataExt(Iterator cells){
        //用户拼接所有行的列
        StringBuffer sbvalue = new StringBuffer();
        int count=0;
        //循环所有列
        while (cells.hasNext()) {
            XSSFCell cell = (XSSFCell) cells.next();
            int mm=cell.getColumnIndex();
            //判断单元格中值类型
            String value = null;
            if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                //判断数字是时间类型进行转换
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue()+"";
                }
                else{
                    //该处强制转换为long型确保数据准确性(在Excel中输入13个数字获取含小数)
                    value = String.valueOf((long) cell.getNumericCellValue());
                }
            }
            if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                value = String.valueOf(cell.getStringCellValue());
            }
            if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
                value = String.valueOf(cell.getBooleanCellValue());
            }
            if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {

                value = String.valueOf(cell.getCellFormula());
            }
            if (cell.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
                value = "";
            }
            if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
                value = "";
            }
            //如果Excel中某列的值为空，默认赋值
            if (value == null) {
                value = "";
            }
            for(int i =1;i<mm-count;i++){
                sbvalue.append(",");
            }
            count=mm;
            //将Excle中的数据拼接起来用","隔开
            sbvalue.append(value).append(",");
        }
        return sbvalue.toString().split(",");
    }

    public static List getList() {
        return list;
    }

    public static void setList(List list) {
        ImportExcelUtil.list = list;
    }

    private static Map<String,Object> mapStringToObj(Class[] clzs,String[] asField,Map<String,String> map){
        Map<String,Object> map1 = new HashMap<>();
        for(int i =0 ; i<asField.length;i++){
            if(clzs[i].getName().endsWith("String")){
                map1.put(asField[i],map.get(asField[i]));
            }else if(clzs[i].getName().endsWith("Long")){
                System.out.println(clzs[i].getName());
                map1.put(asField[i], Long.valueOf(map.get(asField[i])));
                System.out.println(Long.valueOf(map.get(asField[i])));
            }else if(clzs[i].getName().endsWith("Integer")){
                map1.put(asField[i],Integer.parseInt(map.get(asField[i])));
            }
        }
        return map1;
    }

    public static void main(String[] args){
        try {
            Class[] clzs = new Class[]{String.class,String.class,Long.class};
            List<Map<String,String>> l = impExcelExt(new FileInputStream(new File("C:\\Users\\admin\\Desktop\\gk_card.xlsx")), 0);
            for(Map<String,String> map:l){
                System.out.println(map.get("tt1"));
                System.out.println(map.get("tt2"));
                System.out.println(String.valueOf(map.get("tt4")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param file 文件MultipartFile
     * @return map数组一堆，与dblinename顺序名称相同
     */
        public static List doImport(MultipartFile file) throws Exception {
            InputStream in = null;
            in = file.getInputStream();
            String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1);
            List<Map<String, Object>> list = null;
            if("xls".equals(extName)) {
                list  = impExcel(in,0);
            } else if("xlsx".equals(extName)) {
                list = impExcelExt(in,0);
            }
            return list;
         }


    public static int getTitleRow() {
        return titleRow;
    }

    public static void setTitleRow(int titleRow) {
        ImportExcelUtil.titleRow = titleRow;
    }
}