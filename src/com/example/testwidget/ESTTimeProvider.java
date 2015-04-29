package com.example.testwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;

public class ESTTimeProvider extends AppWidgetProvider {

	 @Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.d("YT","ESTTimeProvider --- onDeleted");
		
	    context.stopService(new Intent(context,UpdateService.class));
	}





	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.d("YT","ESTTimeProvider --- onEnabled");
	}





	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.d("YT","ESTTimeProvider --- onReceive");
	}



	/** Called when the activity is first created. */
    @Override
    public void onUpdate(Context context,
            AppWidgetManager appWidgetManager,int[] appWidgetIds )
    {
    	Log.d("YT","ESTTimeProvider --- onUpdate");
        context.startService(new Intent(context,UpdateService.class));        
    }
    
    
    
    
    
  //Service¿‡
    public static class UpdateService extends Service {
        @Override
        public void onStart(Intent intent,int startId){
        	Log.d("YT","ESTTimeProvider --- UpdateService  -- onStart");
            Time estTime = new Time("EST");
            estTime.setToNow();
            RemoteViews updateViews = 
                new RemoteViews(getPackageName(),
                        R.layout.widget_digitalclock);
            updateViews.setTextViewText(R.id.time, estTime.format("%H:%M:%S"));
            
            ComponentName thisWidget = new ComponentName(this,ESTTimeProvider.class);
            
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            manager.updateAppWidget(thisWidget, updateViews);
        }

        @Override
        public IBinder onBind(Intent intent) {
            // TODO Auto-generated method stub
            return null;
        }

    }
	
}
