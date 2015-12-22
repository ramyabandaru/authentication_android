package com.example.ramya.chat;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText pwd,uname;
    TextView test;
    String res,name,pass;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String user_name="username_key";
    public static final String status="status_key";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // super.onCreate(savedInstanceState);
        uname = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);
        test=(TextView) findViewById(R.id.test);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

      //  uname.setText("ramya");
      //  name=uname.getText().toString();
        //pass=pwd.getText().toString();

        //Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

    }
    public void validate(View view) throws ExecutionException, InterruptedException {

        name=uname.getText().toString();
        pass=pwd.getText().toString();

        if(name.equals("")|| pass.equals(""))
        {
            test.setText("enter username and password");

        }
        else
        {
         //   Toast.makeText(MainActivity.this, " hello"+res, Toast.LENGTH_SHORT).show();
            test.setText("else1");
            myclass m=new myclass("http://thethinktankers.in/meridian/ramya/login.php",name,pass);
            RequestTask req = new RequestTask(this);
            req.execute(m);
           SharedPreferences.Editor editor = sharedpreferences.edit();
            test.setText(res);
            String t ="true";
            boolean te = false;
            //if()

          /* if(te) {
               te = true;
           }*/
           /*if("true".equalsIgnoreCase(res)) {
               test.setText("foooo");
               Toast.makeText(MainActivity.this, "testing.....", Toast.LENGTH_SHORT).show();
               te=true;
           }
            if(te)
            {
                test.setText(res);
               editor.putString(user_name, name);
                editor.putString(status,res);
                editor.commit();
                Toast.makeText(MainActivity.this, "  j "+res, Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(this,home.class);
                startActivity(i1);

           }*/


    }
        

    }
    void setr(String s)
    {
        res=s;

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
    public  void display()
    {
        boolean te=false;
        String ttrue= "\"true\"";
        int testbool=res.trim().compareTo(ttrue);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Toast.makeText(MainActivity.this, "testing....."+testbool, Toast.LENGTH_SHORT).show();
        if(testbool==0)
        {
            test.setText(res);
            editor.putString(user_name, name);
            editor.putString(status,res);
            editor.commit();
            Toast.makeText(MainActivity.this, "  j "+res, Toast.LENGTH_SHORT).show();
            Intent i1=new Intent(this,home.class);
            startActivity(i1);

        }


    }

    class myclass
    {
        String ur,n1,p1;
        myclass(String u,String n,String p)
        {
            ur=u;
            n1=n;
            p1=p;
            
        }

    }
    class RequestTask extends AsyncTask<myclass, Void , String>{

        public  MainActivity activity;
        public RequestTask(MainActivity a)
        {
            this.activity=a;

        }
        protected String doInBackground(myclass ... m1)  {
            JSONArray response = new JSONArray();
            String responseString="";
            HttpURLConnection conn=null;
            String name1="";
            String pass1="";
            try {
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                       // test.setText("hello1");
                        Toast.makeText(MainActivity.this, "hello1..", Toast.LENGTH_SHORT).show();
                    }
                });

               // test.setText("hello1");
               // url = new URL(params[0]);
                URL url = new URL(m1[0].ur);
                name1=m1[0].n1;
                pass1=m1[0].p1;
                final String finalName = name1;
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                    //    test.setText(uri[1]);
                        Toast.makeText(MainActivity.this, " welcome..."+finalName, Toast.LENGTH_SHORT).show();
                    }
                });
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                       // test.setText("hello2");
                    }
                });
          /*  List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstParam", paramValue1));
            params.add(new BasicNameValuePair("secondParam", paramValue2));
            params.add(new BasicNameValuePair("thirdParam", paramValue3));*/
               // ContentValues values=new ContentValues();
                //values.put("username", name);
                //values.put("password", pass);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                final String data=URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(name1,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass1, "UTF-8");
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                       test.setText(data);
                    }
                });
                writer.write(data);
                writer.flush();
                writer.close();
                os.close();

               // conn.connect();
                // return responseString;
            //    urlConnection = (HttpURLConnection) url.openConnection();
                int responseCode = conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    responseString = readStream(conn.getInputStream());
                    Log.v("CatalogClient", responseString);
                   // JSONObject j=new JSONObject(responseString);
                   //response = new JSONArray(responseString);

                }else{
                    Log.v("CatalogClient", "Response code:"+ responseCode);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(conn != null)
                    conn.disconnect();
            }
            return responseString;

        }
        @Override
        protected void onPostExecute(final String result) {
            super.onPostExecute(result);
            MainActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    res = result;
                    String ttrue= "\"true\"";
                    int testbool=res.trim().compareTo(ttrue);
                    // test.setText(uri[1]);
                    Toast.makeText(MainActivity.this, " welcome..." + result+" "+res+" "+ttrue+testbool, Toast.LENGTH_SHORT).show();
                    // display();
                }
            });
                activity.display();

            //Do anything with response..
          //  test.setText(result);
        }
    }
    private String getQuery(ContentValues params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, Object> entry : params.valueSet())
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }

        return result.toString();
    }
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}


