package com.example.entrylog;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    AppCompatButton b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.uname);
        ed2=(EditText) findViewById(R.id.pass);
        b1=(AppCompatButton) findViewById(R.id.login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getuname=ed1.getText().toString();
                String getpass=ed2.getText().toString();
                if(getuname.equals("admin")&&getpass.equals("12345"))
                {
                    SharedPreferences preferance=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferance.edit();
                    editor.putString("user","admin");
                    editor.apply();
                    Intent in=new Intent(getApplicationContext(), Log.class);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid Credential", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}