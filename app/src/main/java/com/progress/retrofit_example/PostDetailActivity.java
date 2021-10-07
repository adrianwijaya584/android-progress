package com.progress.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostDetailActivity extends AppCompatActivity {
	TextView post_title, post_body;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_post_detail);
	
	post_title= (TextView) findViewById(R.id.post_title);
	post_body= (TextView) findViewById(R.id.post_body);
	
	Bundle b= getIntent().getExtras();
	Integer id= b.getInt("id");
	
	Retrofit retrofit= new Retrofit.Builder()
	.baseUrl("https://jsonplaceholder.typicode.com/")
	.addConverterFactory(GsonConverterFactory.create())
	.build();
	
	ApiRequest request= retrofit.create(ApiRequest.class);
	
	request.getPostDetail(id).enqueue(new Callback<Posts>() {
		@Override
		public void onResponse(Call<Posts> call, Response<Posts> response) {
			if (!response.isSuccessful()){
				Log.d("PostDetail", response.code()+"");
				return;
			}
			
			Posts body= response.body();
			
			post_title.setText(body.getTitle());
			post_body.setText(body.getText());
		}
		
		@Override
		public void onFailure(Call<Posts> call, Throwable t) {
			Log.d("PostDetail", t.getLocalizedMessage()+"");
		}
	});
}
}