package com.ifsul.remindme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public final TextView nome;
    public final TextView descricao;
    public final TextView limite;

    public TaskViewHolder(View view){
        super(view);
        nome = view.findViewById(R.id.text_view_nome);
        descricao = view.findViewById(R.id.text_view_descricao);
        limite = view.findViewById(R.id.text_view_date);

    }
}
