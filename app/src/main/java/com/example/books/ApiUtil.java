package com.example.books;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE__API_URL =
            "https://www.googleapis.com/books/v1/volumes";

    public static URL buildUrl(String title){
        String fullUrl = BASE__API_URL + "?q=" + title;

        URL url = null;
        try{
            url = new URL(fullUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException{

        // Creates an HTTP connection with the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            // Gets the connection stream
            InputStream stream = connection.getInputStream();

            // Converts stream to a string
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            // Checks if there is data
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            }else {
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {

            // Closes the HTTP connection
            connection.disconnect();
        }
    }

}
