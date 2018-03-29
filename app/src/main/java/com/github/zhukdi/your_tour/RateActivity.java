package com.github.zhukdi.your_tour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Dmitry on 3/29/2018.
 */

public class RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_map:
                        Intent intent0 = new Intent(RateActivity.this, MapActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_weather:
                        Intent intent1 = new Intent(RateActivity.this, WeatherActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_rate:
                        break;
                }
                return false;
            }
        });
//        TextView title (TextView) findViewById(R.id.)
    }
}
