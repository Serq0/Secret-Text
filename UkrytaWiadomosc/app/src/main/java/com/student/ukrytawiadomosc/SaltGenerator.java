package com.student.ukrytawiadomosc;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Serq on 12.11.2017.
 */

public class SaltGenerator {
    SaltGenerator(){
        //value();
    }
    public String value(){
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        //System.out.println(salt.toString());
        String test="";
        try {
            test = new String(salt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(test);
        return test;
    }
}
