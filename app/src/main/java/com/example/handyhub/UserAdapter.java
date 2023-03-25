package com.example.handyhub;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    List<User> Users;
    public UserAdapter(List<User> users) {
        Users = users;
    }

    public void  addUser(User user) {
        Users.add(user);
        notifyDataSetChanged();
        DataPersistencyHelper.storeData(Users);
    }

    public void  deleteUser(int position) {
        Users.remove(position);
        notifyDataSetChanged();
        DataPersistencyHelper.storeData(Users);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact,parent,false);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = Users.get(position);
        holder.barbara.setImageResource(user.getImage());
        holder.barbara_name.setText(user.getName());
        holder.barbara_email.setText(user.getEmail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                intent.putExtra("User", user);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) view.getContext(),
                       holder.barbara,
                       "Barbaratrans"
                );

                view.getContext().startActivity(intent, options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return Users.size();
    }
}
