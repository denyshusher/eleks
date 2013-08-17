package com.elrain.downloadtest;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class TodoShowActivity extends Activity {

	private TextView showTodo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_show);
		
		showTodo = (TextView) findViewById(R.id.outputTodo);
		
		Intent intent = getIntent();
		StringBuilder builder = new StringBuilder();
		builder.append(Variables.TodoHead + intent.getStringExtra(Variables.headCol) + "\n");
		builder.append(Variables.TodoDescr + intent.getStringExtra(Variables.descriptionCol) + "\n");
		builder.append(Variables.TodoDate + intent.getStringExtra(Variables.dateCol) + "\n");
		
		showTodo.setText(builder);
	}
}
