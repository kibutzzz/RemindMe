package com.ifsul.remindme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifsul.remindme.R;
import com.ifsul.remindme.adapters.PageAdapter;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private static View.OnClickListener fabListener;
    public static FirebaseUser usuario;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    private TabItem tabTarefas;
    private TabItem tabGrupos;
    private FloatingActionButton floatingActionButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TabLayout.OnTabSelectedListener onTabSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

//      inicializando compontentes
        tabLayout = findViewById(R.id.tabLayout);
        tabTarefas = findViewById(R.id.tabTarefas);
        tabGrupos = findViewById(R.id.tabGrupos);
        viewPager = findViewById(R.id.viewPager);
        floatingActionButton = findViewById(R.id.fab);

        firebaseAuth = FirebaseAuth.getInstance();
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
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
        };


        fabListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);

            }
        };
        //adiciona o clickListener ao FAB
        floatingActionButton.setOnClickListener(fabListener);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //definindo o authstatelistener
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                usuario = firebaseAuth.getCurrentUser();
                if (usuario != null) {
                    //usuero ta logado
                    viewPager.setAdapter(pageAdapter);
                } else {
                    //usuero ta desloGADOD+++
                    viewPager.setAdapter(null);
                    startActivityForResult(
                            // Get an instance of AuthUI based on the default app
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()))
                                    .setLogo(R.mipmap.ic_launcher)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //se o request for do login
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            //se o login foi concluido com sucesso
            if (resultCode == RESULT_OK) {
                //TODO setar usuario atual
                Toast.makeText(getBaseContext(), "seja bem-vindo " + usuario.getDisplayName(), Toast.LENGTH_LONG).show();

            } else {
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(getBaseContext(), "Login cancelado", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(getBaseContext(), "Erro: sem conexão de internet", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getBaseContext(), "Erro desconhecido", Toast.LENGTH_LONG).show();
                Log.e("---", "Sign-in error: ", response.getError());

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);

        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }


    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
        tabLayout.removeOnTabSelectedListener(onTabSelectedListener);
    }
}
