package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


// better to make static all the methods in utils.Utils class
public class Utils {


    public static String randomPass(){   // length 10
        Faker faker = new Faker();
        String m=faker.bothify("?#??##?@");
        String n=faker.bothify("??").toUpperCase();
        String password = m + n;
        return password;
    }

    static String filePath = "./src/test/resources/users.json";

    public static void saveInJSON(String firstName, String lastName, String username, String empID, String password) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("username", username);
        jsonObject.put("empID", empID);
        jsonObject.put("password", password);

        jsonArray.add(jsonObject);

        FileWriter writer = new FileWriter(filePath);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }

    // read JSON data
    public static JSONArray readJSON() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        return jsonArray;
    }

    // scrolling
    public static void doScroll(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //bottom
        js.executeScript("window.scrollBy(0, document.body.scrollHeight)");  // 0 = initial pixel  //  = last pixer
       // 250 pixel
//        js.executeScript("window.scrollBy(0, 250)");  // 0 = initial pixel  //  = last pixer
        // till the element appears
//        js.executeScript("\"arguments[0].scrollIntoView();\", Element");  // 0 = initial pixel  //  = last pixer
    }

}
