package com.weicheche.android.customcontrol;
import org.weicheche.helloworld.R;

import java.text.DecimalFormat;
import org.json.JSONObject;
import com.weicheche.android.utils.SafeJSONObject;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class InfoPannel extends LinearLayout {

	// Elements
	private SlidingDrawer slidingDrawer;
	private ImageView img_sliding_arrow;
	private Button btn_sliding_1, btn_sliding_2, btn_sliding_3;
	private TextView txt_sliding_1_stname, txt_sliding_1_distance, txt_sliding_1_price, txt_sliding_1_save;
	private TextView txt_sliding_2, txt_sliding_3_weather,txt_sliding_3_wash;
	
	
	public InfoPannel(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
		initView(context);
	}

	public InfoPannel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
		initView(context);
	}

	public InfoPannel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	private void init(){
		
	}
	
	/**
	 * 
	 * @Title: initView 
	 * @Description: TODO(加载自定义信息呈现组件，并监听top layout 和 middle layout的点击事件) 
	 * @param context
	 * @throws 
	 * @date  2014年7月16日  下午5:13:12 
	 * Xuyong
	 */
	@SuppressWarnings("deprecation")
	private void initView(Context context){
		// 调用Layout Inflater service将自定义的布局文件加载进来，并将本控件设置为root;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.info_pannel, this);
		
		slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
		img_sliding_arrow = (ImageView) findViewById(R.id.img_sliding_arrow);
		slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				img_sliding_arrow.setBackgroundResource(R.drawable.btn_sliding_drawer_up);
			}
		});
		slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				img_sliding_arrow.setBackgroundResource(R.drawable.btn_sliding_drawer_down);
			}
		});
		
		btn_sliding_1 = (Button) findViewById(R.id.btn_sliding_1);
		btn_sliding_2 = (Button) findViewById(R.id.btn_sliding_2);
		btn_sliding_3 = (Button) findViewById(R.id.btn_sliding_3);

		txt_sliding_1_stname = (TextView) findViewById(R.id.txt_sliding_1_stname);
		txt_sliding_1_distance = (TextView) findViewById(R.id.txt_sliding_1_distance);
		txt_sliding_1_price = (TextView) findViewById(R.id.txt_sliding_1_price);
		txt_sliding_1_save = (TextView) findViewById(R.id.txt_sliding_1_save);
		txt_sliding_2 = (TextView) findViewById(R.id.txt_sliding_2);
		txt_sliding_3_weather = (TextView) findViewById(R.id.txt_sliding_3_weather);
		txt_sliding_3_wash = (TextView) findViewById(R.id.txt_sliding_3_wash);
	}

	public void setOnPannelClickListener(OnClickListener onClickListener){
		clickListener = onClickListener;
		btn_sliding_1.setOnClickListener(clickListener);
		btn_sliding_2.setOnClickListener(clickListener);
		btn_sliding_3.setOnClickListener(clickListener);
	}
	
	public void setDataSource(JSONObject object){
		
		txt_sliding_1_stname.setText(SafeJSONObject.getString(object, "name", "中石化"));
		txt_sliding_1_distance.setText(SafeJSONObject.getString(object, "distance", "1000米"));
		
		
		String oilTypeString = SafeJSONObject.getString(object, "oilType", "#error");
		double price = SafeJSONObject.getDouble(object, "price", 0.0);
		txt_sliding_1_price.setText(oilTypeString + " ￥" + new DecimalFormat("##0.00").format(price));
		
		double save = SafeJSONObject.getDouble(object, "save", -1.0);
		if (save>0) {
			txt_sliding_1_save.setVisibility(View.VISIBLE);
			txt_sliding_1_save.setText("省" + new DecimalFormat("##0.00").format(save) + "元");
		} else {
			txt_sliding_1_save.setVisibility(View.GONE);
		}
		txt_sliding_2.setText("更多超值推荐，查看详细信息请猛击此处");
		txt_sliding_3_weather.setText(SafeJSONObject.getString(object, "weather", "rain"));
		txt_sliding_3_wash.setText(SafeJSONObject.getString(object, "wash_index", ""));
		
	}
	
	// Listener events, will be override by the out class
	private OnClickListener clickListener =  new OnClickListener() {
		
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
			Log.i("Info Pannel", logString);
		}
	};
}
