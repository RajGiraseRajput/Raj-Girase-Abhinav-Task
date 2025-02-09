package com.example.rajgiraseabhinavtask.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rajgiraseabhinavtask.R;
import com.example.rajgiraseabhinavtask.adapter.MemberRecyclerViewAdapter;
import com.example.rajgiraseabhinavtask.database.MemberDatabase;
import com.example.rajgiraseabhinavtask.databinding.ActivityMainBinding;
import com.example.rajgiraseabhinavtask.model.MemberModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MemberRecyclerViewAdapter adapter;
    private MemberDatabase database;
    private List<MemberModel> allMemberList;
    private static final int REQ_CODE = 111;
    private int listSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        binding.toolbarTitleTv.setText(getString(R.string.registration_details));

        database = new MemberDatabase(this);

        allMemberList = database.getAllMembers();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MemberRecyclerViewAdapter(this,allMemberList);
        binding.recyclerView.setAdapter(adapter);

        listSize = adapter.getItemCount();

        binding.memberCountTv.setText(listSize+"");
        Log.e("listSize", "onCreate: "+listSize );

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.addMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, MemberRegistrationActivity.class),REQ_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQ_CODE){

            allMemberList = database.getAllMembers();
            adapter.updateMember(allMemberList);
            listSize = adapter.getItemCount();
            Log.e("listSize", "onActivityResult: "+listSize);
            binding.memberCountTv.setText(listSize+"");

        }
    }
}