package com.example.aplikasilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button bLogin, bRegister;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bLogin = (Button) findViewById(R.id.btnLogin);
        bRegister = (Button) findViewById(R.id.btnRegister);
        etUsername = (EditText) findViewById(R.id.formUsername);
        etPassword = (EditText) findViewById(R.id.formPassword);
        db = new DatabaseHelper(this);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Boolean checkStatus = db.checkStatus(email,password);

                if(checkStatus){
                    Toast.makeText(getApplicationContext(),"Login Succesfull",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
}