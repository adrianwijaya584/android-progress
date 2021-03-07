package com.example.testapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        EditText input1 = (EditText) findViewById(R.id.input1   );
        TextView text1 = (TextView) findViewById(R.id.text1);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Apakah anda ingin keluar aplikasi ?");
        builder.setTitle("keluar app");

        builder.setPositiveButton("Keluar ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text1.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),coba2.class);

                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog al = builder.create();

                al.show();

            }
        });

    } // onCreate

    @Override
    public void onBackPressed() {
//        Toast.makeText(getApplicationContext(),"kembali",Toast.LENGTH_SHORT).show();
//        super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Apakah Anda Yakin Ingin Keluar Aplikasi ?").setTitle("Keluar App").setNegativeButton("Kembali",null);

        builder.setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        AlertDialog al = builder.create();

        al.show();

    }
} // MainActivity