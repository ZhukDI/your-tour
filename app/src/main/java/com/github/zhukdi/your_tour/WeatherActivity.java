package com.github.zhukdi.your_tour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Dmitry on 3/29/2018.
 */

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_map:
                        Intent intent0 = new Intent(WeatherActivity.this, MapActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_weather:
                        break;

                    case R.id.ic_rate:
                        Intent intent2 = new Intent(WeatherActivity.this, RateActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}
