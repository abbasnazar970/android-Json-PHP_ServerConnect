package com.maximus.server_test;

import android.app.DownloadManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText name, ph, email;
    Button save, show;
    TextView View;
    RequestQueue requestqueue;

    String insertURL = "http://192.168.100.15/TEST/insert.php";
    String readURL = "http://192.168.100.15/TEST/read.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText);
        ph = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);

        save = (Button) findViewById(R.id.button);
        show = (Button) findViewById(R.id.button2);

        View = (TextView) findViewById(R.id.textView);


        requestqueue = Volley.newRequestQueue(getApplicationContext());

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, readURL
                        , new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {

                            JSONArray Employe = response.getJSONArray("employees");
                            for (int i = 0; i < Employe.length(); i++) {
                                JSONObject temp = Employe.getJSONObject(i);


                                View.append("Name : " + temp.getString("name") + "\nPhone : " + temp.getString("phone") + "\nEmail : " + temp.getString("email"));


                            }

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast t=Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT);
                        t.show();

                    }
                });

                requestqueue.add(request);


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast t=Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT);
                        t.show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("name", name.getText().toString());
                        parameters.put("email", email.getText().toString());
                        parameters.put("phone", ph.getText().toString());

                        return parameters;

                    }
                };

                requestqueue.add(request);
            }
        });
    }


}
