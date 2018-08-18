package com.ifsul.remindme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    final TextView nome;
    final TextView descricao;
    final TextView limite;

    TaskViewHolder(View view){
        super(view);
        nome = view.findViewById(R.id.text_view_nome);
        descricao = view.findViewById(R.id.text_view_descricao);
        limite = view.findViewById(R.id.text_view_date);

    }
}
