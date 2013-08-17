package com.elrain.downloadtest.news.classes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.elrain.downloadtest.Variables;

public class DownloadImageFile {
	public static Drawable getImageFromUrl(String url) throws MalformedURLException, IOException{
		File root = Environment.getExternalStorageDirectory();
		return Drawable.createFromStream((InputStream) new URL(url).getContent(), root + Variables.imageDir);
	}
}
