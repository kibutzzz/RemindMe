package com.ifsul.remindme.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifsul.remindme.R;
import com.ifsul.remindme.Task;
import com.ifsul.remindme.database.DatabaseUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddTaskActivity extends AppCompatActivity {

    private AppCompatEditText descricaoEditText;
    private AppCompatEditText nomeEditText;
    private AppCompatEditText limiteEditText;
    private AppCompatButton registerButton;
    private Toolbar toolbar;
    private GregorianCalendar currentDate;
    private int day, month, year;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tasksDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        descricaoEditText = findViewById(R.id.edit_text_desc);
        nomeEditText = findViewById(R.id.edit_text_nome);
        limiteEditText = findViewById(R.id.edit_text_limite);
        registerButton = findViewById(R.id.button_register);

        firebaseDatabase = DatabaseUtils.getFirebaseDatabase();
        tasksDatabaseReference = firebaseDatabase.getReference("tasks").child(MainActivity.usuario.getUid());

        setDatePicker();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task t = new Task(
                        nomeEditText.getText().toString(),
                        descricaoEditText.getText().toString(),
                        limiteEditText.getText().toString());

                tasksDatabaseReference.push().setValue(t);
                finish();
            }
        });
    }


    private void setDatePicker() {

        //pega a data de amanhã
        currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DATE, 1);

        day = currentDate.get(GregorianCalendar.DAY_OF_MONTH);
        month = currentDate.get(GregorianCalendar.MONTH);
        year = currentDate.get(GregorianCalendar.YEAR);
        limiteEditText.setText(day + "/" + (month + 1) + "/" + year);

        limiteEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        day = d;
                        month = m + 1;
                        year = y;
                        limiteEditText.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
