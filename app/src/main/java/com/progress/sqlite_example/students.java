package com.progress.sqlite_example;

public class students {
	Integer _id;
	String _name, _student_id;
	
	public students(){}
	
	public students(Integer id, String name, String student_id) {
		this._id= id;
		this._name= name;
		this._student_id= student_id;
	}

	public Integer get_id() {
		return _id;
	}
	
	public void set_id(Integer _id) {
		this._id = _id;
	}
	
	public String get_name() {
		return _name;
	}
	
	public void set_name(String _name) {
		this._name = _name;
	}
	
	public String get_student_id() {
		return _student_id;
	}
	
	public void set_student_id(String _student_id) {
		this._student_id = _student_id;
	}
}
