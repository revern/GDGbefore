package com.example.almaz.gdgkazanweather;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.almaz.gdgkazanweather.Adapter.CityRecyclerViewAdapter;
import com.example.almaz.gdgkazanweather.Provider.CitiesProvider;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public final String CITIES = "CITIES";
    public final int NAME_COLUMN = 1;

//    @BindView(R.id.rcv_cities)
    RecyclerView mCitiesRcv;

    private SharedPreferences sharedPreferences;
    private List<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        mCitiesRcv = (RecyclerView) findViewById(R.id.rcv_cities);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateRcv();
    }

    private void updateRcv(){
        takeCities();
        setRecyclerAdapter(mCitiesRcv, cities);
    }

    private void setRecyclerAdapter(RecyclerView recyclerView, List list) {
        CityRecyclerViewAdapter adapter = new CityRecyclerViewAdapter(getApplicationContext(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
    }

    private void takeCities(){
        cities = new ArrayList<>();
        Cursor cursor = getContentResolver().query(CitiesProvider.CITIES_CONTENT_URI, null, null,
                null, "name ASC");
        cursor.moveToFirst();
        for(int i = 0; i<cursor.getCount();i++){
            cities.add(cursor.getString(NAME_COLUMN));
            cursor.moveToNext();
        }
    }

    public void onClickAddCity(View v){
        final EditText et = new EditText(getApplicationContext());
        et.setTextColor(Color.parseColor("#555555"));
        et.setTextSize(24);
        et.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Adding new city")
                .setCancelable(true)
                .setView(et)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ContentValues cv = new ContentValues();
                                cv.put(CitiesProvider.CITY_NAME, et.getText().toString());
                                getContentResolver().insert(CitiesProvider.CITIES_CONTENT_URI, cv);
                                updateRcv();
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Adding new city");
        final EditText et = new EditText(getApplicationContext());
        adb.setView(et);
        adb.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContentValues cv = new ContentValues();
                cv.put(CitiesProvider.CITY_NAME, et.getText().toString());
                getContentResolver().insert(CitiesProvider.CITIES_CONTENT_URI, cv);
                finish();
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        return adb.create();
    }


}
