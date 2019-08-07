package com.monykaushik17.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Running extends AppCompatActivity {

    private static int counter;
    private static boolean running;
    private static Handler handler = new Handler();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        counter = getIntent().getIntExtra("counter",0);
        running = true;

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LaptimesContainer.lapTimesArrayList);
        ListView listView = (ListView) findViewById(R.id.lapListView);
        listView.setAdapter(arrayAdapter);

        runner();

    }

    public void onClickStop(View view){
        running = false;
        Intent intent = new Intent(this, Stopped.class);
        intent.putExtra("counter", counter);
        startActivity(intent);
        finish();
    }

    public void onClickLap(View view){
        LaptimesContainer.lapTimesArrayList.add(getTime(counter));
        arrayAdapter.notifyDataSetChanged();

    }

    public void runner(){
        final TextView timeView = (TextView) findViewById(R.id.timeText);

        handler.post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,23);

                if(running){
                    counter+=25;
                    timeView.setText(getTime(counter));
                }
            }
        });
    }


    public static String getTime(int counter){
        int hours= counter / 3600000;
        int minutes = (counter % 3600000)/60000;
        int second = ((counter % 3600000)%60000)/1000;
        int ms = ((counter % 3600000)%60000)%1000;
        return String.format("%02d:%02d:%02d:%03d", hours, minutes,second,ms);
    }
}
