package com.example.parstagram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parstagram.fragments.FeedFragment;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    //constructor
    public PostsAdapter(Context context , List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvTimeAgo;
        private TextView tvCapUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimeAgo = itemView.findViewById((R.id.tvTimeAgo));
            tvCapUser = itemView.findViewById(R.id.tvCapUser);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText("@" + post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            Date createdAt = post.getCreatedAt();
            String timeAgo = Post.calculateTimeAgo(createdAt);
            tvTimeAgo.setText(timeAgo);
            tvCapUser.setText(post.getUser().getUsername());
        }
        public void onClick(View v) {
            //get item position
            int pos = getAdapterPosition();
            //make sure pos exists in view
            if(pos != RecyclerView.NO_POSITION) {
                //step 1: get movie at position pos
                Post post = posts.get(pos);
                //step 2: create intent for new activity
                Intent intent = new Intent(context, ImageDetailActivity.class);
                //serialize pass data to new intent
                intent.putExtra("Caption", post.getDescription());
                Date createdAt = post.getCreatedAt();
                String timeAgo = Post.calculateTimeAgo(createdAt);
                intent.putExtra("Timestamp", timeAgo);
                intent.putExtra("User", post.getUser().getUsername());
                //show activity
                context.startActivity(intent);
            }
        }
    }
}
