package com.elrain.downloadtest;

import com.elrain.downloadtest.news.classes.DownloadImageFile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class BbcShowActivity extends Activity {

	private ImageView imNews;
	private TextView tvTitle;
	private TextView newsDescrText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bbc_show);
		
		imNews = (ImageView) findViewById(R.id.newsImage);
		tvTitle = (TextView) findViewById(R.id.newsTitle);
		newsDescrText = (TextView) findViewById(R.id.newsDescription);
		
		Intent intent = getIntent();
		
		String title = intent.getStringExtra(Variables.title);
		String description = intent.getStringExtra(Variables.description);
		String imageUrl = intent.getStringExtra(Variables.mediaUrl);
		String link = intent.getStringExtra(Variables.link);
		
		try{
			imNews.setImageDrawable(DownloadImageFile.getImageFromUrl(imageUrl));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		tvTitle.setText(title);
		newsDescrText.setText(description + "\n\n" + link);
	}
}
