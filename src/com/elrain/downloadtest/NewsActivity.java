package com.elrain.downloadtest;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class NewsActivity extends Activity implements OnClickListener{

	private Button bashBtn; 
	private Button bbcBtn;
	
	private void initInterface(){
		
		bashBtn = (Button) findViewById(R.id.bashButton);
		bbcBtn = (Button) findViewById(R.id.bbcButton);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		initInterface();
		
		bashBtn.setOnClickListener(this);
        bbcBtn.setOnClickListener(this);
	}

	private void goToNewWindow(String site) {
    	Intent secondActiv = new Intent(getApplicationContext(), NewsViewActivity.class);
		secondActiv.putExtra(Variables.path, site);
		startActivity(secondActiv);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bashButton:
			goToNewWindow(Variables.bash);
			break;
		case R.id.bbcButton:
			goToNewWindow(Variables.bbc);
			break;
		default:
			break;
		}
		
	}
}
