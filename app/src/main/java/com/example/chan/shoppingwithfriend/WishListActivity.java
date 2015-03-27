package com.example.chan.shoppingwithfriend;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class WishListActivity extends ActionBarActivity {
    public final static String FRIEND_USER_NAME = "com.example.chan.shoppingwithfriend.FRIENDUSERNAME";
    public final static String USER_NAME = "com.example.chan.shoppingwithfriend.USERNAME";
    //default username
    static String username = "unknown";

    User users = new User();

    /**
     * Upon create, a list of user's friend will be displayed, it also gives user the option to search
     * for friend
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the friend's username from the intent
        Intent intent = getIntent();
        username = intent.getStringExtra(LoginActivity.USER_NAME);

        setContentView(R.layout.activity_wishlist);

        ListView listView= (ListView)findViewById(R.id.wishListView);
        //Map<String, Map<String, String>> wishList;
        Set<String> wishSet = new HashSet<String>();
        Object wishList = users.getItemDatabase();

        Map<String, Map<String, String>> m = (Map<String, Map<String, String>>) wishList;

        if (!users.getItemDatabase().containsKey(USER_NAME)) {
            Map<String, String> newMap = new HashMap<String, String>();
            m.put(USER_NAME, newMap);
        }

        wishSet = m.get(USER_NAME).keySet();

        Set<String> itemSet = new HashSet<String>();
        ArrayList<String> listing = new ArrayList<String>();

        itemSet = wishSet;
        ArrayList<String> priceList = new ArrayList<String>(m.get(USER_NAME).values());

        int index = 0;
        for (String item : itemSet) {
            listing.add((String) (item + ": $" + priceList.get(index)));
            index++;
        }

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, listing);
        listView.setAdapter(adapter);
    }

    /**
     * Return the username
     * @return
     */
    public String getUsername() {
        return username;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend, menu);
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

    /**
     * Search for the user want to add as friend, if the user that is intended to be add as a friend
     * exists, that user profile get pull up, or else, error message saying user does not exists
     * pop up.
     *
     * If user tries to add himself as friend, error message appears
     */
    public void listItem(View view){
        EditText editTextItem = (EditText) findViewById(R.id.addItem);
        EditText editTextPrice = (EditText) findViewById(R.id.addPrice);
        String item = editTextItem.getText().toString();
        String price = editTextPrice.getText().toString();

        Map<String, String> wishList = users.getItemDatabase().get(USER_NAME);
        wishList.put(item, price);

        Map<String, Map<String, String>> temp = new HashMap<String, Map<String, String>>();
        temp.put(USER_NAME, wishList);
        users.setItemDatabase(temp);

        AlertDialog.Builder builder = new AlertDialog.Builder(WishListActivity.this);
        builder.setMessage("Item Listed Successfully");
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}