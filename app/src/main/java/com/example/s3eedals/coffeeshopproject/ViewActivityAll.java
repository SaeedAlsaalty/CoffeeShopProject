package com.example.s3eedals.coffeeshopproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ViewActivityAll extends Activity {

    private TextView tvCoffees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        tvCoffees = findViewById(R.id.tvCoffees);

        ItemsDatabase cdb = ItemsDatabase.getDatabase(this.getApplicationContext());
        ItemsDAO itemsDAO = cdb.itemsDAO();

        List<Items> ItemsList = itemsDAO.getAllCoffee();
        StringBuilder sbCoffee = new StringBuilder();
        for (Items items : ItemsList) {
            sbCoffee.append("item name: " + items.getFoodItem()
                    + " " +"Food Type :" + items.getType()
                    + " " + "Price: " + items.getPrice()
                    + " " + "\n");



         }
        tvCoffees.setText(sbCoffee.toString());
    }
}
