package com.ifsul.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTaskActivity extends AppCompatActivity {

    private static AppCompatEditText descricaoEditText;
    private static AppCompatEditText nomeEditText;
    private static AppCompatButton registerButton;
    private static Toolbar toolbar;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tasksDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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
                clearFields();

            }
        });
    }

    private void clearFields(){
        nomeEditText.getText().clear();
        descricaoEditText.getText().clear();
    }
}
