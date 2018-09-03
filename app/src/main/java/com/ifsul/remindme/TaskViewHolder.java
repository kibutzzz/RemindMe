package com.ifsul.remindme;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifsul.remindme.activities.TaskDetailActivity;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public final TextView nome;
    public final TextView descricao;
    public final TextView limite;
    public final AppCompatTextView key;

    public TaskViewHolder(View view) {
        super(view);
        nome = view.findViewById(R.id.text_view_nome);
        descricao = view.findViewById(R.id.text_view_descricao);
        limite = view.findViewById(R.id.text_view_date);
        key = view.findViewById(R.id.task_id);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), TaskDetailActivity.class);
                i.putExtra("key", key.getText().toString());
                view.getContext().startActivity(i);
            }
        });
    }
}
