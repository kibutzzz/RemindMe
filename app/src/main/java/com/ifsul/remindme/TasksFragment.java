package com.ifsul.remindme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends Fragment {


    public TasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);

        ArrayList<Task> tasks = getAllTasks();
        ListView listView = rootView.findViewById(R.id.list_view);
        CustomTaskAdapter adapter = new CustomTaskAdapter(tasks, (AppCompatActivity) getActivity());
        listView.setAdapter(adapter);

        return rootView;
    }

    private ArrayList<Task> getAllTasks(){
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Activity de adcionar tarefa", "Adicionar a activity para" +
                "adicionar uma nova tarefa", "17/07/2018"));
        tasks.add(new Task("Adicionar o Firebase", "Adicionar o Firebase ao Projeto",
                "20/07/2018"));
        tasks.add(new Task("Implementar Firebase", "Implementar as buscas e cadastro " +
                "de dados no firebase", "27/07/2018"));


        return tasks;
    }
}
