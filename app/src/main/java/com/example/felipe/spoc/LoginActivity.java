package com.example.felipe.spoc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button botaoRegistrar = (Button) findViewById(R.id.btnRegistrarLOGIN);



        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        if (getIntent().hasExtra("nomedocara")){
            mostrarToastLongo("Seja bem vindo, "+getIntent().getStringExtra("nomedocara")+". Logue-se acima.");
        }

    }

    public void onLogin(View view){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!temInternet()) {
            mostrarToastLongo("Não há conexão com a internet.");
        } else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            String type = "login";
            AutoCompleteTextView email = (AutoCompleteTextView) findViewById(R.id.emailLOGIN);
            EditText senha = (EditText) findViewById(R.id.passwordLOGIN);
            String emailSTR = email.getText().toString();
            String senhaSTR = senha.getText().toString();
            backgroundWorker.execute(type, emailSTR, senhaSTR);
        }
    }

    public void mostrarToastLongo(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void mostrarToastCurto(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public boolean temInternet() {
        boolean temConexao = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    temConexao = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    temConexao = true;
        }
        return temConexao;
    }



}
