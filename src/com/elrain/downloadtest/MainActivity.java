package com.elrain.downloadtest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity{
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        
        tabSpec = tabHost.newTabSpec(Variables.tab1Head);
        tabSpec.setIndicator(Variables.tab1Title);
        tabSpec.setContent(new Intent(this, NewsActivity.class));
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec(Variables.tab2Head);
        tabSpec.setIndicator(Variables.tab2Title);
        tabSpec.setContent(new Intent(this, TodoActivity.class));
        tabHost.addTab(tabSpec);    
    }
}
