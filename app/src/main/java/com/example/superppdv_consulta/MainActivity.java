package com.example.superppdv_consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);

        EditText etPorta = (EditText) findViewById(R.id.porta);
        EditText etIp = (EditText) findViewById(R.id.ip);

        Button btnEntrar = (Button) findViewById(R.id.login);
        SharedPreferences sharedPreferences = getSharedPreferences("Productive", MODE_PRIVATE);


        btnEntrar.setEnabled(true);
        btnEntrar.setOnClickListener(view ->
        {
            String porta = etPorta.getText().toString();
            String ip = etIp.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ip",ip);
            editor.putString("porta",porta);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(),FullscreenActivity.class);
            startActivity(intent);
            finish();

        });

        etIp.setText(sharedPreferences.getString("ip", "IP"));
        etPorta.setText(sharedPreferences.getString("porta", "Porta"));


    }
}