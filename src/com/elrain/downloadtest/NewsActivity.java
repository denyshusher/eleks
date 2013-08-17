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

	private void goToNewWindow() {
    	Intent secondActiv = new Intent(getApplicationContext(), BashActivity.class);
		startActivity(secondActiv);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bashButton:
			goToNewWindow();
			break;
		case R.id.bbcButton:
			goToNewWindow2();
			break;
		default:
			break;
		}
		
	}
	
	private void goToNewWindow2() {
    	Intent secondActiv = new Intent(getApplicationContext(), BbcListActivity.class);
		startActivity(secondActiv);
	}
}
