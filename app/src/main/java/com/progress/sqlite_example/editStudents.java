package com.progress.sqlite_example;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editStudents extends AppCompatActivity {
	EditText edit_name_input, edit_id_input;
	Button btn_edit_send;
	dbHandler db;
	Integer id;
	private final String TAG= "editStudents";

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.edit_students);
	
	db= new dbHandler(this);
	
	edit_name_input= (EditText) findViewById(R.id.edit_name_input);
	edit_id_input= (EditText) findViewById(R.id.edit_id_input);
	btn_edit_send= (Button) findViewById(R.id.btn_edit_send);
	
	Bundle b= getIntent().getExtras();
	id= b.getInt("id");
	
	students studentDetail= db.getStudentDetail(id);
	edit_name_input.setText(studentDetail.get_name());
	edit_id_input.setText(studentDetail.get_student_id());
	
	btn_edit_send.setOnClickListener(v->{
		String empty_message= "Harap isi semua form.";
		String name= edit_name_input.getText().toString().trim();
		String student_id= edit_id_input.getText().toString().trim();
		
		if (name.isEmpty()){
			makeToast(empty_message);
			edit_name_input.requestFocus();
			return;
		}
		
		if (student_id.isEmpty()){
			makeToast(empty_message);
			edit_name_input.requestFocus();
			return;
		}
		
		makeToast("Data berhasil diedit.");
		db.updateStudent(id, name, student_id);
		finish();
	});
	
}

public void makeToast(String message) {
	Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
}
}