package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDataReader {
	public String searchQuery ,pageNumberTwo,pageNumberThree;
	String JSONFILEPATH = LoadProperties.paths.getProperty("jsonFilePath");
	/**
	 * Read Json File by take a instance of File and send the filePath To It 
	 * Then take instance of JSONParser to parse the file using FileReader 
	 * Put the result of parse in a new instance of JSONArray Then loop in it to get 
	 * the required item
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public  void jsonReader() throws FileNotFoundException, IOException, ParseException  {
		String filePath = System.getProperty("user.dir")+JSONFILEPATH;
		File srcFile = new File(filePath); 
		JSONParser parser = new JSONParser(); 
		JSONArray jarray = (JSONArray)parser.parse(new FileReader(srcFile)); 
		for(Object jsonObj : jarray) 
		{
			JSONObject key = (JSONObject) jsonObj ; 
			searchQuery  = (String) key.get("searchQuery"); 
			System.out.println(searchQuery);
			
			pageNumberTwo  = (String) key.get("pageTwo"); 
			System.out.println(pageNumberTwo);
			 
			pageNumberThree  = (String) key.get("pageThree"); 
			System.out.println(pageNumberThree);
		}

	}
}
