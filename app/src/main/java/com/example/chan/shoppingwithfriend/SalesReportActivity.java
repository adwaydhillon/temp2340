package com.example.chan.shoppingwithfriend;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;


public class SalesReportActivity extends ActionBarActivity {

    public static String salesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sales_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void reportItem(View view){
        EditText editTextItem = (EditText) findViewById(R.id.reportItem);
        EditText editTextPrice = (EditText) findViewById(R.id.reportPrice);
        EditText editTextQuantity = (EditText) findViewById(R.id.reportQuantity);
        EditText editTextLocation = (EditText) findViewById(R.id.reportLocation);

        String item = editTextItem.getText().toString();
        String price = editTextPrice.getText().toString();
        String quantity = editTextQuantity.getText().toString();
        String location = editTextLocation.getText().toString();


        salesData = item + " " + price + " " + quantity + " " + location;

        AlertDialog.Builder builder = new AlertDialog.Builder(SalesReportActivity.this);
        builder.setMessage("Item Reported Successfully");
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();

        Intent intent = new Intent(this, UserLoginSuccessActivity.class);
        //username = "dummy";
        intent.putExtra(salesData, salesData);
    }
}
