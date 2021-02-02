package com.example.barkas_pavlos_18022_androidjan21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "CORONA";

    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        dataAdapter = new DataAdapter(this, R.layout.listview_item, new ArrayList<DataEntry>());
        listView.setAdapter(dataAdapter);

        FetchDataTask fetchDataTask = new FetchDataTask(dataAdapter);
        fetchDataTask.execute();
    }
}