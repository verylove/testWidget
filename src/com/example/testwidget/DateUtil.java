package com.example.testwidget;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




public class DateUtil {

	     private DateUtil(){  
	           
	     }  
	       
	     private static final String hhmmFormat="HH:mm";  
	     private static final String MMddFormat="MM-dd";  
	     private static final String yyyyFormat="yyyy";  
	     private static final String yyyyChineseFormat="yyyy��";  
	     private static final String yyyyMMddFormat="yyyy-MM-dd";  
	     private static final String fullFormat="yyyy-MM-dd HH:mm:ss";  
	     private static final String MMddChineseFormat="MM��dd��";  
	     private static final String yyyyMMddChineseFormat="yyyy��MM��dd��";  
	     private static final String fullChineseFormat="yyyy��MM��dd�� HHʱmm��ss��";  
	     private static final String [] WEEKS={"������","����һ","���ڶ�","������","������","������","������"};  
	       
	     /** 
	      * �õ�ָ��ʱ���ʱ�����ڸ�ʽ 
	      * @param date ָ����ʱ�� 
	      * @param format ʱ�����ڸ�ʽ 
	      * @return 
	 */  
	     public static String getFormatDateTime(Date date,String format){  
	         DateFormat df=new SimpleDateFormat(format);  
	         return df.format(date);  
	     }  
	       
	     /** 
	      * �ж��Ƿ������� 
	      * @param date ָ����ʱ�� 
	      * @return true:������,false:�������� 
	 */  
	     public static boolean isLeapYear(Date date) {  
	        Calendar cal=Calendar.getInstance();  
	        cal.setTime(date);  
	        return isLeapYear(cal.get(Calendar.YEAR));  
	     }  
	       
	     /** 
	      * �ж��Ƿ������� 
	      * @param date ָ������ 
	      * @return true:������,false:�������� 
	 */  
	     public static boolean isLeapYear(int year) {  
	        GregorianCalendar calendar = new GregorianCalendar();  
	        return calendar.isLeapYear(year);  
	     }  
	       
	     /** 
	      * �ж�ָ����ʱ���Ƿ��ǽ��� 
	      * @param date ָ����ʱ�� 
	      * @return true:�ǽ���,false:�ǽ��� 
	 */  
	     public static boolean isInToday(Date date){  
	         boolean flag=false;  
	         Date now=new Date();  
	         String fullFormat=getFormatDateTime(now,DateUtil.yyyyMMddFormat);  
	         String beginString=fullFormat+" 00:00:00";  
	         String endString=fullFormat+" 23:59:59";  
	         DateFormat df=new SimpleDateFormat(DateUtil.fullFormat);  
	         try {  
	             Date beginTime=df.parse(beginString);  
	             Date endTime=df.parse(endString);  
	             flag=date.before(endTime)&&date.after(beginTime);  
	         } catch (ParseException e) {  
	             e.printStackTrace();  
	         }  
	         return flag;  
	     }  
	       
	     /** 
	      * �ж���ʱ���Ƿ���ͬһ�� 
	      * @param from ��һ��ʱ��� 
	      * @param to �ڶ���ʱ��� 
	      * @return true:��ͬһ��,false:��ͬһ�� 
	 */  
	     public static boolean isSameDay(Date from,Date to){  
	         boolean isSameDay=false;  
	         DateFormat df=new SimpleDateFormat(DateUtil.yyyyMMddFormat);  
	         String firstDate=df.format(from);  
	         String secondDate=df.format(to);  
	         isSameDay=firstDate.equals(secondDate);  
	         return isSameDay;  
	     }  
	       
	     /** 
	      * ���ָ����ʱ�����������ڼ� 
	      * @param date ָ����ʱ�� 
	      * @return ����X 
	 */  
	     public static String getWeekString(Date date){  
	         return DateUtil.WEEKS[getWeek(date)-1];  
	     }  
	       
	     /** 
	      * ���ָ��ʱ�����������ڼ� 
	      * @param date ָ����ʱ�� 
	      * @return 1-7 
	 */  
	     public static int getWeek(Date date){  
	         int week=0;  
	         Calendar cal=Calendar.getInstance();  
	         cal.setTime(date);  
	         week=cal.get(Calendar.DAY_OF_WEEK);  
	         return week;  
	     }  
	       
