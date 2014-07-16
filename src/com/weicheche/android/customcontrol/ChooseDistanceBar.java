package com.weicheche.android.customcontrol;


import org.weicheche.helloworld.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @ClassName: ChooseDistanceBar 
 * @Description: TODO(自定义选择距离控件) 
 * @author xuyong yxy8023@gmail.com   
 * @date  2014年7月16日  上午11:10:34
 */
public class ChooseDistanceBar extends LinearLayout {

	// Listener
	/*
	 * 对外暴露这个接口传递事件响应
	 */
	public static abstract interface DistanceListener{
		public abstract void onDistanceChanged(int distance);
	}
	private DistanceListener mDistanceListener = new DistanceListener() {
		
		@Override
		public void onDistanceChanged(int distance) {
			// TODO Auto-generated method stub
			Log.i("DistanceListener", distance + "No implement");
		}
	};
	
	public void setDistanceListener(DistanceListener distanceListener){
		mDistanceListener = distanceListener;
	}
	
	
	
	// Elements
	private static final int levelCount = 5;
	private ImageView[] mDotViewList = null;
	private ImageView[] mLineViewList = null;
	private TextView[] mTextViewList = null;
	private GestureDetector mGestureDetector = null;
	
	// data
	private int mDistance = 3000;
	private int mLevel = 1;
	private int mPendingLevel = 1;
	private int mUnitWidth = 200;
	
	
	public ChooseDistanceBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
		initView(context);
	}

	public ChooseDistanceBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
		initView(context);
	}

	@SuppressLint("NewApi") public ChooseDistanceBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
		initView(context);
	}

	private void init(){
		int userPreferedDistance = 100;
		mDistance = userPreferedDistance;
		mLevel = getDistanceLevel(mDistance);
	}
	/**
	 * @Title: initView 
	 * @Description: TODO(加载自定义组件并监听相关事件) 
	 * @param context
	 * @throws 
	 * @date  2014年7月16日  上午11:14:56 
	 * Xuyong
	 */
	private void initView(Context context){
		// 调用Layout Inflater service将自定义的布局文件加载进来，并将本控件设置为root;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.choose_distance_bar, this);
		
		mDotViewList = new ImageView[levelCount];
		mDotViewList[0] = (ImageView) findViewById(R.id.ch_di_ba_fi_dot1);
		mDotViewList[1] = (ImageView) findViewById(R.id.ch_di_ba_fi_dot2);
		mDotViewList[2] = (ImageView) findViewById(R.id.ch_di_ba_fi_dot3);
		mDotViewList[3] = (ImageView) findViewById(R.id.ch_di_ba_fi_dot4);
		mDotViewList[4] = (ImageView) findViewById(R.id.ch_di_ba_fi_dot5);
		
		mLineViewList = new ImageView[levelCount-1];
		mLineViewList[0] = (ImageView) findViewById(R.id.ch_di_ba_fi_li1);
		mLineViewList[1] = (ImageView) findViewById(R.id.ch_di_ba_fi_li2);
		mLineViewList[2] = (ImageView) findViewById(R.id.ch_di_ba_fi_li3);
		mLineViewList[3] = (ImageView) findViewById(R.id.ch_di_ba_fi_li4);
		
		mTextViewList = new TextView[levelCount];
		mTextViewList[0] = (TextView) findViewById(R.id.ch_di_ba_tv1);
		mTextViewList[1] = (TextView) findViewById(R.id.ch_di_ba_tv2);
		mTextViewList[2] = (TextView) findViewById(R.id.ch_di_ba_tv3);
		mTextViewList[3] = (TextView) findViewById(R.id.ch_di_ba_tv4);
		mTextViewList[4] = (TextView) findViewById(R.id.ch_di_ba_tv5);
		
		// 设置距离条的初始显示
		ctrlBarBg(mLevel);
		
		// set listener
		mGestureDetector = new GestureDetector(scrollDistanceListener);
		setOnTouchListener(onTouchListener);
		
		getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				// TODO 再控件加载后就是其宽度
				mUnitWidth = getWidth() / levelCount;
			}
		});
	}
	
	public void setDistanceBG(int distance){
		mDistance = distance;
		mLevel = getDistanceLevel(mDistance);
		ctrlBarBg(mLevel);
	}
	
	/**
	 * 
	 * @Title: getDistanceLevel 
	 * @Description: TODO(根据距离获取级别,距离是服务器和用户认知的物理距离) 
	 * @param distance
	 * @return level
	 * @throws 
	 * @date  2014年7月16日  上午11:44:06 
	 * Xuyong
	 */
	private int getDistanceLevel(int distance) {
		int level = 5;
		if (distance < 1001) {
			level = 1;
		} else if (distance < 3001) {
			level = 2;
		} else if (distance < 5001) {
			level = 3;
		} else if (distance < 10001) {
			level = 4;
		} else {
			level = 5;
		}
		return level;
	}
	
	private int getDistanceFromLevel(int level) {
		int distance = 3000;
		switch (level) {
		case 1:
			distance = 1000;
			break;
		case 2:
			distance = 3000;
			break;
		case 3:
			distance = 5000;
			break;
		case 4:
			distance = 10000;
			break;
		case 5:
			distance = 30000;
			break;
		default:
			distance = 3000;
			break;
		}
		return distance;
	}
	
	/**
	 * @Title: ctrlBarBg 
	 * @Description: TODO(根据level控制距离条的背景刷新) 
	 * @param level
	 * @throws 
	 * @date  2014年7月16日  下午12:26:58 
	 * Xuyong
	 */
	private void ctrlBarBg(int level){
		if(level < 1 || level > levelCount){
			return;
		}
		
		// before the selected level
		for (int arrivedIdx = 1; arrivedIdx < level; arrivedIdx++){
			mDotViewList[arrivedIdx-1].setBackgroundResource(R.drawable.ic_dot_arrived);
			mLineViewList[arrivedIdx-1].setBackgroundResource(R.drawable.ic_line_past);
			mTextViewList[arrivedIdx-1].setTextColor(getResources().getColor(R.color.gray));
		}
		
		// the selected level
		mDotViewList[level-1].setBackgroundResource(R.drawable.ic_dot_now);
		mTextViewList[level-1].setTextColor(getResources().getColor(R.color.green_light));
		
		// after the selected level
		for (int futureIdx = level+1; futureIdx <= levelCount; futureIdx++){
			mDotViewList[futureIdx-1].setBackgroundResource(R.drawable.ic_dot_future);
			mLineViewList[futureIdx-2].setBackgroundResource(R.drawable.ic_line_future);
			mTextViewList[futureIdx-1].setTextColor(getResources().getColor(R.color.gray));
		}
	}
	
	/*
	 * 事件响应
	 */
	GestureDetector.OnGestureListener scrollDistanceListener = new GestureDetector.OnGestureListener() {
		
		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
				float distanceY) {
			// TODO Auto-generated method stub
			int xOffset = (int)e2.getX();
			mPendingLevel = ((int)xOffset / mUnitWidth) +1;
			ctrlBarBg(mPendingLevel);
			return false;
		}
		
		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			int xOffset = (int)e.getX();
			mPendingLevel = ((int)xOffset / mUnitWidth) +1;
			ctrlBarBg(mPendingLevel);
			return false;
		}
	};
	
	View.OnTouchListener onTouchListener = new View.OnTouchListener(){
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			mGestureDetector.onTouchEvent(event);
			if( event.getAction() == MotionEvent.ACTION_UP 
					&& mPendingLevel != mLevel) {
				mLevel = mPendingLevel;
				mDistance = getDistanceFromLevel(mLevel);
				mDistanceListener.onDistanceChanged(mDistance);
			}
			return true;
		}
	};
}

