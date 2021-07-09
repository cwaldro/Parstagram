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
import com.parse.ParseFile;

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

        //receive passed in data
        String caption = getIntent().getStringExtra("Caption");
        String Timestamp = getIntent().getStringExtra("Timestamp");
        String username = getIntent().getStringExtra("User");
        tvCaption.setText(caption);
        tvTimestamp.setText(Timestamp);
        tvUser.setText("@"+ username);

    }

}