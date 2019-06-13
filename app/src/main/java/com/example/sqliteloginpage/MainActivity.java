package com.example.sqliteloginpage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button btnreg,btnlogin;
    EditText txtfname,txtlname,txtpass,txtemail,txtphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper=new DatabaseHelper(this);
        btnreg=(Button) findViewById(R.id.btnreg);
        txtfname=(EditText) findViewById(R.id.txtfname);
        txtlname=(EditText) findViewById(R.id.txtlname);
        txtpass=(EditText) findViewById(R.id.txtpass);
        txtemail=(EditText) findViewById(R.id.txtemail);
        txtphone=(EditText) findViewById(R.id.txtphone);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fname=txtfname.getText().toString();
                String lname=txtlname.getText().toString();
                String pass=txtpass.getText().toString();
                String email=txtemail.getText().toString();
                String phone=txtphone.getText().toString();
                insertdata(fname,lname,pass,email,phone);
                Toast.makeText(getApplicationContext(),"register successfully",Toast.LENGTH_LONG).show();

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });

    }
    public void insertdata(String fname,String lname,String pass,String email,String phone){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,fname);
        contentValues.put(DatabaseHelper.COL_3,lname);
        contentValues.put(DatabaseHelper.COL_4,pass);
        contentValues.put(DatabaseHelper.COL_5,email);
        contentValues.put(DatabaseHelper.COL_6,phone);
        long id=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }
}
