package com.example.testapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;


public class images extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        ImageView image_test = (ImageView) findViewById(R.id.image_test);

        SwipeRefreshLayout swiper = (SwipeRefreshLayout) findViewById(R.id.swiper);

        String imageUri = "https://media4.giphy.com/media/cXOc29SzcTjgI/giphy.gif";

        Glide.with(this).load(imageUri)
//          .apply(new RequestOptions().override(250, 250))
          .circleCrop()
          .into(image_test);

        swiper.setColorSchemeResources(R.color.teal_700);

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("tag", "onRefresh: true ");

                //https://wallpapercave.com/wp/wp1829975.png

                Glide.with(images.this).load("https://wallpapercave.com/wp/wp1829975.png").listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        swiper.setRefreshing(false);
                        return false;
                    }
                }).into(image_test);


            }
        });

    }
}