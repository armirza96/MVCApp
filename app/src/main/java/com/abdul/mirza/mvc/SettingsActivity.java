package com.abdul.mirza.mvc;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.abdul.mirza.mvc.Controller.SettingsController;
import com.abdul.mirza.mvc.Helper.PREFERENCE_NAME;
import com.abdul.mirza.mvc.View.ISettingsView;

import java.util.Map;

public class SettingsActivity extends AppCompatActivity implements ISettingsView {

    private EditText eventA, eventB, eventC, maxCount;
    private Button btn_save;

    SettingsController settingsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsController = new SettingsController(this, this);

        assert getSupportActionBar() != null;   //null check
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_save = findViewById(R.id.btn_cmd_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            public void onClick(View v) {
                saveData();
            }
        });

        eventA = findViewById(R.id.et_counter_eventA);
        eventB = findViewById(R.id.et_counter_eventB);
        eventC = findViewById(R.id.et_counter_eventC);
        maxCount = findViewById(R.id.et_counter_maxCounter);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    void saveData(){
        settingsController.saveData(Map.of(eventA, PREFERENCE_NAME.EVENT_A, eventB, PREFERENCE_NAME.EVENT_B, eventC, PREFERENCE_NAME.EVENT_C), maxCount);
    }

    @Override
    public void updateUIState(boolean isEditing) {

        eventA.setFocusable(isEditing);
        eventA.setFocusableInTouchMode(isEditing);

        eventB.setFocusable(isEditing);
        eventB.setFocusableInTouchMode(isEditing);

        eventC.setFocusable(isEditing);
        eventC.setFocusableInTouchMode(isEditing);

        maxCount.setFocusableInTouchMode(isEditing);
        maxCount.setFocusable(isEditing);

        btn_save.setVisibility( (isEditing ? View.VISIBLE : View.INVISIBLE) );
    }

    @Override
    public void setEditTextText(String eventAText, String eventBText, String eventCText, String itemsCount) {
        eventA.setText(eventAText);
        eventB.setText(eventBText);
        eventC.setText(eventCText);
        maxCount.setText(itemsCount);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!settingsController.onSetup()) {
            settingsController.changeState();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_toggle_edit:
                //updateUI(settingsController.isEditing())
                settingsController.changeState();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_settings_menu, menu);
        return true;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
