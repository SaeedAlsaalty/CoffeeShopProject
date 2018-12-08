package com.example.s3eedals.coffeeshopproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManageActivity extends Activity {

    private EditText etFoodItem;
    private EditText etType;
    private EditText etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);


        etFoodItem = findViewById(R.id.etFoodItem);
        etType = findViewById(R.id.etType);
        etPrice = findViewById(R.id.etPrice);


    }
    public void addItem(View view) {
        String foodItem = etFoodItem.getText().toString();
        String type = etType.getText().toString();
        String strPrice = etPrice.getText().toString();
        int price = Integer.parseInt(strPrice);

        if (foodItem.isEmpty() || type.isEmpty() || strPrice.isEmpty()) {
            Toast.makeText(this, "Please fill the empty blank", Toast.LENGTH_SHORT).show();
            return;
        }

        ItemsDatabase cdb = ItemsDatabase.getDatabase(this.getApplicationContext());
        ItemsDAO itemsDAO = cdb.itemsDAO();

        if (itemsDAO.getCoffee(foodItem) != null) {
            Toast.makeText(this, foodItem + " already exists", Toast.LENGTH_LONG).show();
            return;

        }


        Items items = new Items(foodItem, type, price);
        itemsDAO.insert(items);

        Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
    }

    public void showAllItems(View view) {
        Intent intent = new Intent(this, ViewActivityAll.class);
        startActivity(intent);
    }

    public void doFindByName(View view) {
        ItemsDatabase cdb = ItemsDatabase.getDatabase(this.getApplicationContext());
        ItemsDAO itemsDAO = cdb.itemsDAO();

        String foodItem = etFoodItem.getText().toString();
        Items items = itemsDAO.getCoffee(foodItem);

        if (foodItem.isEmpty()){
            Toast.makeText(this, "Please fill the empty blank", Toast.LENGTH_SHORT).show();
        }

        if (items == null) {
            Toast.makeText(this, foodItem + " NOT found!", Toast.LENGTH_LONG).show();
            return;
        }

        //fill the type  and price
        etType.setText(items.getType());
        etPrice.setText(items.getPrice() + "");
    }

    public void doUpdate(View view) {
        ItemsDatabase cdb = ItemsDatabase.getDatabase(this.getApplicationContext());
        ItemsDAO itemsDAO = cdb.itemsDAO();

        String foodItem = etFoodItem.getText().toString();
        String type = etType.getText().toString();
        String strPrice = etPrice.getText().toString();

        int price = Integer.parseInt(strPrice);
        Items items = new Items(foodItem, type, price);

        int updateCount = itemsDAO.updateCoffee(items);

        if (updateCount == 1) {
            Toast.makeText(this, foodItem + " data updated!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, foodItem + " could NOT update", Toast.LENGTH_LONG).show();
        }
    }

    public void doDelete(View view) {
        ItemsDatabase cdb = ItemsDatabase.getDatabase(this.getApplicationContext());
        ItemsDAO itemsDAO = cdb.itemsDAO();

        String foodItem = etFoodItem.getText().toString();

        //check if the items exists
        Items items = itemsDAO.getCoffee(foodItem);
        if (items == null) {
            Toast.makeText(this, foodItem + " NOT found!", Toast.LENGTH_LONG).show();
            return;
        }

        //delete by Food item
        int deleteCount = itemsDAO.deleteByFoodItem(foodItem);
        if (deleteCount == 1) {
            Toast.makeText(this, foodItem + " deleted", Toast.LENGTH_LONG).show();
            etFoodItem.setText("");
            etType.setText("");
            etPrice.setText("");
        } else {
            Toast.makeText(this, foodItem + " could NOT be deleted", Toast.LENGTH_LONG).show();
        }
    }


}
