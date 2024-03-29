package com.elrain.downloadtest;

public class Variables {

// global variables
	public static String bash 			=		"bash";
	public static String bbc 			=		"bbc";
	public static String path 			= 		"path";
	public static String bashURL		=		"http://bash.im/rss/";
	public static String bbcURL			=		"http://feeds.bbci.co.uk/news/rss.xml"; 
	public static String filePath		=		"file.xml";

//xml variables
	public static String title			=		"title";
	public static String guid			=		"guid";
	public static String description	=		"description";
	public static String pubDate		=		"title";
	public static String link			=		"link";
	public static String item			=		"item";
	public static String media			=		"media:thumbnail";
	public static String mediaUrl		=		"url";
	public static String mediaWidth		=		"width";
	public static String mediaHeight	=		"height";
	
//tab variables
	public static String tab1Head		=		"tag1";
	public static String tab2Head		=		"tag2";
	public static String tab1Title		=		"Новости";
	public static String tab2Title		=		"Задачи";
	
//log variables
	public static String logCreateTable			=		"create table";
	public static String logCreateTableValue	=		"onCreate database";
	public static String logInsert				=		"insert";
	public static String logInsertValue			=		"Insert in todoTable";
	public static String logSelect				=		"select";
	public static String logSelectValue			=		"Select * from todoTable";
	
//db variables
	public static String dbName			=		"DBTodo";
	public static String tableName		=		"todoTable";
	public static String idCol			=		"id";
	public static String headCol		=		"head";
	public static String descriptionCol	=		"description";
	public static String dateCol		=		"createDate";
	
//strings to toast
	public static String enterTodoHead	=		"Введите тему";
	public static String enterTodoDescr	=		"Введите содержание";
	public static String todoAdd		=		"Задача добавлена";

//todo Strings
	public static String TodoHead	=		"Тема: ";
	public static String TodoDescr	=		"Описание: ";
	public static String TodoDate	=		"Дата создания: ";
	
//image
	public static String imageDir	=		"/NewsApp/bbc/";
	public static String imageName	=		"/image";
	public static String imageType	=		".jpg";
	
//exceptions
	public static String fileNotFound	=		"File not found";
	
//encoding type
	public static String encodingType	=	"windows-1251";
}
