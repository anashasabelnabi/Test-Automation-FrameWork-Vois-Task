package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	/**
     * Read Properties File Using FileInputStream That take the path of the
     * Properties file then load the data in the instance of properties then return this instance.
     * 
     * @param Path 		path of properties file
     */

	private static Properties loadProperties(String Path) {

		Properties properties = new Properties() ;
		try {
			FileInputStream stream = new FileInputStream(Path);
			properties.load(stream);
		} catch (FileNotFoundException e) {
			System.out.println("Error Message" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error Message" + e.getMessage());
		}
		return properties;
	}
	
	/**
     * executionType return instance of Execution.properties File To Manage The Execution by the user
     */
	public static Properties executionType = 
			loadProperties(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Execution.properties");
	
	/**
     * paths return instance of Path.properties File To get any path 
     */
	public static Properties paths = 
			loadProperties(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Paths.properties");

}
