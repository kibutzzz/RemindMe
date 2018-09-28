package com.ifsul.remindme.activities;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifsul.remindme.R;
import com.ifsul.remindme.Task;
import com.ifsul.remindme.database.DatabaseUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaskDetailActivity extends AppCompatActivity {
    private final static String TAG = "LOG_TAG";
    private FirebaseDatabase database;
    private DatabaseReference taskReference;
    private AppCompatEditText nomeTarefa;
    private AppCompatEditText descricaoTarefa;
    private AppCompatEditText limiteTarefa;
    private AppCompatButton atualizarButton;
    private AppCompatButton concluirButton;
    private Toolbar toolbar;

    private GregorianCalendar currentDate;
    private int day, month, year;
    private String valorInicialNome;
    private String valorInicialDescricao;
    private String valorInicialLimite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        database = DatabaseUtils.getFirebaseDatabase();

        nomeTarefa = findViewById(R.id.nome_da_tarefa);
        descricaoTarefa = findViewById(R.id.descricao_da_tarefa);
        limiteTarefa = findViewById(R.id.limite_da_tarefa);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }


        String key = getIntent().getStringExtra("key");
        taskReference = database.getReference("tasks")
                .child(MainActivity.usuario.getUid())
                .child(key);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomeTarefa.setText(dataSnapshot.getValue(Task.class).getNome());
                descricaoTarefa.setText(dataSnapshot.getValue(Task.class).getDesricao());
                limiteTarefa.setText(dataSnapshot.getValue(Task.class).getLimite());
                valorInicialNome  = nomeTarefa.getText().toString();
                valorInicialDescricao  = descricaoTarefa.getText().toString();
                valorInicialLimite  = limiteTarefa.getText().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), "Erro ao buscar registro", Toast.LENGTH_LONG).show();
                finish();
            }
        };

        taskReference.addListenerForSingleValueEvent(eventListener);
        setDatePicker();

        atualizarButton = findViewById(R.id.atualizar_button);
        atualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anyFieldChanged()){
                    taskReference.setValue(new Task(
                            nomeTarefa.getText().toString(),
                            descricaoTarefa.getText().toString(),
                            limiteTarefa.getText().toString()));
                }
            }
        });

        concluirButton = findViewById(R.id.concluir_button);
        concluirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskReference.removeValue();
                finish();
            }
        });

    }

    private boolean anyFieldChanged() {

        return nomeTarefa.getText().toString().equals(valorInicialNome)
                || descricaoTarefa.getText().toString().equals(valorInicialDescricao)
                || limiteTarefa.getText().toString().equals(valorInicialLimite);

    }

    private void setDatePicker(){

        //pega a data de amanhã
        currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DATE, 1);

        day = currentDate.get(GregorianCalendar.DAY_OF_MONTH);
        month = currentDate.get(GregorianCalendar.MONTH);
        year = currentDate.get(GregorianCalendar.YEAR);
        limiteTarefa.setText(day + "/" + (month + 1) + "/" + year);

        limiteTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(TaskDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        day = d;
                        month = m + 1;
                        year = y;
                        limiteTarefa.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
