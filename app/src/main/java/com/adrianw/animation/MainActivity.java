package com.adrianw.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
	
	ImageView img;
	Button btn_fadein, btn_slideup, btn_zoomin, btn_rotate;
	Animation fadeIn, slideUp, zoomIn, rotate;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	img= (ImageView) findViewById(R.id.img);
	btn_fadein= (Button) findViewById(R.id.btn_fadein);
	btn_slideup= (Button) findViewById(R.id.btn_slideup);
	btn_zoomin= (Button) findViewById(R.id.btn_zoomin);
	btn_rotate= (Button) findViewById(R.id.btn_rotate);
	
	fadeIn= AnimationUtils.loadAnimation(this, R.anim.fade_in);
	slideUp= AnimationUtils.loadAnimation(this, R.anim.slideup);
	zoomIn= AnimationUtils.loadAnimation(this, R.anim.zoomin);
	rotate= AnimationUtils.loadAnimation(this, R.anim.rotate);
	
	
	btn_fadein.setOnClickListener(v->{
		img.startAnimation(fadeIn);
	});
	
	btn_slideup.setOnClickListener(v -> {
		img.startAnimation(slideUp);
	});
	
	btn_zoomin.setOnClickListener(v-> {
		img.startAnimation(zoomIn);
	});
	
	btn_rotate.setOnClickListener(v -> {
		img.startAnimation(rotate);
	});
}
}