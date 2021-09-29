package com.abdul.mirza.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abdul.mirza.mvc.Controller.MainController;
import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.Model.ListItem;
import com.abdul.mirza.mvc.View.IMainView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements IMainView {

    private Button btn_increase_eventA, btn_increase_eventB, btn_increase_eventC;
    private TextView tv_event_count, tv_max_count;
    private CircularProgressBar pb;

    MainController mainController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainController = new MainController(this, this);

        findViewById(R.id.btn_activity_settings).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToSettingsActivity();
            }
        });

        findViewById(R.id.btn_activity_data).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToDataActivity();
            }
        });

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss", Locale.getDefault());

        btn_increase_eventA = findViewById(R.id.btn_cmd_increase_eventA);
        btn_increase_eventA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainController.onSaveDataToSharedPreferences(new ListItem(PREFERENCE_NAME.EVENT_A, df.format(new Date())));
            }
        });

        btn_increase_eventB = findViewById(R.id.btn_cmd_increase_eventB);
        btn_increase_eventB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainController.onSaveDataToSharedPreferences(new ListItem(PREFERENCE_NAME.EVENT_B, df.format(new Date())));
            }
        });

        btn_increase_eventC = findViewById(R.id.btn_cmd_increase_eventC);
        btn_increase_eventC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainController.onSaveDataToSharedPreferences(new ListItem(PREFERENCE_NAME.EVENT_C, df.format(new Date())));
            }
        });

        tv_event_count = findViewById(R.id.tv_event_count);
        tv_max_count = findViewById(R.id.tv_max_count);
        pb = findViewById(R.id.pb);
    }

    @Override
    public void updateUI(String btnEventAText, String btnEventBText, String btnEventCText, int itemsCount, int maxCount) {
        btn_increase_eventA.setText(btnEventAText);
        btn_increase_eventB.setText(btnEventBText);
        btn_increase_eventC.setText(btnEventCText);
        tv_event_count.setText(Integer.toString(itemsCount));
        tv_max_count.setText("max count: "+ Integer.toString(maxCount));
        pb.setProgress(itemsCount);
        pb.setProgressMax(maxCount);
    }

    @Override
    public void setTextViewText(String text) {
        tv_event_count.setText(text);
    }

    @Override
    public void setProgressValue(int itemsCount) {
        pb.setProgress(itemsCount);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!mainController.onSetup()) {
            goToSettingsActivity();
        }
    }

    void goToSettingsActivity()
    {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    void goToDataActivity()
    {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }


}