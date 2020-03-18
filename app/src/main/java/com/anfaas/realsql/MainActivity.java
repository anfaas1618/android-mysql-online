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
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final String CREATE_TABLE_SQL="CREATE TABLE users ("
            + "UID INT NOT NULL,"
            + "NAME VARCHAR(45) NOT NULL,"
            +"password VARCHAR(45) NOT NULL,"
            + "EMAIL VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (UID))";
    EditText name,email,password;
    Button register;
    int x;
TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="insert into users(UID,NAME,password,EMAIL)" +
                        "VALUES(" +
                        12+",\"" +
                        name.getText().toString()+"\",\""+password.getText().toString()+"\",\""
                        +email.getText().toString()+"\")";
                Log.i("STring",s);
                regiserSQL();
            }
        });
        text=findViewById(R.id.textView);
     AsyncTask<Void, Void, Integer> f=   new Task().execute();
        try {
            text.setText("ger"+f.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     void regiserSQL() {
        new Register().execute();

    }
    class  Register extends  AsyncTask<Void,Void,Void>
    {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection(
                        "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12328500","sql12328500","cF4cq9Efn3");
                Statement stmt=con.createStatement();
                stmt.executeUpdate("insert into users(UID,NAME,password,EMAIL)" +
                        "VALUES(" +
                        12+",\"" +
                        name.getText().toString()+"\",\""+password.getText().toString()+"\",\""
                        +email.getText().toString()+"\")"
                );
                con.close();
            }catch(Exception e){ System.out.println(e);}

            return null;
        }
    }
    class  Task extends AsyncTask<Void, Void, Integer>
    {
        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            text.setText(""+x);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection(
                        "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12328500","sql12328500","cF4cq9Efn3");
                //register your own online database at freemysqlhosting.net and chage the above url,username,password,database name.
                Statement stmt=con.createStatement();
                ResultSet set=   stmt.executeQuery("select * from anfi");//there was a table created by me by name anfi
                set.next();
                 x=  set.getInt(1);//it has only one row with int value.

                System.out.println(x);
                Log.i("her",""+x);
                con.close();
            }catch(Exception e){ System.out.println(e);}


            return  x;
        }
    }


}
