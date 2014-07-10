package org.weicheche.helloworld;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

public class CalculateActivity extends Activity {

	GridLayout gridLayout = null;
	
	final String[] charactorSet = new String[]{
			"7", "8","9","/",
			"4", "5","6","x",
			"1", "2","3","-",
			".", "0","=","+"};
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.calc);
    	// Get the grid layout for adding the characters
    	gridLayout = (GridLayout)findViewById(R.id.cal_root);
    	for(int i = 0; i < charactorSet.length; i++){
    		Button charButton = new Button(this);
    		charButton.setText(charactorSet[i]);
    		charButton.setTextSize(40);
    		GridLayout.Spec rowSpec = GridLayout.spec(i/4+2);
    		GridLayout.Spec colSpec = GridLayout.spec(i%4);
    		GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
    		params.setGravity(Gravity.FILL);
    		
    		gridLayout.addView(charButton, params);
    	}
    }

}
