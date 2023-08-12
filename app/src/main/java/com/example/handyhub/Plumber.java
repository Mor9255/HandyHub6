package com.example.handyhub;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Plumber extends AppCompatActivity {

    // State
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data  = result.getData();
                        newContactResult(data);
                    }
                }
        );
        DataPersistencyHelper.context = getApplicationContext();
        List<User> users = DataPersistencyHelper.loadData();
        RecyclerView recyclerView = findViewById(R.id.Rv1);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new UserAdapter(users);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        helper.attachToRecyclerView(recyclerView);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Upload images before adding a new contact
                uploadImagesToFirebaseStorage();

                // Launch the NewContactActivity
                Intent intent = new Intent(view.getContext(), NewContactActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

    private void newContactResult(Intent data) {
        Bundle b = data.getExtras();
        User user = (User) b.getSerializable("user");
        adapter.addUser(user);
    }

    private void uploadImagesToFirebaseStorage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        int[] imageResources = {R.drawable.image, R.drawable.image1, R.drawable.john, R.drawable.unnamed};

        for (int i = 0; i < imageResources.length; i++) {
            int resourceId = imageResources[i];
            Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + resourceId);
            StorageReference imageRef = storageRef.child("images/image_" + i + ".jpeg");

            UploadTask uploadTask = imageRef.putFile(imageUri);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Image uploaded successfully
            }).addOnFailureListener(e -> {
                // Handle upload failure
            });
        }
    }
}
