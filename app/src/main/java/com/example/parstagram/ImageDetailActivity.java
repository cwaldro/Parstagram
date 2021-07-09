package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.parstagram.databinding.ActivityImageDetailBinding;

import org.parceler.Parcels;

import java.util.Date;

public class ImageDetailActivity extends AppCompatActivity {

    Post post;

    private TextView tvUser;
    private TextView tvTimestamp;
    private TextView tvCaption;
    private ImageView ivPostDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityImageDetailBinding binding = ActivityImageDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        tvUser = binding.tvUser;
        tvTimestamp = binding.tvTimestamp;
        tvCaption = binding.tvCaption;
        ivPostDetail = binding.ivPostDetail;

        //post = (Post) Parcels.unwrap(getIntent().getParcelableExtra("post"));
        tvUser.setText(post.getUser().getUsername());
        Date createdAt = post.getCreatedAt();
        String timeAgo = Post.calculateTimeAgo(createdAt);
        tvTimestamp.setText(timeAgo);
        tvCaption.setText(post.getDescription());
        Glide.with(this)
                .load(post.getImage())
                .into(ivPostDetail);
    }

}