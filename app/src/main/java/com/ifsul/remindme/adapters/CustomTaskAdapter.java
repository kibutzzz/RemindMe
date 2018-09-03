package com.ifsul.remindme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifsul.remindme.R;
import com.ifsul.remindme.Task;
import com.ifsul.remindme.TaskViewHolder;

import java.util.List;

public class CustomTaskAdapter extends RecyclerView.Adapter {

    private List<Task> tasks;
    private Context context;

    public CustomTaskAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TaskViewHolder holder = (TaskViewHolder) viewHolder;
        Task task = tasks.get(position);


        holder.nome.setText(task.getNome());
        holder.descricao.setText(task.getDesricao());
        holder.limite.setText(task.getLimite());
        holder.key.setText(task.getKey());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
