package com.progress.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
	ListView posts_list;
	ArrayAdapter<String> adapter;
	List<Posts> posts;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	posts_list= (ListView) findViewById(R.id.posts_list);
	
	Retrofit retrofit= new Retrofit.Builder()
		.baseUrl("https://jsonplaceholder.typicode.com/")
		.addConverterFactory(GsonConverterFactory.create())
		.build();
	
	ApiRequest request= retrofit.create(ApiRequest.class);
	
	request.getPosts().enqueue(new Callback<List<Posts>>() {
		@Override
		public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
			if (!response.isSuccessful()) {
				Log.d("mainActivity", response.code()+"");
				return;
			}
			
			posts= response.body();
			List<String> postsTitle= new ArrayList<String>();
			
			for (Posts post: posts){
				postsTitle.add(post.getTitle());
			}
			
			adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, postsTitle);
			
			posts_list.setAdapter(adapter);
		}
		
		@Override
		public void onFailure(Call<List<Posts>> call, Throwable t) {
			Log.d("mainActivity", t.getLocalizedMessage());
		}
	});
	
	posts_list.setOnItemClickListener((parent, view, position, id) -> {
		Intent i= new Intent(getApplicationContext(), PostDetailActivity.class);
		i.putExtra("id", posts.get(position).getId());
		startActivity(i);
	});
}
}