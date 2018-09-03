package com.ifsul.remindme.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifsul.remindme.R;
import com.ifsul.remindme.Task;
import com.ifsul.remindme.database.DatabaseUtils;

public class TaskDetailActivity extends AppCompatActivity {
    private final static String TAG = "LOG_TAG";
    private FirebaseDatabase database;
    private DatabaseReference taskReference;
    private AppCompatTextView nomeTarefa;
    private AppCompatTextView descricaoTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        database = DatabaseUtils.getFirebaseDatabase();

        nomeTarefa = findViewById(R.id.nome_da_tarefa);
        descricaoTarefa = findViewById(R.id.descricao_da_tarefa);

        String key = getIntent().getStringExtra("key");
        taskReference = database.getReference("tasks").child(key);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomeTarefa.setText(dataSnapshot.getValue(Task.class).getNome());
                descricaoTarefa.setText(dataSnapshot.getValue(Task.class).getDesricao());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        taskReference.addListenerForSingleValueEvent(eventListener);
        

    }
}
