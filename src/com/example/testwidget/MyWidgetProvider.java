package com.example.testwidget;

import java.util.Timer;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

	
	private static Timer myTimer;
	private static int index = 0;
	
	private final String broadCastString = "com.wd.appWidgetUpdate";
	
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.d("YT","MyWidgetProvider ---- onDeleted");
		
		myTimer.cancel();
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		
		Log.d("YT","MyWidgetProvider ---- onEnabled");
		
		//在插件被创建的时候这里会被调用， 所以我们在这里开启一个timer 每秒执行一次  
		MyTask mMyTask = new MyTask(context);  
        myTimer = new Timer();  
        myTimer.schedule(mMyTask, 1000, 1000); 

        System.out.println("onEnabled2");  
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.d("YT","MyWidgetProvider ---- onReceive");
		
		if(intent.getAction().equals(broadCastString))  
        {  
            index++;  
              
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);  
            rv.setTextViewText(R.id.update, Integer.toString(index));  
              
            //将该界面显示到插件中  
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);  
            ComponentName componentName = new ComponentName(context,MyWidgetProvider.class);  
            appWidgetManager.updateAppWidget(componentName, rv);  
        }     
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		Log.d("YT","MyWidgetProvider ---- onUpdate");
	}

	
	class MyTask extends TimerTask  
    {  
  
        private Context mcontext = null;  
        private Intent intent = null;  
          
        public MyTask(Context context) {  
              
            //新建一个要发送的Intent  
            mcontext = context;  
            intent = new Intent();  
            intent.setAction(broadCastString);  
        }  
        
        public void run()  
        {  
            System.out.println("2");  
            //发送广播(由onReceive来接收)  
            mcontext.sendBroadcast(intent);  
        }  
          
    }  
	
	
	
}
