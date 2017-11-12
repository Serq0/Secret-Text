package com.student.ukrytawiadomosc;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Serq on 12.11.2017.
 */

public class Files {
    //Context context;


    public void saveFile(String filename, String textt, Context ctx){
        File path = ctx.getFilesDir();
        File file = new File(path, filename);

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.write(textt.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
        File internalStorageDir = ctx.getFilesDir();
        File file = new File(internalStorageDir, filename);
        FileOutputStream fos;
        try {
            fos = ctx.openFileOutput(filename,Context.MODE_PRIVATE);
            //outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.write(textt.getBytes());
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public String readFile(String filename, Context ctx){
        File path = ctx.getFilesDir();
        File file = new File(path, filename);

        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                in.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String contents = new String(bytes);
        return contents;
        /*
        File internalStorageDir = ctx.getFilesDir();
        String passw = null;
        //internalStorageDir = context.getFilesDir();
        File pswd = new File(internalStorageDir, filename);
        return passw;
        */
    }
}
