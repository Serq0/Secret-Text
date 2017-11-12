package com.student.ukrytawiadomosc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class StartActivity extends AppCompatActivity {

    String passwordtocheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    private boolean correctPassword(){
        Files file = new Files();
        Enigma enigma = new Enigma();

        String realpassword = file.readFile("password", getApplicationContext());
        EditText password = (EditText) findViewById(R.id.passwordEditText);
        passwordtocheck = password.getText().toString();
        String salt = file.readFile("salt",getApplicationContext());
        String passwordtochecksalt = passwordtocheck+salt;
        byte[] byteencryptedPassword = "".getBytes();
        try {
            byteencryptedPassword = enigma.encrypt(passwordtocheck, passwordtochecksalt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tocheck = new String(byteencryptedPassword);

        if(realpassword.equals(tocheck)){
            return true;
        }

        return false;
    }

    public void login(View view){

        if(correctPassword())
        {
            Intent intent = new Intent(StartActivity.this, MenuActivity.class);
            intent.putExtra("password",passwordtocheck);
            startActivity(intent);
        }
        else{
            Toast.makeText(StartActivity.this, "Bledne haslo." , LENGTH_SHORT).show();
            EditText password = (EditText) findViewById(R.id.passwordEditText);
            password.setText("");
        }
    }

}
