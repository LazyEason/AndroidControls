package org.weicheche.helloworld;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class ActivityToggleButtonTest extends Activity {

	private ToggleButton toggleButton;
	private Switch switcherSwitch;
	
	public ActivityToggleButtonTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_dynamic_control);
		toggleButton = (ToggleButton)findViewById(R.id.toggle);
		switcherSwitch = (Switch)findViewById(R.id.switcher);
		
		
		final LinearLayout testLayout = (LinearLayout)findViewById(R.id.test);
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton button, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					// set the vertical layout
					testLayout.setOrientation(0);
				}else {
					testLayout.setOrientation(1);
				}
				
			}
		};
		
		toggleButton.setOnCheckedChangeListener(listener);
		switcherSwitch.setOnCheckedChangeListener(listener);
	}
	
}
