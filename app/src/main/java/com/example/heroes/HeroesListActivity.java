package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;


import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HeroesListActivity extends AppCompatActivity {

    private ArrayAdapter adaptHeroes;
    public static final String TAG = HeroesListActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.heroes); //getting xml
        String jsonString = readTextFile(XmlFileInputStream);
        // create a gson object
        Gson gson = new Gson();
        // read your json file into an array of questions
        Heroes[] heroes =  gson.fromJson(jsonString, Heroes[].class);
        // convert your array to a list using the Arrays utility class
        List<Heroes> heroesList = Arrays.asList(heroes);
        // verify that it read everything properly
        adaptHeroes = new ArrayAdapter<Heroes>(this, android.R.layout.simple_list_item_1, heroesList);
        Log.d(TAG, "onCreate: " + heroesList.toString());
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
