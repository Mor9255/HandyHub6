package com.example.handyhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        Button btn = findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.name);
                EditText email = findViewById(R.id.email);

                User newUser = new User(R.drawable.image1, name.getText().toString(), email.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("user", newUser);
                setResult(1, intent);
                finish();
            }
        });
    }
}