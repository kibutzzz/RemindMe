package com.ifsul.remindme.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ifsul.remindme.adapters.CustomGroupsAdapter;
import com.ifsul.remindme.Group;
import com.ifsul.remindme.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {


    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_groups, container, false);

        ArrayList<Group> groups = getAllGrupos();

        ListView listaDeGrupos = rootView.findViewById(R.id.list_view);
        CustomGroupsAdapter adapter = new CustomGroupsAdapter(groups, (AppCompatActivity) getActivity());
        listaDeGrupos.setAdapter(adapter);
        return rootView;
    }

    public ArrayList<Group> getAllGrupos(){
        ArrayList<Group> groups = new ArrayList<>();

        groups.add(new Group("Projeto X", "Grupo do projeto"));
        groups.add(new Group("INFO Familia", "Grupo da turma"));

        return groups;
    }

}
