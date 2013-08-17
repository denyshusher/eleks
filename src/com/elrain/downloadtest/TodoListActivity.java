package com.elrain.downloadtest;

import java.util.List;
import java.util.Map;

import com.elrain.downloadtest.dao.impl.TodoDaoImpl;
import com.elrain.downloadtest.todo.MyArrayAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class TodoListActivity extends ListActivity {

	private List<Map<String, String>> todoList;
	private TodoDaoImpl todoDaoImpl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		todoDaoImpl = new TodoDaoImpl(this);
		SQLiteDatabase database = todoDaoImpl.getWritableDatabase();
		todoList = todoDaoImpl.getAllTodo(database);
		
		setListAdapter(new MyArrayAdapter(this, 0, todoList));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent todoShowActivity = new Intent(getApplicationContext(), TodoShowActivity.class);
		todoShowActivity.putExtra(Variables.headCol, todoList.get(position).get(Variables.headCol));
		todoShowActivity.putExtra(Variables.descriptionCol, todoList.get(position).get(Variables.descriptionCol));
		todoShowActivity.putExtra(Variables.dateCol, todoList.get(position).get(Variables.dateCol));
		startActivity(todoShowActivity);
	}
}
