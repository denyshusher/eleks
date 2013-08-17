package com.elrain.downloadtest;

import java.util.Date;

import com.elrain.downloadtest.dao.impl.TodoDaoImpl;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class TodoActivity extends Activity implements OnClickListener{

	private EditText todoHeadEditText;
	private EditText todoTextEditText;
	private Button todoAddButton;
	private Button todoViewButton;
	private TodoDaoImpl todoDaoImpl;
	
	private void initInterface(){
		todoAddButton = (Button) findViewById(R.id.todo_add_button);
		todoViewButton = (Button) findViewById(R.id.todo_view_button);
		todoTextEditText = (EditText) findViewById(R.id.todo_text_edit);
		todoHeadEditText = (EditText) findViewById(R.id.todo_head_edit);
		todoDaoImpl = new TodoDaoImpl(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		
		initInterface();

		todoAddButton.setOnClickListener(this);
		todoViewButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ContentValues contentValues = new ContentValues();		
		
		String todoHead = todoHeadEditText.getText().toString();
		String todoDescription = todoTextEditText.getText().toString();
		String date = new Date().toString();
		
		SQLiteDatabase database = todoDaoImpl.getWritableDatabase();
		
		switch (v.getId()) {
		case R.id.todo_add_button:
			contentValues.put(Variables.headCol, todoHead);
			contentValues.put(Variables.descriptionCol, todoDescription);
			contentValues.put(Variables.dateCol, date);

			int retValue = todoDaoImpl.addTodo(contentValues, database);
			
			switch (retValue) {
			case 0:
				Toast.makeText(getApplicationContext(), 
						Variables.todoAdd, Toast.LENGTH_LONG).show();
				break;
			case 1:	
				Toast.makeText(getApplicationContext(), 
	                    Variables.enterTodoHead, Toast.LENGTH_LONG).show();
				break;
			case 2:	
				Toast.makeText(getApplicationContext(), 
	                    Variables.enterTodoDescr, Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
			
			break;
		case R.id.todo_view_button:
			Log.d(Variables.logSelect, Variables.logSelectValue);
			
			goToNewWindow();
			
			break;
		default:
			break;
		}
		
	}
	
	private void goToNewWindow() {
    	Intent secondActiv = new Intent(getApplicationContext(), TodoListActivity.class);
		startActivity(secondActiv);
	}
}
