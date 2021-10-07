package com.progress.retrofit_example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRequest {
	@GET("/posts")
	Call<List<Posts>> getPosts();
	
	@GET("/posts/{id}")
	Call<Posts> getPostDetail(@Path("id") Integer id);
}
