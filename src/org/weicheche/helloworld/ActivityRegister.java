package org.weicheche.helloworld;

import android.app.Activity;
import android.os.Bundle;

public class ActivityRegister extends Activity {

	public ActivityRegister() {
		// TODO Auto-generated constructor stub
	}
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
    }
}
