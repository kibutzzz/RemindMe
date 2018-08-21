package com.ifsul.remindme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifsul.remindme.database.DatabaseUtils;
import com.ifsul.remindme.R;
import com.ifsul.remindme.Task;

public class AddTaskActivity extends AppCompatActivity {

    private AppCompatEditText descricaoEditText;
    private AppCompatEditText nomeEditText;
    private AppCompatButton registerButton;
    private Toolbar toolbar;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tasksDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        descricaoEditText = findViewById(R.id.edit_text_desc);
        nomeEditText = findViewById(R.id.edit_text_nome);
        registerButton = findViewById(R.id.button_register);

        firebaseDatabase = DatabaseUtils.getFirebaseDatabase();
        tasksDatabaseReference = firebaseDatabase.getReference("tasks");


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task t = new Task(
                        nomeEditText.getText().toString(),
                        descricaoEditText.getText().toString());

                tasksDatabaseReference.push().setValue(t);
                finish();
            }
        });
    }


}
