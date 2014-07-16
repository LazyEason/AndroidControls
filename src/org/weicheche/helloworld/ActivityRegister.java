package org.weicheche.helloworld;

import org.json.JSONException;
import org.json.JSONObject;

import com.weicheche.android.customcontrol.ChooseDistanceBar;
import com.weicheche.android.customcontrol.InfoPannel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ActivityRegister extends Activity 
	implements OnClickListener{

	private EditText mDisplayDistance = null;
	private ChooseDistanceBar mChooseDistanceBar = null;
	private InfoPannel mInfoPannel = null;
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
        
        mInfoPannel = (InfoPannel)findViewById(R.id.info_pannel);
        mInfoPannel.setOnPannelClickListener(this);
        
        JSONObject object = new JSONObject();
        try {
			object.put("save", 1.0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mInfoPannel.setDataSource(object);
    }
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		String logString = "UnImplement:";
		switch (view.getId()){
			case R.id.btn_sliding_1:
				logString = logString + "btn_sliding_1";
				break;
			case R.id.btn_sliding_2:
				logString = logString + "btn_sliding_2";
				break;
			case R.id.btn_sliding_3:
				logString = logString + "btn_sliding_3";
				break;
			default:
				break;
		}
		mDisplayDistance.setText(logString);
	}
}
