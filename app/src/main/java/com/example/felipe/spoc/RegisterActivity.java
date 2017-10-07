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
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        TextView tvVoltar = (TextView) findViewById(R.id.tvVoltar);
        Button botaoRegistrar = (Button) findViewById(R.id.btnRegistrarREGISTRO);

        tvVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    public void onReg(View view){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!temInternet()) {
            mostrarToastLongo("Não há conexão com a internet.");
        } else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            String type = "cadastro";
            EditText nome = (EditText) findViewById(R.id.nomeREGISTRO);
            AutoCompleteTextView email = (AutoCompleteTextView) findViewById(R.id.emailREGISTRO);
            EditText senha = (EditText) findViewById(R.id.passwordREGISTRO);
            EditText csenha = (EditText) findViewById(R.id.passwordCREGISTRO);
            String nomeSTR = nome.getText().toString();
            String emailSTR = email.getText().toString();
            String senhaSTR = senha.getText().toString();
            String csenhaSTR = csenha.getText().toString();
            if (!senhaSTR.equals(csenhaSTR)){
                mostrarToastCurto("As senhas não combinam.");
            } else {
                backgroundWorker.execute(type, emailSTR, senhaSTR, nomeSTR);
            }
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
