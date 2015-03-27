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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FriendActivity extends ActionBarActivity {
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

        setContentView(R.layout.activity_friend);

        ListView listView= (ListView)findViewById(R.id.tempFriendListView);
        Set<String> friendSet = users.getFriendDatabase().get(username);
        List<String> friendList = new ArrayList<String>(friendSet);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, friendList);
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
    public void displayFriend(View view){
        EditText editText = (EditText) findViewById(R.id.searchFriendInput);
        String message = editText.getText().toString();
        if(username.equals(message)){
            AlertDialog.Builder builder = new AlertDialog.Builder(FriendActivity.this);
            builder.setMessage("You cannot add yourself as friend");
            builder.setCancelable(true);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(users.getDatabase().containsKey(message)){
            displayFriendSuccess(message);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(FriendActivity.this);
            builder.setMessage("User does not exist");
            builder.setCancelable(true);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /**
     * Display the friend's profile information
     * Extra: pass along the friend's username and user's username
     * @param friendUsername
     */
    public void displayFriendSuccess(String friendUsername){
        Intent intent = new Intent(this, DisplayUserProfileActivity.class);
        intent.putExtra(FRIEND_USER_NAME, friendUsername);
        intent.putExtra(USER_NAME, username);
        startActivity(intent);
        finish();
    }
}