package com.example.omararenaslandgrave.android_keystore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Android_keystore keyStore ;
        try {
            keyStore = new Android_keystore(this);
            keyStore.createKeys("heelo1");
            keyStore.createKeys("world1");
            keyStore.createKeys("how1");
            keyStore.createKeys("ar1e");
            keyStore.createKeys("yo2u");



            //Encrypt and decrypt data
        }catch (Exception e){

        }



    }
}
