package com.ifsul.remindme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.ifsul.remindme.R;
import com.ifsul.remindme.adapters.PageAdapter;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabTarefas;
    TabItem tabGrupos;
    FloatingActionButton floatingActionButton;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            //logado
        } else{
            //deslogado
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.EmailBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);


        tabLayout = findViewById(R.id.tabLayout);
        tabTarefas = findViewById(R.id.tabTarefas);
        tabGrupos = findViewById(R.id.tabGrupos);
        viewPager = findViewById(R.id.viewPager);

        floatingActionButton = findViewById(R.id.fab);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //seta o pager para a tab que o usuario clicou
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //se o request for do login
        if (requestCode == RC_SIGN_IN){

            //se o login foi concluido com sucesso
            if(resultCode == RESULT_OK){
                //TODO setar usuario atual

            }
        }
    }
}
