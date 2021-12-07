package com.example.edu_1404_userlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView; // Объявляем переменную для элемента RecyclerView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView); // Находим элемент RecyclerView на активности и сохраняем его в переменную
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        ArrayList<String> usersName = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            usersName.add("User #"+i);
        }
        UserAdapter userAdapter = new UserAdapter(usersName);
        recyclerView.setAdapter(userAdapter);
    }

    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTextView;
        String userName;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }
        public void bind(String userName){
            itemTextView.setText(userName);
            this.userName = userName;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "Нажат элемент"+userName, Toast.LENGTH_SHORT).show();
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<String> usersName = new ArrayList<>();

        public UserAdapter(ArrayList<String> usersName) {
            this.usersName = usersName;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            String userName = usersName.get(position);
            holder.bind(userName);
        }

        @Override
        public int getItemCount() {
            return usersName.size();
        }
    }
}