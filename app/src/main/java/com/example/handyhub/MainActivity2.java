package com.example.handyhub;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Get the User object from the intent
        User user = (User) getIntent().getSerializableExtra("User");

        // Get references to the ImageView and TextViews in the layout
        ImageView imageView = findViewById(R.id.imageView);
        TextView nameTextView = findViewById(R.id.name);
        TextView emailTextView = findViewById(R.id.email);

        // Set the values for the ImageView and TextViews using the User object
        imageView.setImageResource(user.getImage());
        nameTextView.setText(user.getName());
        emailTextView.setText(user.getEmail());
    }
}
