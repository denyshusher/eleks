package com.elrain.downloadtest;

import java.util.Date;

import com.elrain.downloadtest.todo.DBHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class TodoActivity extends Activity implements OnClickListener{

	private EditText todoHeadEditText;
	private EditText todoTextEditText;
	private Button todoAddButton;
	private DBHelper dbHelper;
	
	private void initInterface(){
		todoAddButton = (Button) findViewById(R.id.todo_add_button);
		todoTextEditText = (EditText) findViewById(R.id.todo_text_edit);
		todoHeadEditText = (EditText) findViewById(R.id.todo_head_edit);
		dbHelper = new DBHelper(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		
		initInterface();
		todoAddButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ContentValues contentValues = new ContentValues();
		
		String todoHead = todoHeadEditText.getText().toString();
		String todoDescription = todoTextEditText.getText().toString();
		String date = new Date().toString();
		
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		
		switch (v.getId()) {
		case R.id.todo_add_button:
			Log.d(Variables.logInsert, Variables.logInsertValue);
			
			contentValues.put("head", todoHead);
			contentValues.put("description", todoDescription);
			contentValues.put("createDate", date);
			
			long rowId = database.insert("todoTable", null, contentValues);
			Log.d(Variables.logInsert, Variables.logInsertValue + " " + rowId + " " + contentValues.toString());
			
			break;

		default:
			break;
		}
		
	}
}
