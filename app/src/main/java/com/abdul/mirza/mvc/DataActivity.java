package com.abdul.mirza.mvc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.abdul.mirza.mvc.Controller.DataController;
import com.abdul.mirza.mvc.Model.ListItem;
import com.abdul.mirza.mvc.View.IDataView;

import java.util.List;

public class DataActivity extends AppCompatActivity implements IDataView {

    RecyclerView rv;
    TextView tv_event_A, tv_event_B, tv_event_C, tv_max_count;
    //boolean showEventNames = true;

    DataController dataController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        dataController = new DataController(this, this);

        assert getSupportActionBar() != null;   //null check
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_event_A = findViewById(R.id.tv_event_A);
        tv_event_B = findViewById(R.id.tv_event_B);
        tv_event_C = findViewById(R.id.tv_event_C);

        tv_max_count = findViewById(R.id.tv_event_max_count);

        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        dataController.onSetup();
    }

    @Override
    public void updateUI(List<ListItem> items, boolean showEventNames, String eventA, String eventB, String eventC, String totalEvents) {

        tv_event_A.setText( eventA);
        tv_event_B.setText( eventB);
        tv_event_C.setText( eventC);

        tv_max_count.setText(totalEvents);
        onRecyclerViewUpdate(items, showEventNames);
    }

    @Override
    public void onRecyclerViewUpdate( List<ListItem> items, boolean showEventNames) {
        if(rv.getAdapter() != null) {
            DataRecyclerViewAdapter adapter = (DataRecyclerViewAdapter) rv.getAdapter();
            adapter.setShowDataNames(showEventNames);
            adapter.notifyDataSetChanged();
        } else {
            rv.setAdapter(new DataRecyclerViewAdapter(items, true));
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
                    dataController.changeState();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }


}