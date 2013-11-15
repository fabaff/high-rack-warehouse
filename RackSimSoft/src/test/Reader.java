package test;

import java.io.IOException;
import helper.*;

public class Reader {

	public static void main(String[] args) throws IOException {
		String article_list = "article_list.txt";
		
		
		
		System.out.println("Reading files and printing the content:");
		
		ReadingFiles reader = new ReadingFiles();
	    reader.readArticles(article_list);
	    
	    
	}

}
