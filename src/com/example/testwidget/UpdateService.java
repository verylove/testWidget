package com.example.testwidget;

import java.util.Calendar;
import java.util.Date;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.widget.RemoteViews;

public class UpdateService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	 @Override
	    public void onStart(Intent intent, int startId) 
	    {
	        super.onStart(intent, startId);
	        UpdateWidget(this);
	    }
	    private void UpdateWidget(Context context) 
	    {    
	        //不用Calendar，Time对cpu负荷较小
	        Time time = new Time();
	        time.setToNow();
	        int hour = time.hour;
	        int min = time.minute;
	        int second = time.second;
	        int year = time.year;
	        int month = time.month+1;
	        int day = time.monthDay;
	        String strTime = String.format("%02d:%02d:%02d %04d-%02d-%02d", hour, min, second,year,month,day);
	        RemoteViews updateView = new RemoteViews(context.getPackageName(),
	                R.layout.widget_time);

	        //时间图像更新
	        String packageString="com.example.testwidget";
	        //String timePic="time";
	        String timePic="date";
	        int hourHbit = hour/10;
	        updateView.setImageViewResource(R.id.hourHPic, getResources().getIdentifier(timePic+hourHbit, "drawable", packageString));
	        int hourLbit = hour%10;
	        updateView.setImageViewResource(R.id.hourLPic, getResources().getIdentifier(timePic+hourLbit, "drawable", packageString));
	        int minHbit = min/10;
	        updateView.setImageViewResource(R.id.MinuteHPic, getResources().getIdentifier(timePic+minHbit, "drawable", packageString));
	        int minLbit = min%10;
	        updateView.setImageViewResource(R.id.MinuteLPic, getResources().getIdentifier(timePic+minLbit, "drawable", packageString));

	        //星期几
	        updateView.setTextViewText(R.id.weekInfo, getWeekString(time.weekDay+1));

	        //日期更新，根据日期，计算使用的图片
	        String datePic="date";
	        int year1bit = year/1000;
	        updateView.setImageViewResource(R.id.Year1BitPic, getResources().getIdentifier(datePic+year1bit, "drawable", packageString));
	        int year2bit = (year%1000)/100;
	        updateView.setImageViewResource(R.id.Year2BitPic, getResources().getIdentifier(datePic+year2bit, "drawable", packageString));
	        int year3bit = (year%100)/10;
	        updateView.setImageViewResource(R.id.Year3BitPic, getResources().getIdentifier(datePic+year3bit, "drawable", packageString));
	        int year4bit = year%10;
	        updateView.setImageViewResource(R.id.Year4BitPic, getResources().getIdentifier(datePic+year4bit, "drawable", packageString));
	        //月
	        int mouth1bit = month/10;
	        updateView.setImageViewResource(R.id.mouth1BitPic, getResources().getIdentifier(datePic+mouth1bit, "drawable", packageString));
	        int mouth2bit = month%10;
	        updateView.setImageViewResource(R.id.mouth2BitPic, getResources().getIdentifier(datePic+mouth2bit, "drawable", packageString));
	        //日
	        int day1bit = day/10;
	        updateView.setImageViewResource(R.id.day1BitPic, getResources().getIdentifier(datePic+day1bit, "drawable", packageString));
	        int day2bit = day%10;
	        updateView.setImageViewResource(R.id.day2BitPic, getResources().getIdentifier(datePic+day2bit, "drawable", packageString));

	        //点击widget，启动日历
	        Intent launchIntent = new Intent();
	        launchIntent.setComponent(new ComponentName("com.example.testwidget",
	                "com.example.testwidget.MainActivity"));
	        launchIntent.setAction(Intent.ACTION_MAIN);
	        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
	                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
	        PendingIntent intentAction = PendingIntent.getActivity(context, 0,
	                launchIntent, 0);
	        updateView.setOnClickPendingIntent(R.id.SmallBase, intentAction);        
	        AppWidgetManager awg = AppWidgetManager.getInstance(context);
	        awg.updateAppWidget(new ComponentName(context, TimeWidgetProvider.class),updateView);
	    }
	
	    
	    public String getWeekString(int i){
	    	
	    	String [] WEEKS={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"}; 

	    	return WEEKS[i];
		    
	    }
	 
	
}
