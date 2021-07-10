package com.example.parstagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.parstagram.databinding.ActivityMainBinding;
import com.example.parstagram.fragments.CaptureFragment;
import com.example.parstagram.fragments.FeedFragment;
import com.example.parstagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import static com.example.parstagram.R.id.*;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private BottomNavigationView navBar;
    private Button btnLogout;
    public Toolbar toolbar;
//    ActionBar appBar = getSupportActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        navBar = binding.navBar;
        btnLogout = binding.btnLogout;
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                //keeps user from going back to login activity
                finish();
            }
        });
        final FragmentManager fragmentManager = getSupportFragmentManager();
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case action_feed:
                        fragment = new FeedFragment();
                        item.setIcon(R.drawable.instagram_home_filled_24);
                        break;
                    case action_capture:
                        fragment = new CaptureFragment();
                        item.setIcon(R.drawable.instagram_new_post_filled_24);
                        break;
                    case action_profile:
                    default:
                        fragment = new ProfileFragment();
                        item.setIcon(R.drawable.instagram_user_filled_24);
                        break;
                }
                fragmentManager.beginTransaction().replace(fragment_placeholder, fragment).commit();
                return true;
            }
        });

        //set default selection for navBar to home
        navBar.setSelectedItemId(action_feed);

    }
}
