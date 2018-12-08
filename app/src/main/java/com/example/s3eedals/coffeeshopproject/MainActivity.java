package com.example.s3eedals.coffeeshopproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void showAllItems(View view) {
            Intent intent = new Intent(this, ViewActivityAll.class);
            startActivity(intent);
        }

        public void ManageActivity(View view) {
            Intent intent = new Intent(this, ManageActivity.class);
            startActivity(intent);
        }

        public void MapsActivity(View view) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

}
