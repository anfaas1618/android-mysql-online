package com.anfaas.realsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    int x;
TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
