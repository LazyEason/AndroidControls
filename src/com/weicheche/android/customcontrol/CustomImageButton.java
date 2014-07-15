package com.weicheche.android.customcontrol;

import org.weicheche.helloworld.R;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomImageButton extends LinearLayout {

	// UI element
	private ImageView imageView;
	private TextView textView;
	private CharSequence text;
	private Drawable drawable;
	private float textSize;
	
	public CustomImageButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageButton);
		
		// 根据text在custom image button中的偏移获取text内容，并判断赋值
		text = typedArray.getText(R.styleable.CustomImageButton_text);
		if(null == text){
			text = "";
		}
		
		// 获取xml中索引的图片资源，由于我们只允许从Resource中获取，故而当返回为null时，直接报错
		drawable = typedArray.getDrawable(R.styleable.CustomImageButton_src);
		if(null == drawable){
			throw new RuntimeException("图片资源不存在");
		}
		
		// 获取文字大小，默认值为12
		textSize = typedArray.getDimension(R.styleable.CustomImageButton_textSize, 12);
		
		// 调用Layout Inflater service将自定义的布局文件加载进来，并将本控件设置为root;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.custom_image_button, this);
		
		imageView = (ImageView)findViewById(R.id.icon_part);
		imageView.setImageDrawable(drawable);
		
		textView = (TextView)findViewById(R.id.text_part);
		textView.setText(text);
		textView.setTextSize(textSize);
		
		if(null == text || text.equals("")){
			textView.setVisibility(View.GONE);
		}
		
		typedArray.recycle();
	}

	public CustomImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	
	// 
	public void setImageResource(int resId){
		imageView.setImageResource(resId);
	}
	
	public void setTextViewText(String text){
		textView.setText(text);
	}
}
