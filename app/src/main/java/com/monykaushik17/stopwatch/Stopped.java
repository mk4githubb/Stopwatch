package com.monykaushik17.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Stopped extends AppCompatActivity {

    private int counter;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopped);
        counter = getIntent().getIntExtra("counter",0);

        TextView textView = (TextView) findViewById(R.id.timeText);
        textView.setText(Running.getTime(counter));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LaptimesContainer.lapTimesArrayList);
        ListView listView = (ListView) findViewById(R.id.lapListView);
        listView.setAdapter(arrayAdapter);
    }

    public void onClickStart(View view){
        Intent intent = new Intent(this, Running.class);
        intent.putExtra("counter", counter);
        intent.putExtra("running",true);
        startActivity(intent);
        finish();
    }

    public void onClickReset(View view){
        LaptimesContainer.lapTimesArrayList.clear();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }


}
