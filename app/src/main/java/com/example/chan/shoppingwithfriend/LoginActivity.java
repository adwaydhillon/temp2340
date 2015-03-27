package com.example.chan.shoppingwithfriend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;


public class LoginActivity extends ActionBarActivity {
    public final static String USER_NAME = "com.example.chan.shoppingwithfriend.USERNAME";

    boolean loginSuccess = false;
    User users = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    /**
     * User type in their username and password, if authorized, user will log in using LoginAllow method.
     * @param view
     */
    public void loginRequest(View view){
        Button b = (Button) findViewById(R.id.loginplz);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.usernameInput)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();
                if(password.equals(users.database.get(username)))
                {
                    loginSuccess = true;
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Invalid Username and Password!");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                loginAllow(loginSuccess, username);
            }
        });
    }

    /**
     *
     * @param allow if the user has the permission to log in or not, if yes, UserLoginSuccessActivity
     *              will start using the username given.
     * @param username
     * extra: the username will be provided to UserLoginSuccessActivity
     */
    public void loginAllow(boolean allow, String username){
        if(allow){
            Intent intent = new Intent(this, UserLoginSuccessActivity.class);
            intent.putExtra(USER_NAME, username);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.friend, menu);
        return super.onCreateOptionsMenu(menu);
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
     * Called when the user clicks the Cancel button
     */
    /**
     * homeScreen
     * @param view homeScreen
     */
    public void homeScreen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the register button
     */
    /**
     * register
     * @param view register
     */
    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the logout button
     */
    /**
     * logout
     * @param view logout
     */
    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
