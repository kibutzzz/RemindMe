package com.ifsul.remindme;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomTaskAdapter extends BaseAdapter {
    private ArrayList<Task> tasks;
    private final AppCompatActivity activity;

    public CustomTaskAdapter(ArrayList<Task> tasks, AppCompatActivity activity) {
        this.tasks = tasks;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //TODO implement viewHolder for smoother scrolling
        View itemView = activity.getLayoutInflater()
                .inflate(R.layout.list_item_task, viewGroup, false);
        Task task = tasks.get(i);

        //pegando a referencia de cada view
        TextView nome = (TextView) itemView.findViewById(R.id.text_view_nome);
        TextView descricao = (TextView) itemView.findViewById(R.id.text_view_descricao);
        TextView limite = itemView.findViewById(R.id.text_view_date);

        //populando as views
        nome.setText(task.getNome());
        descricao.setText(task.getDesricao());
        limite.setText(task.getLimite());

        return itemView;
    }

    public void udpateAdapter(Task t){
        this.tasks.add(t);
        notifyDataSetChanged();
    }
}
