package com.example.player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
	MediaPlayer mediaPlayer;
	private Boolean isPlaying = false;
	private ImageButton actionButton;
	private TextView time_start,time_end;
	private  double startTime = 0;
	private  double finalTime = 0;
	private SeekBar seekBar;
	private Handler handler = new Handler();
	volatile Boolean isRunning = false;
	private Boolean isScrolling = false;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	mediaPlayer = MediaPlayer.create(this, R.raw.song);
	actionButton =  (ImageButton) findViewById(R.id.actionButton);
	seekBar = (SeekBar) findViewById(R.id.seekBar);
	time_start = (TextView) findViewById(R.id.time_start);
	time_end = (TextView) findViewById(R.id.time_end);
	
	time_start.setText("00:00");
	time_end.setText("00:00");
	finalTime = mediaPlayer.getDuration();
	seekBar.setMax((int)finalTime);
	
	
	
	actionButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(!isPlaying){
				isPlaying=!isPlaying;
				mediaPlayer.start();
				actionButton.setImageResource(android.R.drawable.ic_media_pause);
				
				startTime = mediaPlayer.getCurrentPosition();
				
				time_end.setText(String.format("%s:%s",
					timeFormat(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)),
					timeFormat(TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
						TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
				));
				
				setStartTime(startTime);
				
				isRunning=true;
				
				if(!isScrolling) seekBar.setProgress((int)startTime);
				handler.postDelayed(updateSongTime, 100);
			}
			else{
				isPlaying=!isPlaying;
				mediaPlayer.pause();
				actionButton.setImageResource(android.R.drawable.ic_media_play);
				isRunning=false;
			}
		}
	});
	
	seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			isScrolling=true;
		}
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			isScrolling=false;
			startTime = mediaPlayer.getCurrentPosition();
			
			setStartTime(startTime);
			mediaPlayer.seekTo(seekBar.getProgress());
			handler.postDelayed(updateSongTime, 100);
		}
	});
	
	}

	private String timeFormat(long time){
		if(time<10){
			return "0"+time;
		}
		return ""+time;
	}
	
	private void setStartTime(double startTime){
		time_start.setText(String.format("%s:%s",
			timeFormat(TimeUnit.MILLISECONDS.toMinutes((long) startTime)),
			timeFormat(TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
		));
	}
	
	private Runnable updateSongTime = new Runnable() {
		@Override
		public void run() {
			if(isRunning){
				startTime = mediaPlayer.getCurrentPosition();
				
				setStartTime(startTime);
				
				if(!isScrolling) seekBar.setProgress((int)startTime);
				handler.postDelayed(this, 100);
			}
		}
	};


}