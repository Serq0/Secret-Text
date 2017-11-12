package com.student.ukrytawiadomosc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.widget.Toast.LENGTH_SHORT;

public class MenuActivity extends AppCompatActivity {

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        password = getIntent().getStringExtra("password");
    }


    public void editPassword(View view){
        Files file = new Files();
        EditText passwordET = (EditText) findViewById(R.id.EditPasswordEditText);
        SaltGenerator salt = new SaltGenerator();
        String saltvalue = salt.value();
        file.saveFile("salt", saltvalue, getApplicationContext());


        Enigma enigma = new Enigma();
        String passwordsalt = passwordET.getText().toString()+saltvalue;
        String password = passwordET.getText().toString();
        byte[] bytetosave="".getBytes();
        try {
            bytetosave = enigma.encrypt( password , passwordsalt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tosave = new String(bytetosave);
        Toast.makeText(MenuActivity.this, tosave , LENGTH_SHORT).show();
        file.saveFile("password", tosave ,getApplicationContext());
        passwordET.setText("");
    }

    public void editMessage(View view){
        Message message = new Message();
        EditText messageET = (EditText) findViewById(R.id.EditMessageEditText);
        String messageToEncrypt = messageET.getText().toString();
        message.saveMessage(messageToEncrypt, password, getApplicationContext());
        messageET.setText("");
    }

    public void showMessage(View view){
        Message message = new Message();
        Toast.makeText(MenuActivity.this, message.readMessage(password, getFilesDir()), LENGTH_SHORT).show();
    }



}
