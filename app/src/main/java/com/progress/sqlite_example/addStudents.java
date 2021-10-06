package com.progress.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addStudents extends AppCompatActivity {
	EditText add_name_input, add_id_input;
	Button btn_add_send;
	dbHandler db;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.add_students);
	
	db= new dbHandler(this);
	
	add_name_input= (EditText) findViewById(R.id.edit_name_input);
	add_id_input= (EditText) findViewById(R.id.edit_id_input);
	btn_add_send= (Button) findViewById(R.id.btn_edit_send);
	
	btn_add_send.setOnClickListener(v->{
		String empty_message= "Harap isi semua form.";
		String name= add_name_input.getText().toString().trim();
		String id= add_id_input.getText().toString().trim();
		
		if (name.isEmpty()){
			makeToast(empty_message);
			add_name_input.requestFocus();
			return;
		}
		
		if (id.isEmpty()){
			makeToast(empty_message);
			add_name_input.requestFocus();
			return;
		}

		db.addStudent(name, id);
		makeToast("Data berhasil ditambahkan");
		finish();
	});
	
}

public void makeToast(String message) {
	Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
}
}