	     /** 
	      * ȡ��ָ��ʱ���������Ƕ���ʱ����ǰ���磺3��ǰ,2Сʱǰ�� 
	      * ע�⣺�˼��㷽�����Ǿ�ȷ�� 
	      * @param date ���е�ָ��ʱ�� 
	      * @return ʱ������� 
	 */  
	     public static String getAgoTimeString(Date date){  
	         Date now=new Date();  
	         Calendar cal=Calendar.getInstance();  
	         cal.setTime(date);  
	         Date agoTime=cal.getTime();  
	         long mtime=now.getTime()-agoTime.getTime();  
	         String str="";  
	         long stime=mtime/1000;  
	         long minute=60;  
	         long hour=60*60;  
	         long day=24*60*60;  
	         long weeks=7*24*60*60;  
	         long months=100*24*60*60;  
	         if(stime<minute){  
	             long time_value=stime;  
	             if(time_value<=0){  
	                 time_value=1;  
	             }  
	             str=time_value+"��ǰ";  
	         }else if(stime>=minute && stime<hour){  
	             long time_value=stime/minute;  
	             if(time_value<=0){  
	                 time_value=1;  
	             }  
	             str=time_value+"��ǰ";  
	         }else if(stime>=hour && stime<day){  
	             long time_value=stime/hour;  
	             if(time_value<=0){  
	                 time_value=1;  
	             }  
	             str=time_value+"Сʱǰ";  
	         }else if(stime>=day&&stime<weeks){  
	             long time_value=stime/day;  
	             if(time_value<=0){  
	                 time_value=1;  
	             }  
	             str=time_value+"��ǰ";  
	         }else if(stime>=weeks&&stime<months){  
	             DateFormat df=new SimpleDateFormat(DateUtil.MMddFormat);  
	             str=df.format(date);  
	         }else{  
	             DateFormat df=new SimpleDateFormat(DateUtil.yyyyMMddFormat);  
	             str=df.format(date);  
	         }  
	         return str;  
	     }  
	       
	     /** 
	      * �ж�ָ��ʱ���Ƿ�����ĩ 
	      * @param date ָ����ʱ�� 
	      * @return true:����ĩ,false:����ĩ 
	 */  
	     public static boolean isWeeks(Date date){  
	         boolean isWeek=false;  
	         isWeek=(getWeek(date)-1==0||getWeek(date)-1==6);  
	         return isWeek;  
	     }  
	       
	     /** 
	      * �õ�������ʼʱ�� 
	      * @return ������ʼʱ�� 
	 */  
	     public static Date getTodayBeginTime(){  
	         String beginString=DateUtil.yyyyMMddFormat+" 00:00:00";  
	         DateFormat df=new SimpleDateFormat(DateUtil.fullFormat);  
	         Date beginTime=new Date();  
	         try {  
	             beginTime=df.parse(beginString);  
	         } catch (ParseException e) {  
	             e.printStackTrace();  
	         }  
	         return beginTime;  
	     }  
	       
	     /** 
	      * �õ������������ʱ�� 
	      * @return ��������ʱ�� 
	 */  
	     public static Date getTodayEndTime(){  
	         String endString=DateUtil.yyyyMMddFormat+" 23:59:59";  
	         DateFormat df=new SimpleDateFormat(DateUtil.fullFormat);  
	         Date endTime=new Date();  
	         try {  
	             endTime=df.parse(endString);  
	         } catch (ParseException e) {  
	             e.printStackTrace();  
	         }  
	         return endTime;  
	     }  
	       
	     /** 
	      * ȡ�ñ��ܵĿ�ʼʱ�� 
	      * @return ���ܵĿ�ʼʱ�� 
	 */  
	     public static Date getThisWeekBeginTime(){  
	         Date beginTime=null;  
	         Calendar cal=Calendar.getInstance();  
	         int week=getWeek(cal.getTime());  
	         week=week-1;  
	         int days=0;  
	         if(week==0){  
	             days=6;  
	         }else{  
	             days=week-1;  
	         }  
	         cal.add(Calendar.DAY_OF_MONTH, -days);  
	         beginTime=cal.getTime();  
	         return beginTime;  
	     }  
	       
	     /** 
	      * ȡ�ñ��ܵĿ�ʼ���� 
	      * @param format ʱ��ĸ�ʽ 
	      * @return ָ����ʽ�ı����ʼʱ�� 
	 */  
	     public static String getThisWeekBeginTimeString(String format){  
	         DateFormat df=new SimpleDateFormat(format);  
	         return df.format(getThisWeekBeginTime());  
	     }  
	       
	       
	     /** 
	      * ȡ�ñ��ܵĽ���ʱ�� 
	      * @return ���ܵĽ���ʱ�� 
	 */  
	     public static Date getThisWeekEndTime(){  
	         Date endTime=null;  
	         Calendar cal=Calendar.getInstance();  
	         int week=getWeek(cal.getTime());  
	         week=week-1;  
	         int days=0;  
	         if(week!=0){  
	             days=7-week;  
	         }  
	         cal.add(Calendar.DAY_OF_MONTH, days);  
	         endTime=cal.getTime();  
	         return endTime;  
	     }  
	       
	       
	     /** 
	      * ȡ�ñ��ܵĽ������� 
	      * @param format ʱ��ĸ�ʽ 
	      * @return ָ����ʽ�ı��ܽ���ʱ�� 
	 */  
	     public static String getThisWeekEndTimeString(String format){  
	         DateFormat df=new SimpleDateFormat(format);  
	         return df.format(getThisWeekEndTime());  
	     }  
	       
