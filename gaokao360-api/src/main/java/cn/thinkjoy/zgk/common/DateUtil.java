package cn.thinkjoy.zgk.common;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 
 * 日期转换函数
 * @author Ethan.Lam  2011-7-24
 *
 */
public class DateUtil {

	public static final String Formater_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String Formater_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String Formater_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		  str = str!=null?str.trim():str;
          return  str==null||"".equals(str)?true:false;		
	}
	
	
	/**
	 * 
     * 获取当前的时间戳
     * 
     * @return
     */
    public static String getCurrentTimestamp() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		return formater.format(today);
    }
    /**
     * 获取当前时间数字形式
     * @return
     */
    public static long getTimeInMillis() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.getTimeInMillis();
    }
    
	/**
	 * 
     * Date 对象转换成对应格式的 字符串
     * @param date
     * @param format  如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss" 
     * @return   
     * 
     */
    public static String dateToString(Date date,String format) {
    	if(date==null)
    		return "";
		SimpleDateFormat formater = new SimpleDateFormat(isNull(format)?"yyyy-MM-dd HH:mm:ss":format.trim());
		return formater.format(date);
    }
	/**
	 * 
     * Date 对象转换成对应格式的 字符串
     * @param date
     * @param format  "yyyy-MM-dd HH:mm:ss" 
     * @return   
     * 
     */
    public static String dateToyMd(Date date) {
    	if(date==null)
    		return "";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(date);
    }
    
    /**
	 * 
     * Date 对象转换成对应格式的 字符串
     * @param date
     * 默认："yyyy-MM-dd HH:mm:ss" 
     * @return   
     * 
     */
    public static String dateToString(Date date) {
    	if(date==null)
    		return "";
		return dateToString(date,null);
    }
    public static String timestampToString(java.sql.Timestamp timestamp) {
    	if(timestamp==null)
    		return "";
		return timestamp.toString();
    }
    /**
     * XMLGregorianCalendar 对象转换成对应格式的 字符串
     * @param cal
     * @return
     * 默认："yyyy-MM-dd HH:mm:ss"
     * @throws Exception
     */
    public  static String xMLGregorianToString(XMLGregorianCalendar cal) throws Exception{
        GregorianCalendar ca = cal.toGregorianCalendar();
        return dateToString(ca.getTime(),"yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 
     * Date 对象转换成对应格式的 字符串
     * @param dateSource
     * @param format  如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"  
     * @return
     * 
     */
    public static Date timeStrToDate(String dateSource,String format) {
    	if(isNull(dateSource))
    		return null;
		SimpleDateFormat formater = new SimpleDateFormat(isNull(format)?"yyyy-MM-dd HH:mm:ss":format.trim());
		try {
			return formater.parse(dateSource);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 
     * Date 对象转换成对应格式的 字符串
     * @param dateSource
     * 默认："yyyy-MM-dd HH:mm:ss"  
     * @return
     * 
     */
    public static Date timeStrToDate(String dateSource) {
    	if(isNull(dateSource))
    		return null;
		return timeStrToDate(dateSource,null) ;
    }
    
    /**
	 * 得到系统日期
	 * 
	 * @return  
	 */
	public static String getDate() {
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR) + "";
		String month = calendar.get(Calendar.MONTH) + 1 + "";
		String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
		if (month.length() == 1)
			month = "0" + month;
		
		return year+"-"+month+"-"+day;
	}
	
	/**
	 * 得到系统日期,xx月xx日 xx xx:xx
	 * 
	 * @return  
	 */
	public static String getWapDate() {
		Calendar calendar = Calendar.getInstance();
		String month = calendar.get(Calendar.MONTH) + 1 + "";
		String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		String minute = calendar.get(Calendar.MINUTE)+"";
		
		if (month.length() == 1)
			month = "0" + month;
		
		return month+"月"+day+"日 "+hour+":"+minute;
	}
	
	
	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数 
	 * @param nowdate
	 * @param delay   
	 * @return   
	 */
	public static String getNextDay(String nowdate, int delay) { 
	  try{ 
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	       String mdate = ""; 
	       Date d = timeStrToDate(nowdate); 
	       long myTime = (d.getTime() / 1000) +  delay * 24 * 60 * 60; 
	       d.setTime(myTime * 1000); 
	       mdate = format.format(d); 
	       return mdate; 
	   }catch(Exception e){ 
	        return ""; 
	   } 
	} 
	
	/**
	 * 得到一个时间延后或前移几天的日期,nowdate为日期,delay为前移或后延的天数 
	 * @param nowdate
	 * @param delay   
	 * @return   
	 */
	public static Date getNextDay(Date nowdate, int delay) {
		Date nextDay = new Date();
		try {
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(nowdate);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, delay); // 设置为前一天
			nextDay = calendar.getTime(); // 得到前一天的时间
			return nextDay;
		} catch (Exception e) {
			return nextDay;
		}
	}

	/**
	 * 
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数 
	 * @param nowdate
	 * @param delay  小于 0，过去多小天，大于0 未来多小天
	 * @param dateFormat
	 * @return  
	 */
	public static String getNextDay(String nowdate,int delay,String dateFormat) { 
	  try{ 
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	       String mdate = ""; 
	       Date d = timeStrToDate(nowdate,dateFormat); 
	       long myTime = (d.getTime() / 1000) + delay* 24 * 60 * 60; 
	       d.setTime(myTime * 1000); 
	       mdate = format.format(d); 
	       return mdate; 
	   }catch(Exception e){ 
	        return ""; 
	   } 
	} 
	
	
	/**
	 * 返回今天的时间段
	 * @return   yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getTodayPeriods(){
		String today = dateToString(new Date(),Formater_yyyy_MM_dd);
		return new String[]{today+" 00:00:00",today+" 23:59:59"};	
	}

	/**
	 * 返回今天的时间段
	 * @return   date
	 */
	public static Date[] getTodayPeriodsDate(){
		String today = dateToString(new Date(),Formater_yyyy_MM_dd);
		return new Date[]{ timeStrToDate(today+" 00:00:00") ,timeStrToDate(today+" 23:59:59")};	
	}
	/**
	 * 返回昨天的时间段
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getYesterdayPeriods(){
		String today = dateToString(new Date(),Formater_yyyy_MM_dd);
		String beforeDay = getNextDay(today,-1,Formater_yyyy_MM_dd);
		return new String[]{beforeDay+" 00:00:00",beforeDay+" 23:59:59"};
	}
	
	/**
	 * 返回一个星期(7天前)的时间段
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getWeekPeriods(){
		String today = dateToString(new Date(),Formater_yyyy_MM_dd);
		String beforeDay = getNextDay(today,-7,Formater_yyyy_MM_dd);
		return new String[]{beforeDay+" 00:00:00",today+" 23:59:59"};	
	}

	/**
	 * 返回本周的时间段
	 * @return yyyy-MM-dd HH:mm:ss
	 */
//	public static String[] getThisWeekPeriods() {
//		Calendar cal = Calendar.getInstance();
//		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
//		cal.add(Calendar.DATE, -day_of_week);
//		Date startDate=cal.getTime();
//		cal.add(Calendar.DATE, 6);
//		Date endDate=cal.getTime();
//		return new String[] { dateToString(startDate,"yyyy-MM-dd") + " 00:00:00", dateToString(endDate,"yyyy-MM-dd") + " 23:59:59" };
//	}
//	/**
//	 * 返回上周的时间段
//	 * @return yyyy-MM-dd HH:mm:ss
//	 */
//	public static String[] getLastWeekPeriods() {
//		Calendar cal = Calendar.getInstance();
//		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 3;
//		cal.add(Calendar.DATE, -day_of_week);
//		Date startDate=cal.getTime();
//		cal.add(Calendar.DATE, 6);
//		Date endDate=cal.getTime();
//		return new String[] { dateToString(startDate,"yyyy-MM-dd") + " 00:00:00", dateToString(endDate,"yyyy-MM-dd") + " 23:59:59" };
//	}
	
	/**
	 * 返回的一个月时间段
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getMonthDayPeriods(int difMonths){
		difMonths=difMonths==0?1:difMonths;
		String today = dateToString(new Date(),Formater_yyyy_MM_dd);
		String beforeDay = getNextDay(today,-7*4*difMonths,Formater_yyyy_MM_dd);
		return new String[]{beforeDay+" 00:00:00",today+" 23:59:59"};	
	}
    	
	
	
	/**
	 * 得到系统当前的时间
	 * @return
	 */
	public static String getSysDateTime(){
		return dateToString(new Date(),DateUtil.Formater_yyyy_MM_dd_HH_mm_ss);
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	
	public static void main(String...args){
//		Date input=DateUtil.timeStrToDate("2012-03-01 00:00:00");
////		int day=(int)Math.abs(getDiffDays(input));
////		System.out.println(day%365);
////		System.out.println(getDiffDays(input));
//		System.out.println(getDiffYears(input));
		
//		System.out.println(getMondayPlus()+"  a "+getPreviousWeekday()+"   b  "+getPreviousWeekSunday());
		String date[]=getWeekDateTime();
				
		System.out.println(date[0]+"  aa "+date[1]);
//		System.out.println(isOutOfDate("2015-06-16 00:00:00",date[0], date[1]));
		
//		System.out.println(getThisWeekPeriods());
		System.out.println(calDayByYearAndMonth("2015", "2"));
		Date inDate=DateUtil.timeStrToDate("2015-12-05 23:59:59");
		System.out.println(new Date().before(inDate));
//		System.out.println(getMonthStartEnd("2015", "2"));
	}
	/**
	 * 对比当前时间与要验证的时间相差的分钟数
	 * @param checkTime 要验证的时间
	 * @param minute 分钟数
	 * @return 要验证的时间少于当前时间 false 要验证的时间大于当前时间 true
	 */
	public static boolean CheckInDate(String checkTime,int minute) 
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(checkTime));
			cd.add(Calendar.MINUTE, minute);
			Date nowDate = new Date();

			if (cd.getTime().getTime() < nowDate.getTime()) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 获得"yyyy年MM月dd日   星期N"格式的时间输出
	 * @param date
	 * @return
	 */
	public final static String getStandardFormatTime(Date date) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日   EEEE");
		
		return format.format(date);
		
	}
	/**
	 * 当前时间与输入时间相差多少天
	 * @param date
	 * @return 输入-当前
	 */
	public static long getDiffDays(Date inputDate){
	    long result=(inputDate.getTime() - getCurrentDate().getTime())/ (24 * 60 * 60 * 1000);
	    if((inputDate.getTime() - getCurrentDate().getTime())% (24 * 60 * 60 * 1000)>0){
	    	result=result+1;
	    }
	    return result;
	}
	/**获取两个时间相差多少年
	 * @param inputDate
	 * @return
	 */
	public static long getDiffYears(Date inputDate){
	    long result=(getCurrentDate().getTime() - inputDate.getTime())/ (24 * 60 * 60 * 1000)/365;
//	    if((getCurrentDate().getTime() - inputDate.getTime())% (24 * 60 * 60 * 1000)>0){
//	    	result=result+1;
//	    }
	    return result;
	}
	/**
	 * 当前时间与输入时间相差多少
	 * @param inputDate
	 * @return 输入-当前
	 */
	public static long getDiffSend(Date inputDate){
//	    long result=(inputDate.getTime() - getCurrentDate().getTime())/ (24 * 60 * 60 * 1000);
//	    if((inputDate.getTime() - getCurrentDate().getTime())% (24 * 60 * 60 * 1000)>0){
//	    	result=result+1;
//	    }
	    return (inputDate.getTime() - getCurrentDate().getTime());
	}
	/**
	 * 得到一个时间延后或前移几个月的日期 
	 * @param delaymonth   
	 * @return   
	 */
	public static Date getNextDayByMonth (int delaymonth) {
		Date nextDay = new Date();
		try {
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(getCurrentDate());// 把当前时间赋给日历
			calendar.add(Calendar.MONTH, delaymonth); // 设置为前一天
			nextDay = calendar.getTime(); // 得到前一天的时间
			return nextDay;
		} catch (Exception e) {
			return nextDay;
		}
	}
	
	/**返回与当前时间相着多少天的时间
	 * @param delayDay
	 * @return
	 */
	public static Date getNextDayByDay (int delayDay) {
		Date nextDay = new Date();
		try {
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(getCurrentDate());// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH,delayDay); //设置天数
			calendar.set(Calendar.HOUR_OF_DAY, 23);//当天小时
			calendar.set(Calendar.MINUTE, 59);//当天分钟
			nextDay = calendar.getTime(); // 得到时间
			return nextDay;
		} catch (Exception e) {
			return nextDay;
		}
	}
	/**
	 * 判断date是否在当前时间之前
	 * @param date
	 * @return
	 */
	public static boolean before(Date date){
		return new Date().before(date);
	}
	/**
	 * 手机号码*处理
	 * hsq 2012-5-17
	 * @param str
	 * @return
	 */
	public static String PhoneNumber(String str){
	if(str!=null&&str.length()==11){
			String s="";
			String s1=str.substring(0,3);
			String s2=str.substring(7);
			s=s1+"****"+s2;
			return s;
		}else return str	;
		
	}

	/**
	 * 返回 d1-d2的相差的分钟数
	 * @param d1 格式：yyyy-MM-dd HH:mm:ss
	 * @param d2 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDiffMins(String date1,String date2){
		StringUtil stuStringUtil=new StringUtil();
		String reString="";
		if(stuStringUtil.isNulOrBlank(date1)==true || stuStringUtil.isNulOrBlank(date2)==true){
			return reString;
		}
		//LoggerUtil.info("date1:"+date1+",date2:"+date2);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
		    Date d1 = df.parse(date1);
		    Date d2 = df.parse(date2);
		    long diff = Math.abs(d1.getTime() - d2.getTime())/1000;
		    long second=diff%60;
		    long min=(diff/60)%60;
		    long hou=(diff/3600)%24;
		    long day=(diff/3600)/24;
		    if(day>0){
		    	reString=day+"天";
		    }
		    if(hou>0){
		    	reString+=hou+"时";
		    }
		    if(min>0){
		    	reString+=min+"分";
		    }
		    if(second>0){
		    	reString+=(int)second+"秒";
		    }
//		    long days = diff / (1000 * 60 * 60 * 24);
//		    minCount=days*24*60;
		    if(stuStringUtil.isNulOrBlank(reString)){
		    	reString="0";
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return reString;
	}
	public static String getDiffMins(Date indate){
		try {
			Date d1 = new Date();
		    Date d2 = indate;
		    long diff = (d1.getTime() - d2.getTime())/1000;
//		    long second=diff%60;
		    long min=(diff/60)%60;
		    long hou=diff/3600;
		    if(hou>0){
		    	if(hou>0&&hou<23){
		    		return dateToString(indate, "HH:mm");
		    	}else{
		    		return dateToString(indate, "MM月dd日");
		    	}
		    }else{
		    	if(min==0){
		    		return "刚刚";
		    	}else {
		    		return min+"分钟前";
		    	}
		    }
//		    
//		    if(hou>0){
//		    	if(hou<=1){
//		    		return hou+"小时前";
//		    	}else if(hou>1&&hou<23){
//		    		
//		    	}else{
//		    		return dateToString(indate, "MM月dd日");
//		    	}
//		    	
//		    }
//		    if(min>0){
//		    	return min+"分钟前";
//		    }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return dateToString(indate, "MM月dd日");
	}
	
    /**
     * <pre>
     * 判断date和当前日期是否在同一周内
     * 注:
     * Calendar类提供了一个获取日期在所属年份中是第几周的方法，对于上一年末的某一天
     * 和新年初的某一天在同一周内也一样可以处理，例如2012-12-31和2013-01-01虽然在
     * 不同的年份中，但是使用此方法依然判断二者属于同一周内
     * 周日和周六肯定不在同一周内，就是说一周的开始是周日，结束是周六 
     * </pre>
     * 
     * @param date
     * @return
     */
    public static boolean isSameWeekWithToday(Date date) {
        
        if (date == null) {
            return false;
        }
        
        // 0.先把Date类型的对象转换Calendar类型的对象
        Calendar todayCal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();

        todayCal.setTime(new Date());
        dateCal.setTime(date);

        // 1.比较当前日期在年份中的周数是否相同
        if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**与当前时间相差月数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(String date)throws ParseException {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date));
		c2.setTime(new Date());

		int year=c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		result = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);
		result=year*12+result;

		return result == 0 ? 1 : Math.abs(result);

	}
    /**与当前时间相差月数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(Date date)throws ParseException {

		int result = 0;

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date);
		c2.setTime(new Date());
		int year=c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		result = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);
		result=year*12+result;

		return result == 0 ? 1 : Math.abs(result);

	}
	
	/**
	 * 得到一个时间延后或前移几小时的时间,nowdate为当前时间的Date对象,delay为前移或后延的小时数
	 * @param nowdate
	 * @param delay   
	 * @return   
	 */
	public static Date getNextHour(Date nowdate, int delay) {
		Date nextDay = new Date();
		try {
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(nowdate);// 把当前时间赋给日历
			calendar.add(Calendar.HOUR_OF_DAY, delay); // 设置为延后多少小时
			nextDay = calendar.getTime(); // 得到延后多少小时的时间
			return nextDay;
		} catch (Exception e) {
			return nextDay;
		}
	}
	
	/**
	 * 当月份的开始时间，和结束时间
	 * @return 第一个是开始时间，第二个是结束时间
	 */
	public static String[] getMonthDate(){
		Calendar calendar=Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH)+1; //当前月份
		int year = calendar.get(Calendar.YEAR); //当前年份
		String monthStr = month>9?(month+""):("0"+month);
		
		String startDate = year + "-" + monthStr + "-01 00:00:00"; //开始日期
		calendar.setTime(timeStrToDate(startDate));
		calendar.add(calendar.MONTH,   1);    //加一个月
		SimpleDateFormat sf = new SimpleDateFormat(Formater_yyyy_MM_dd_HH_mm_ss);
		String endDate = sf.format(calendar.getTime()); //结束日期
		
		String[] result = new String[]{startDate, endDate};
		return result;
	}
	
	/**获取今天的开始结束时间
	 * @return
	 */
	public static String[] getCurrentDateBeginEnd(){
		String currentDate=dateToString(new Date(), Formater_yyyy_MM_dd);
		String startDate=currentDate+" 00:00:00";
		String endDate=currentDate+" 23:59:59";
		String[] result = new String[]{startDate, endDate};
		return result;
	}
	
	/**获取当前年的1月1日
	 * @return
	 */
	public static Date getCurrentYear(){
		Calendar calendar=Calendar.getInstance();
		String currnet = calendar.get(Calendar.YEAR) + "-01-01 00:00:00"; //开始日期
		return timeStrToDate(currnet);
	}
    /**获取一周的开始结束时间
     * @return 第一个是开始时间 第二个结束时间
     */
    public static String[] getWeekDateTime() {  
    	
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        String date1=dateToString(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 7);  
        String date2=dateToString(cal.getTime());
        String date[]={date1,date2};
        return date;  
    }  
  
    /**
     * 判断是否在一个起止日期内<br/>
     * 例如:2012-04-05 00:00:00~2012-04-15 00:00:00
     *
     * @param start_time
     * @param over_time
     * @return (int)&nbsp;在这个时间段内返回1，不在返回0
     * @throws ParseException
     */
	public static int isOutOfDate(String start_time, String over_time) {
		try {
			long nowTimeLong = new Date().getTime();
			long ckStartTimeLong = new SimpleDateFormat(Formater_yyyy_MM_dd_HH_mm_ss).parse(start_time).getTime();
			long ckOverTimeLong = new SimpleDateFormat(Formater_yyyy_MM_dd_HH_mm_ss).parse(over_time).getTime();
			if (nowTimeLong > ckStartTimeLong && nowTimeLong < ckOverTimeLong) {
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return 0;
	}
	 /**
     * 判断一个自定义日期是否在一个起止日期内<br/>
     * 例如:判断2012-01-05 00:00:00是否在2012-04-05 00:00:00~2012-04-15 00:00:00
     *
     * @param start_time
     * @param over_time
     * @return (int)&nbsp;在这个时间段内返回1，不在返回0
     * @throws ParseException
     */
	public static int isOutOfDate(String time, String start_time, String over_time) {
		try {
			long timeLong = new SimpleDateFormat(Formater_yyyy_MM_dd_HH_mm_ss).parse(time).getTime();
			long ckStartTimeLong = new SimpleDateFormat(Formater_yyyy_MM_dd_HH_mm_ss).parse(start_time).getTime();
			long ckOverTimeLong = new SimpleDateFormat(Formater_yyyy_MM_dd_HH_mm_ss).parse(over_time).getTime();
			if (timeLong > ckStartTimeLong && timeLong < ckOverTimeLong) {
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return 0;
	}
	
	/**获取与输入时间相关多少分钟的时间
	 * @param checkTime 验证时间
	 * @param minute   相差分钟数
	 * @return
	 */
	public static String getDateDelayByMinute(String checkTime,int minute) 
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(checkTime));
			cd.add(Calendar.MINUTE, minute);
//			Date nowDate = new Date();

			return dateToString(cd.getTime());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkTime;
	}
	/**
	 * 获取两个时间相差年份
	 * @param front
	 * @param behind
	 * @return
	 */
//	public static int getDiffYears(Date front, Date behind){
//		Calendar calendar=Calendar.getInstance();
//		calendar.setTime(front);
//		int frontYear = calendar.get(Calendar.YEAR);
//		calendar.setTime(behind);
//		int endYear = calendar.get(Calendar.YEAR);
//		return endYear-frontYear;
//	}
	
	/**获取上一周的开始结束时间
	 * @return
	 */
	public static String [] getPreWeekDay() {
		int mondayPlus = getMondayPlus();

		GregorianCalendar currentDate1 = new GregorianCalendar();

		currentDate1.add(5, mondayPlus + 7 * -1);

		Date monday1 = currentDate1.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String weekPre = sdf.format(monday1)+" 00:00:00";
		
		GregorianCalendar currentDat2e = new GregorianCalendar();
		currentDat2e.add(GregorianCalendar.DATE, mondayPlus + -1);
		Date monday2 = currentDat2e.getTime();
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String weekEnd = sdf.format(monday2)+" 23:59:59";
		
		String []result={weekPre,weekEnd};

		return result;
	}


	/**获得当前日期与本周日相差的天数  
	 * @return
	 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}
	
	/**获取指定月份的开始结束时间
	 * @param dyear
	 * @param dmouth
	 * @return
	 */
	public static String[] getMonthStartEnd(String dyear, String dmouth){
		Calendar calendar=Calendar.getInstance();
//		int month = calendar.get(Calendar.MONTH)+1; //当前月份
//		int year = calendar.get(Calendar.YEAR); //当前年份
//		String monthStr = month>9?(month+""):("0"+month);
		
		String startDate = dyear + "-" + dmouth + "-01 00:00:00"; //开始日期
		calendar.setTime(timeStrToDate(startDate));
		calendar.add(calendar.MONTH,   1);    //加一个月
		SimpleDateFormat sf = new SimpleDateFormat(Formater_yyyy_MM_dd_HH_mm_ss);
		String endDate = sf.format(calendar.getTime()); //结束日期
		
		String[] result = new String[]{startDate, endDate};
		return result;
	}
	
	/**获取指定月份的天数
	 * @param dyear
	 * @param dmouth
	 * @return
	 */
	public static int calDayByYearAndMonth(String dyear, String dmouth) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(simpleDate.parse(dyear + "/" + dmouth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);// 根据年月 获取月份天数
	}
}
