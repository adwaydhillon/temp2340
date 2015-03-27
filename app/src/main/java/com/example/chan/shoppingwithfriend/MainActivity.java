package com.example.chan.shoppingwithfriend;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashSet;


public class MainActivity extends ActionBarActivity {

    User users = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dummys
        users.getDatabase().put("aa","123");
        users.getFriendDatabase().put("aa", new HashSet<String>());
        users.getDatabase().put("bb","123");
        users.getFriendDatabase().put("bb", new HashSet<String>());
        users.getDatabase().put("cc","123");
        users.getFriendDatabase().put("aa", new HashSet<String>());
        users.getDatabase().put("qq","qq");
        users.getFriendDatabase().put("qq", new HashSet<String>());
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //Let the user to login

    /**
     * method that allows user to login
     * @param view login
     */
    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //Let the user to register

    /**
     * method that allows the user register
     * @param view register
     */
    public void register(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