	     /** 
	      * ȡ����ʱ���������� 
	      * @param from ��һ��ʱ�� 
	      * @param to �ڶ���ʱ�� 
	      * @return �������� 
	 */  
	     public static long getBetweenDays(Date from, Date to){  
	         long days=0;  
	         long dayTime=24*60*60*1000;  
	         long fromTime=from.getTime();  
	         long toTime=to.getTime();  
	         long times=Math.abs(fromTime-toTime);  
	         days=times/dayTime;  
	         return days;  
	     }  
	       
	     /** 
	      * ȡ����ʱ������Сʱ�� 
	      * @param from ��һ��ʱ�� 
	      * @param to �ڶ���ʱ�� 
	      * @return ����Сʱ�� 
	 */  
	     public static long getBetweenHours(Date from,Date to){  
	         long hours=0;  
	         long hourTime=60*60*1000;  
	         long fromTime=from.getTime();  
	         long toTime=to.getTime();  
	         long times=Math.abs(fromTime-toTime);  
	         hours=times/hourTime;  
	         return hours;  
	     }  
	       
	     /** 
	      * ȡ����ָ��ʱ���ϼӼ�days����ʱ�� 
	      * @param date ָ����ʱ�� 
	      * @param days ����,��Ϊ�ӣ���Ϊ�� 
	      * @return ��ָ��ʱ���ϼӼ�days����ʱ�� 
	 */  
	     public static Date addDays(Date date,int days){  
	         Date time=null;  
	         Calendar cal=Calendar.getInstance();  
	         cal.add(Calendar.DAY_OF_MONTH, days);  
	         time=cal.getTime();  
	         return time;  
	     }  
	       
	     /** 
	      * ȡ����ָ��ʱ���ϼӼ�months�º��ʱ�� 
	      * @param date ָ��ʱ�� 
	      * @param months ��������Ϊ�ӣ���Ϊ�� 
	      * @return ��ָ��ʱ���ϼӼ�months�º��ʱ�� 
	 */  
	     public static Date addMonths(Date date,int months){  
	         Date time=null;  
	         Calendar cal=Calendar.getInstance();  
	         cal.add(Calendar.MONTH, months);  
	         time=cal.getTime();  
	         return time;  
	     }  
	       
	     /** 
	      * ȡ����ָ��ʱ���ϼӼ�years����ʱ�� 
	      * @param date ָ��ʱ�� 
	      * @param years ��������Ϊ�ӣ���Ϊ�� 
	      * @return ��ָ��ʱ���ϼӼ�years����ʱ�� 
	 */  
	     public static Date addYears(Date date,int years){  
	         Date time=null;  
	         Calendar cal=Calendar.getInstance();  
	         cal.add(Calendar.YEAR, years);  
	         time=cal.getTime();  
	         return time;  
	     }  
	       
	       
	       
	     /** 
	      * @param args 
	 */  
	     public static void main(String[] args) {  
	         System.out.println(getFormatDateTime(new Date(),DateUtil.fullChineseFormat));  
	         System.out.println(isLeapYear(new Date()));  
	         Calendar cal=Calendar.getInstance();  
	         System.out.println(isInToday(cal.getTime()));  
	         Calendar cal2=Calendar.getInstance();  
	         cal2.set(2011, 06, 05);  
	         System.out.println(isSameDay(cal.getTime(),cal2.getTime()));  
	         System.out.println(getWeekString(new Date()));  
	         DateFormat df=new SimpleDateFormat(DateUtil.fullFormat);  
	         String fullString="2011-06-03 22:37:20";  
	         try {  
	             Date fulldate=df.parse(fullString);  
	             System.out.println(getBetweenDays(fulldate,cal.getTime()));  
	             System.out.println(getAgoTimeString(fulldate));  
	             System.out.println(isWeeks(fulldate));  
	         } catch (ParseException e) {  
	             e.printStackTrace();  
	         }  
	           
	         System.out.println(getThisWeekBeginTimeString(DateUtil.yyyyMMddChineseFormat));  
	         System.out.println(getThisWeekEndTimeString(DateUtil.yyyyMMddChineseFormat));  
	         System.out.println(addDays(new Date(),3));  
	         System.out.println(addDays(new Date(),-3));  
	         System.out.println(addMonths(new Date(),2));  
	         System.out.println(addMonths(new Date(),-2));  
	         System.out.println(addYears(new Date(),1));  
	         System.out.println(addYears(new Date(),-1));  
	           
	     }  
	
	
	
}