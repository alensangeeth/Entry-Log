package com.example.entrylog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Log extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;
    String apiurl="http://10.0.4.16:3000/api/students";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);
        ed1=(EditText)findViewById(R.id.name);
        ed2=(EditText)findViewById(R.id.adnum);
        ed3=(EditText)findViewById(R.id.sysnum);
        ed4=(EditText)findViewById(R.id.depart);
        b1=(AppCompatButton)findViewById(R.id.addtorec);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=ed1.getText().toString();
                String getAdno=ed2.getText().toString();
                String getSysno=ed3.getText().toString();
                String getDept=ed4.getText().toString();
                //JSON OBJECT CREATION
                JSONObject student=new JSONObject();
                try {
                    student.put("name",getName);
                    student.put("admission_number",getAdno);
                    student.put("system_number",getSysno);
                    student.put("department",getDept);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //JSON OBJECT REQ CREATION
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST, apiurl, student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "LOG ADDEDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                //REQ QUEUE
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);

                Toast.makeText(getApplicationContext(),"Log Added Successfully",Toast.LENGTH_SHORT).show();
            }
        });
        b2=(AppCompatButton) findViewById(R.id.logout);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}