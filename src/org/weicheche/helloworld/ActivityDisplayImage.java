package org.weicheche.helloworld;

import android.R.integer;
import android.R.xml;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityDisplayImage extends Activity {

	Button pluseButton;
	Button minusButton;
	Button nextButton;
	
	ImageView displayWholeImageView;
	ImageView displayLocalImageView;
	
	
	// data
	int[] images = new int[]{
			R.drawable.pic_1, 
//			R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
//			R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8,
//			R.drawable.pic_9, R.drawable.pic_10, R.drawable.pic_11, R.drawable.pic_12,
//			R.drawable.pic_13,R.drawable.pic_14
	};
	int currentImg = 0;
	
	public ActivityDisplayImage() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_image_viewer);
		
		pluseButton = (Button)findViewById(R.id.plus);
		minusButton = (Button)findViewById(R.id.minus);
		nextButton = (Button)findViewById(R.id.next);
		
		displayWholeImageView = (ImageView)findViewById(R.id.display_whole_image);
		displayLocalImageView = (ImageView)findViewById(R.id.display_local_image);
		
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				displayWholeImageView.setImageResource(images[(++currentImg)%images.length]);
			}
		});
		
		displayWholeImageView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				BitmapDrawable bitmapDrawable = (BitmapDrawable)displayWholeImageView.getDrawable();
				Bitmap bitmap = bitmapDrawable.getBitmap();
				
				double scale = bitmap.getWidth() / 320;
				int x = (int)(event.getX()*scale);
				int y = (int)(event.getY()*scale);
				if(x+180>bitmap.getWidth()){
					x = bitmap.getWidth()-180;
				}
				if((y+180)>bitmap.getHeight()){
					y = bitmap.getHeight()-180;
				}
				
				displayLocalImageView.setImageBitmap(Bitmap.createBitmap(bitmap, x, y, 180, 180));
				
				return false;
			}
		});
	}

}
