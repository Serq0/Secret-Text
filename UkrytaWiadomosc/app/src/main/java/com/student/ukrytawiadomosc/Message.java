package com.student.ukrytawiadomosc;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Serq on 12.11.2017.
 */

public class Message {
    public void saveMessage(String message, String key, Context context){

        byte[] encrypted = new byte[0];
        Enigma enigma = new Enigma();
        try {
            encrypted = enigma.encrypt(message,key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput("message", Context.MODE_PRIVATE);
            outputStream.write(encrypted);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readMessage(String password, File f){

        String decrypted = null;
        try {
            Enigma enigma = new Enigma();
            decrypted = enigma.decrypt(getByte("message", f), password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;

        //return "";
    }

    private byte[] getByte(String filename, File f) {
        byte[] getBytes = {};
        try {
            File internalStorageDir = f;
            File file = new File(internalStorageDir,filename);
            getBytes = new byte[(int) file.length()];
            InputStream is = new FileInputStream(file);
            is.read(getBytes);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getBytes;
    }
}
