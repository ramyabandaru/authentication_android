package com.example.ramya.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Ramya on 20-12-2015.
 */
public class home extends Activity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String user_name="username_key";
    public static final String status="status_key";
    SharedPreferences sharedpreferences;
    TextView username;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        username=(TextView) findViewById(R.id.uname);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
      //  SharedPreferences.Editor editor = sharedpreferences.edit();
        String name=sharedpreferences.getString(user_name,"null");
        username.setText(name);
    }
}


