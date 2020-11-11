package com.example.thuctap;

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
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String mUsername, mPassword, mDate, mActive ;
    private RelativeLayout navURL,navAdd,navAbout,navRemove;
    private EditText edtPassWord ;
    private Button btnLogin ;
    private View v;
    private Users users = null;
    ArrayList<Users> arrayListUser = new ArrayList<>();
    private String text = "";
    String  TAG = "vvv" ;





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


//        new RetrieveFeedTask().execute("");
        initializeAll();

    }

    private void initializeAll() {
        edtPassWord = findViewById(R.id.edtPassWord) ;
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
                new RetrieveFeedTask().execute("");
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


    // getDataJSon
    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {
                users = new Users();
                try {
                    URL url = new URL("http://171.244.141.28/API/Values/getmethod02");
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.connect();
                    int responsecode = conn.getResponseCode();

                    if (responsecode != 200)
                    {
                        text = ("HTTP get error: " + responsecode);
                    }
                    else{
                        Scanner scanner = new Scanner(url.openStream());
                        while (scanner.hasNext())
                        {
                            text+=scanner.nextLine();
                            Gson gson = new Gson();
                            users = gson.fromJson(text, Users.class);
//                            Log.d("aaa",users.getPassword()) ;
                            mPassword = users.getPassword() ;

                        }
                    }
                } catch (MalformedURLException e) {
                    text = e.getLocalizedMessage();
                } catch (IOException e) {
                    text = e.getLocalizedMessage();
                }
                System.out.println(text);
                return text;
            } catch (Exception e) {
                this.exception = e;
                return null;
            } finally {

            }
        }

        protected void onPostExecute(String feed) {
            if (edtPassWord.getText().toString().equals(mPassword) == true ) {
                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,ScanActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        }
    }


}


/*
/                LoadRSSFromInternetTask loadRSSFromInternetTask = new LoadRSSFromInternetTask(this);
//                loadRSSFromInternetTask.execute("https://firebasestorage.googleapis.com/v0/b/demofirebaseandroid-768a3.appspot.com/o/APIValuesEInvoiceAction.xml?alt=media&token=d833d843-0af6-45c8-a809-084d188fdfeb");
//                loadRSSFromInternetTask.execute("http://171.244.141.28:80/API/Values/getmethod02");
//                loadRSSFromInternetTask.execute("http://192.168.0.108/trongxml/demo.rss");
* */

// getData -  mk:123144
/*class LoadRSSFromInternetTask extends AsyncTask<String, Long, ArrayList<Users>> {

    private Context context;

    LoadRSSFromInternetTask(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList<Users> doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(url.openConnection().getInputStream(), "UTF-8");

            int eventType = xpp.getEventType();
            String text = "";


            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nameTag = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:

                        Log.e("UserPassWord", xpp.getName());
                        if (nameTag.equalsIgnoreCase("UserPassWord")) {
                            users = new Users();
                            Log.e("CREATE", "NEWS");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (nameTag.equals("UserPassWord")) {
                            arrayListUser.add(new Users());
                        } else if (users != null & nameTag.equalsIgnoreCase("Active")) {
                            mActive = text.trim();
                            users.setActive(mActive);
                        }else if (users != null & nameTag.equalsIgnoreCase("DateLoggin")) {
                            mDate = text.trim();
                            users.setDateLoggin(mDate);
                        }else if (users != null & nameTag.equalsIgnoreCase("Password")) {
                            mPassword = text.trim();
                            users.setPassword(mPassword);
//                                Log.d("bassword",mPassword) ;
                        }else if (users != null & nameTag.equalsIgnoreCase("Username")) {
                            mUsername = text.trim();
                            users.setUsername(mUsername);
                        }
                        Log.e("END_TAG " + nameTag, text + "");
                        break;
                    default:
                        break;
                }
                eventType = xpp.next(); //move to next element
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//            Log.d("arrayListUser", String.valueOf(arrayListUser.size())) ;
        return arrayListUser;
    }

    @Override
    protected void onPostExecute(ArrayList<Users> arrayList) {
        super.onPostExecute(arrayList);
        if (edtPassWord.getText().toString().equals(mPassword) == true ) {
            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context,ScanActivity.class));
        } else {
            Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
        }
           Log.d("arrayListUser", String.valueOf(arrayListUser.size())) ;
            Log.d("mPassword",mPassword) ;
            for (int i = 0; i < arrayListUser.size(); i++) {
                Log.d("mpassword", String.valueOf(arrayListUser.contains(i))) ;
                if (edtPassWord.getText().toString().equals(arrayListUser.get(i).getPassword()) == true ) {
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
                    i ++ ;
                }
            }
    }
}*/

