package com.ifsul.remindme.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifsul.remindme.R;
import com.ifsul.remindme.Task;
import com.ifsul.remindme.activities.MainActivity;
import com.ifsul.remindme.adapters.CustomTaskAdapter;
import com.ifsul.remindme.database.DatabaseUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends Fragment {

    private static String TAG = "LOG_TAG";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tasksDatabaseReferrence;
    private ArrayList<Task> tasks;
    private CustomTaskAdapter adapter;
    private ChildEventListener tasksReferenceChildEventListener;


    public TasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);

        //empty arraylist
        tasks = new ArrayList<>();

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        adapter = new CustomTaskAdapter(tasks, getActivity());
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        tasksReferenceChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Task t = dataSnapshot.getValue(Task.class);

                t.setKey(dataSnapshot.getKey());
                tasks.add(t);
                removeDuplicates();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        setDatabaseChangeListener();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        removeDatabaseChangeListener();

    }

    private void removeDuplicates(){
        int lastIndex = tasks.size()-1;
        if(lastIndex > 0) {
            if (tasks.get(lastIndex).getKey().equals(tasks.get(lastIndex - 1).getKey())) {
                tasks.remove(lastIndex);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void setDatabaseChangeListener() {
        firebaseDatabase = DatabaseUtils.getFirebaseDatabase();
        tasksDatabaseReferrence = firebaseDatabase.getReference("tasks").child(MainActivity.usuario.getUid());

        tasksDatabaseReferrence.addChildEventListener(tasksReferenceChildEventListener);

    }
    private void removeDatabaseChangeListener(){
        tasksDatabaseReferrence.removeEventListener(tasksReferenceChildEventListener);

    }


    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
