package com.elrain.downloadtest;

import java.util.Date;

import com.elrain.downloadtest.todo.DBHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TodoActivity extends Activity implements OnClickListener{

	private EditText todoHeadEditText;
	private EditText todoTextEditText;
	private Button todoAddButton;
	private Button todoViewButton;
	private DBHelper dbHelper;
	private TextView tasksView;
	
	private void initInterface(){
		todoAddButton = (Button) findViewById(R.id.todo_add_button);
		todoViewButton = (Button) findViewById(R.id.todo_view_button);
		todoTextEditText = (EditText) findViewById(R.id.todo_text_edit);
		todoHeadEditText = (EditText) findViewById(R.id.todo_head_edit);
		tasksView = (TextView) findViewById(R.id.tasksView);
		dbHelper = new DBHelper(this);
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
		
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		
		switch (v.getId()) {
		case R.id.todo_add_button:
			Log.d(Variables.logInsert, Variables.logInsertValue);
			
			contentValues.put(Variables.headCol, todoHead);
			contentValues.put(Variables.descriptionCol, todoDescription);
			contentValues.put(Variables.dateCol, date);
			
			long rowId = database.insert(Variables.tableName, null, contentValues);
			Log.d(Variables.logInsert, Variables.logInsertValue + " " + rowId + " " + contentValues.toString());
			
			break;
		case R.id.todo_view_button:
			Log.d(Variables.logSelect, Variables.logSelectValue);
			
			Cursor cursor = database.query(Variables.tableName, null, null, null, null, null, null);
			if (cursor.moveToFirst()) {

		    	// определяем номера столбцов по имени в выборке
		        int idColIndex = cursor.getColumnIndex("id");
		        int headColIndex = cursor.getColumnIndex(Variables.headCol);
		        int descriptionColIndex = cursor.getColumnIndex(Variables.descriptionCol);
		        int dateColIndex = cursor.getColumnIndex(Variables.dateCol);

		        StringBuilder builder = new StringBuilder();
		        
		        do {
		        	builder.append(Variables.headCol + ": " + cursor.getString(headColIndex) + "\n");
		        	builder.append(Variables.descriptionCol + ": " + cursor.getString(descriptionColIndex) + "\n");
		        	builder.append(Variables.dateCol + ": " + cursor.getString(dateColIndex) + "\n");
		        	// получаем значения по номерам столбцов и пишем все в лог
		        	Log.d(Variables.logSelect,
		        			"ID = " + cursor.getInt(idColIndex) + 
		        			", head = " + cursor.getString(headColIndex) + 
		        			", description = " + cursor.getString(descriptionColIndex));
		        	// переход на следующую строку 
		        	// а если следующей нет (текущая - последняя), то false - выходим из цикла
		        } while (cursor.moveToNext());
		        tasksView.setText(builder.toString());
		    } 
		    else
		    	Log.d(Variables.logSelect, "0 rows");
		    cursor.close();	    
		    
			break;
		default:
			break;
		}
		
	}
}
