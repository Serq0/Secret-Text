package com.student.ukrytawiadomosc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class RegisterPasswordActivity extends AppCompatActivity {
    String passwordtocheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerPassword(View view){
        editPassword(view);
        Intent intent = new Intent(RegisterPasswordActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void editPassword(View view){
        Files file = new Files();
        EditText passwordET = (EditText) findViewById(R.id.newPasswordEditText);
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
        Toast.makeText(RegisterPasswordActivity.this, tosave , LENGTH_SHORT).show();
        file.saveFile("password", tosave ,getApplicationContext());
        passwordET.setText("");
    }

}
