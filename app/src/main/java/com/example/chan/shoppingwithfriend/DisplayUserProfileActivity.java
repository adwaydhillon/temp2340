package com.example.chan.shoppingwithfriend;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Set;

/**
 * Display any user's profile for view of user's detail information
 */
public class DisplayUserProfileActivity extends ActionBarActivity {
    String username = "unknown";
    String friendUsername = "unknown";
    User users = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the friend's username from the intent
        Intent intent = getIntent();
        friendUsername = intent.getStringExtra(FriendActivity.FRIEND_USER_NAME);
        username = intent.getStringExtra(FriendActivity.USER_NAME);

        setContentView(R.layout.activity_display_user_profile);
        // Create the text view
        TextView friendTextElement = (TextView) findViewById(R.id.friendUsernameView);
        friendTextElement.setText(friendUsername); //leave this line to assign a specific text
//        TextView usernametextElement = (TextView) findViewById(R.id.usernameView);
//        usernametextElement.setText(username); //leave this line to assign a specific text
        TextView usernametextElement = (TextView) findViewById(R.id.usernameView);
        usernametextElement.setText(""); //leave this line to assign a specific text
    }

    /**
     * Response to when user click the addFriend's button, friend's username is add into user's
     * friend list
     */
    public void addFriend(View view){
        Set friendSet = users.getFriendDatabase().get(username);
        friendSet.add(friendUsername);
        users.getFriendDatabase().put(username,friendSet);
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayUserProfileActivity.this);
        builder.setMessage("User successfully added as your friend");
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deleteFriend(View view) {
        Set friendSet = users.getFriendDatabase().get(username);
        friendSet.remove(friendUsername);
        users.getFriendDatabase().put(username,friendSet);
        // am I actually deleting from the database?!
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayUserProfileActivity.this);
        builder.setMessage("User successfully removed from friends");
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_user_profile, menu);
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
}
