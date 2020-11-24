package com.example.thuctap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String mUsername, mPassword;
    private RelativeLayout navURL,navAdd,navAbout,navRemove;
    private EditText edtPassWord, edtUserName ;
    private Button btnLogin ;
    private String text = "";
//    private View v;
    private Users users ;
    ArrayList<Users> arrayListUser = new ArrayList<>();
    ArrayList<String> Listusername = new ArrayList<>();
//    String  TAG = "vvv" ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        initializeAll();

    }

    private void initializeAll() {
        edtPassWord = findViewById(R.id.edtPassWord) ;
        edtUserName = findViewById(R.id.edtUserName) ;
        btnLogin = findViewById(R.id.btnLogin) ;
        navURL = (RelativeLayout) findViewById(R.id.navURL);
        navAdd = (RelativeLayout) findViewById(R.id.navAdd);
        navRemove = (RelativeLayout) findViewById(R.id.navRemove);
        navAbout = (RelativeLayout) findViewById(R.id.navAbout);
        navURL.setOnClickListener(this);
        navAbout.setOnClickListener(this);
        navAdd.setOnClickListener(this);
        navRemove.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            //if you want to use fragment you can use that on each option i just use a toast message
            case R.id.navURL:
                showMessage("Navigation URL");
                closeDrawer();
                break;
            case R.id.navAdd:
                showMessage("Navigation Add");
                closeDrawer();
                break;
            case R.id.navRemove:
                showMessage("Navigation Remove");
                closeDrawer();
                break;
            case R.id.navAbout:
                showMessage("Navigation About");
                closeDrawer();
                break;
            case R.id.btnLogin:
                checkLogin();
                break;

          /*  case R.id.navUrlRl:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://lazycoder.6te.net/"));
                startActivity(browserIntent);
                break;
*/
        }
    }

    public void closeDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (drawer.isDrawerOpen(GravityCompat.END)){
            drawer.closeDrawer(GravityCompat.END);
        }
    }

    public void showMessage(String message){
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    // checkLogin
    private void checkLogin() {
        mUsername = edtUserName.getText().toString() ;
        mPassword = edtPassWord.getText().toString() ;
        RequestQueue queue ;
        queue = Volley.newRequestQueue(this);
        // readJSON
        StringRequest request = new StringRequest(Request.Method.GET, "http://171.244.141.28/Acumatica/Login?userName=demouser&passWord=1234", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("text",response) ;
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);
                        String Username = object.getString("Username");
                            Log.d("text",Username) ;
                            Listusername.add(Username) ;
                        if (((mUsername.equals("admin") == true)||(mUsername.equals(Username) == true)) && mPassword.equals("1234") == true) {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,ScanActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });
        queue.add(request);
    }
}



