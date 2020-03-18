package com.anfaas.realsql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
  EditText email,password;
  TextView name_t;
  Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.l_email);
        password=findViewById(R.id.l_pass);
        login_btn=findViewById(R.id.login);
        name_t=findViewById(R.id.name_txt);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CheckLogin().execute();
            }
        });
    }
    class  CheckLogin extends AsyncTask<Void,Void,Void>
    {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12328500","sql12328500","cF4cq9Efn3");
           Statement stmt = connection.createStatement();
           String a="select * from users where EMAIL=\"" +
                   email.getText().toString() +"\"";
                Log.i("loa",a);
       ResultSet set=    stmt.executeQuery(a);
       set.next();
      name_t.setText(set.getString(2));
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
