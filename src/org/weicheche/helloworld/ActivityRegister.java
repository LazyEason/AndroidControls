package org.weicheche.helloworld;

import com.weicheche.android.customcontrol.ChooseDistanceBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ActivityRegister extends Activity {

	private EditText mDisplayDistance = null;
	private ChooseDistanceBar mChooseDistanceBar = null;
	public ActivityRegister() {
		// TODO Auto-generated constructor stub
	}
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_control);  //
        
        mDisplayDistance = (EditText)findViewById(R.id.display_distance);
        mChooseDistanceBar = (ChooseDistanceBar)findViewById(R.id.control_choose_distance_bar);
        
        mChooseDistanceBar.setDistanceListener(new com.weicheche.android.customcontrol.ChooseDistanceBar.DistanceListener() {
			
			@Override
			public void onDistanceChanged(int distance) {
				// TODO Auto-generated method stub
				mDisplayDistance.setText(Float.toString(distance));
			}
		});
    }
}
