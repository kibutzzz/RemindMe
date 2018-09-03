package com.ifsul.remindme.adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifsul.remindme.Group;
import com.ifsul.remindme.R;

import java.util.ArrayList;

public class CustomGroupsAdapter extends BaseAdapter {
    private final ArrayList<Group> groups;
    private final AppCompatActivity activity;

    public CustomGroupsAdapter(ArrayList<Group> groups, AppCompatActivity activity) {
        this.groups = groups;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int i) {
        return groups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = activity.getLayoutInflater()
                .inflate(R.layout.list_item_group, viewGroup, false);
        Group group = groups.get(i);


        //pegando a referencia de cada view
        AppCompatTextView nome = itemView.findViewById(R.id.group_name);
        AppCompatTextView descricao = itemView.findViewById(R.id.group_description);

        //populando as views
        nome.setText(group.getNome());
        descricao.setText(group.getDescricao());

        //TODO implementação da imagem



        return itemView;
    }
}
