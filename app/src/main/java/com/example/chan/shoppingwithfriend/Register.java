package com.example.chan.shoppingwithfriend;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

/**
 * Let new user to register, already existed user will be asked to log in
 */
public class Register extends ActionBarActivity {

    User users = new User();

    /**
     * Create RegisterActivity
     * @param savedInstanceState taking in the state currently
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button create = (Button)findViewById(R.id.createAccount);
        create.setOnClickListener(new View.OnClickListener(){
            /**
             * method override for onclick
             * @param v View
             */
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.usernameReg)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordReg)).getText().toString();
                String retypePassword = ((EditText) findViewById(R.id.retypePasswordReg)).getText().toString();
                if(!(password.equals(retypePassword))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Passwords do not match");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if((username.equals(""))||(password.equals(""))||(retypePassword.equals(""))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Inputs cannot be blanks");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if(users.getDatabase().containsKey(username)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("username already exists");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    users.getDatabase().put(username,password);
                    users.getFriendDatabase().put(username,new HashSet<String>());
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Register successful");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    //setContentView(R.layout.activity_login);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    // Called when the user clicks the Cancel button
    /**
     * homeScreen method that is called when the cancel button is pressed
     * @param view of the homescreen
     */
    public void homeScreen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Cancel button */
    /**
     * method that is called when the user clicks the cancel button to go back to the login screen
     * @param view of login
     */
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
