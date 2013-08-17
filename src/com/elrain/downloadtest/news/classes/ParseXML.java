package com.elrain.downloadtest.news.classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.elrain.downloadtest.Variables;

public class ParseXML{

	private static SAXParserFactory factory = null;
	static SAXParser saxParser = null;
	private Reader reader = null;
	private List<Map<String,String>> newsList = new ArrayList<Map<String,String>>();
	private Map<String,String> newsMap = new HashMap<String, String>();
	private String urlAdress = "";
	
	public ParseXML(String _urlAdress){
		this.urlAdress = _urlAdress;
	}
	
	public List<Map<String,String>> getNews(){
		try{
			URL url = new URL(urlAdress);
			URLConnection urlConnection = url.openConnection();
			
			InputStream inputStream = urlConnection.getInputStream();
			reader = new InputStreamReader(inputStream,Variables.encodingType);
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		 
		InputSource is = new InputSource(reader);
		is.setEncoding(Variables.encodingType);
		
		factory = SAXParserFactory.newInstance();
		try{
			saxParser = factory.newSAXParser(); 
			DefaultHandler handler = new DefaultHandler(){
				boolean bguid = false;
				boolean blink = false;
				boolean btitle = false;
				boolean bdescription = false;
				boolean bitem = false;
				boolean bpubdate = false;
				boolean bmedia = false;
			 
				public void startElement(String uri, String localName,String qName, Attributes attributes) 
						throws SAXException {
					
					if(qName.equalsIgnoreCase(Variables.item)){
						bitem = true;
						newsMap = new HashMap<String, String>();
					}
						
					if (qName.equalsIgnoreCase(Variables.guid)) 
						bguid = true;		
					if (qName.equalsIgnoreCase(Variables.link) && bitem)
						blink = true;
					if (qName.equalsIgnoreCase(Variables.title) && bitem)
						btitle = true;
					if (qName.equalsIgnoreCase(Variables.description) && bitem)
						bdescription = true;
					if (qName.equalsIgnoreCase(Variables.pubDate) && bitem)
						bpubdate = true;
					if (qName.equalsIgnoreCase(Variables.media) && bitem){
						bmedia = true;
						newsMap.put(Variables.mediaUrl, attributes.getValue(Variables.mediaUrl));
						newsMap.put(Variables.mediaHeight, attributes.getValue(Variables.mediaHeight));
						newsMap.put(Variables.mediaWidth, attributes.getValue(Variables.mediaWidth));
					}
						
				}
			 
				public void endElement(String uri, String localName, String qName) 
						throws SAXException {
					if(qName.equalsIgnoreCase(Variables.item)){
						bitem = false;
						newsList.add(newsMap);
					}
				}
			 
				public void characters(char ch[], int start, int length) 
						throws SAXException {
					
					if(bitem){
						if (bguid) {
							newsMap.put(Variables.guid, new String(ch, start, length));
							bguid = false;
						}
				 
						if (blink) {
							newsMap.put(Variables.link,new String(ch, start, length));
							blink = false;
						}
						
						if (bpubdate){
							newsMap.put(Variables.pubDate, new String(ch, start, length));
							bpubdate = false;
						}
							
						if (btitle) {
						newsMap.put(Variables.title, new String(ch, start, length));
							btitle = false;
						}
				 
						if (bdescription) {
							String line = divideDescription(new String(ch, start, length));
							newsMap.put(Variables.description, line);
							bdescription = false;
						}
						if (bmedia)
							bmedia = false;
					}
					
				}
			};
			
			try {
				saxParser.parse(is, handler);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		catch(ParserConfigurationException ex){
			ex.printStackTrace();
		}
		catch (SAXException ex) {
			ex.printStackTrace();
		}
		
		return newsList;
	}
	
	private String divideDescription(String line){
		StringBuilder stB = new StringBuilder();
		String[] ret = null;
		ret = line.split("<br>");
		if(ret.length > 1){
            for(int i=0; i<ret.length; ++i){
                stB.append(ret[i]+"\n");
            }
            ret[0] = stB.toString();
        }
		ret = ret[0].split("&gt;");
		if(ret.length > 1){
			stB = new StringBuilder();
            for(int i=0; i<ret.length; ++i){
                stB.append(ret[i]+">");
            }
            ret[0] = stB.toString();
        }
		ret = ret[0].split("&lt;");
		if(ret.length > 1){
			stB = new StringBuilder();
            for(int i=0; i<ret.length; ++i){
                stB.append(ret[i]+"<");
            }
            ret[0] = stB.toString();
        }
		ret = ret[0].split("&quot;");
		if(ret.length > 1){
			stB = new StringBuilder();
            for(int i=0; i<ret.length; ++i){
                stB.append(ret[i]+"\"");
            }
            ret[0] = stB.toString();
        }
		
		return ret[0];
	}
		
}
