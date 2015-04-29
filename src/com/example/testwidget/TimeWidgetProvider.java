package com.example.testwidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

public class TimeWidgetProvider extends AppWidgetProvider {

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.d("YT","TimeWidget ---- onDeleted");
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.d("YT","TimeWidget ---- onEnabled");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.d("YT","TimeWidget ---- onReceive");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.d("YT","TimeWidget ---- onUpdate");
		
		
		//context.startService(new Intent(context,UpdateService.class));   
		
		Time time = new Time();
	    time.setToNow();
        //使用Service更新时间
	    Intent intent = new Intent(context, UpdateService.class);
	    PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        //使用Alarm定时更新界面数据
	    AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	    alarm.setRepeating(AlarmManager.RTC, time.toMillis(true), 60*1000, pendingIntent);
		    
	}

}
