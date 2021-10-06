package com.progress.sqlite_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private static final String TAG= "MainActivity";
ListView students_list;
ArrayAdapter<String> arr;
List<students> students_data;
dbHandler openDb;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	openDb= new dbHandler(this);
	
	students_list= (ListView) findViewById(R.id.students_list);
	
	students_list.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
		menu.setHeaderTitle("pilih menu");
		menu.add(0,0,0, "Edit");
		menu.add(0,1,1, "Hapus");
	});
	
	setListView();
	
}

@Override
protected void onResume() {
	setListView();
	super.onResume();
}

public void toAddStudents(View view) {
	startActivity(new Intent(getApplicationContext(), addStudents.class));
}

public void setListView() {
	students_data= openDb.getStudents();
	List<String> students_name= new ArrayList<>();
	
	for (students student: students_data){
		students_name.add(student.get_name());
	}
	arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, students_name);
	
	students_list.setAdapter(arr);
}

@Override
public boolean onContextItemSelected(@NonNull MenuItem item){
	AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
	Integer id;
	
	switch (item.getItemId()){
		case 0:
			id= students_data.get(info.position)._id;
			Intent i= new Intent(getApplicationContext(), editStudents.class);
			i.putExtra("id", id);
			startActivity(i);
			return true;
		case 1:
			id= students_data.get(info.position)._id;
			openDb.deleteStudent(id);
			setListView();
			return true;
		default:
			return super.onContextItemSelected(item);
	}
}

}