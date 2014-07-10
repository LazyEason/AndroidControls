package org.weicheche.helloworld;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class HelloAndroid extends Activity
{
    /** Called when the activity is first created. */
	private int curColorIdx = 0;
	final int[] txIdx = new int[]{R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6};
	final int[] colors = new int[]{R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6};
	private TextView[] textViews = new TextView[txIdx.length];
	
	int activityFlag  = 0x123;
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			// to validate the message come from this activity
			if(msg.what == activityFlag){
				for(int i = 0; i < txIdx.length; i++){
					textViews[i].setBackgroundResource(colors[(i+curColorIdx)%txIdx.length]);
				}
				curColorIdx++;
			}
			super.handleMessage(msg);
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        for ( int i = 0; i < txIdx.length; i++){
        	textViews[i] = (TextView)findViewById(txIdx[i]); 
        }
        
        new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(activityFlag);
			}
		}, 1000, 500);
    }
}
