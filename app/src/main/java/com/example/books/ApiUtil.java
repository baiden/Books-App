package com.example.books;


import android.net.Uri;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE_API_URL =
            "https://www.googleapis.com/books/v1/volumes";
    public static final String QUERY_PARAMETER_KEY = "q";
    public static final String KEY = "key=";
    public static final String API_KEY = "AIzaSyBLGxRN44kOgLSYV7X_46jcWrXGPSg-fVs";

    public static URL buildUrl(String title){

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY, title)
                .appendQueryParameter(KEY, API_KEY)
                .build();
        try{
            url = new URL(uri.toString());
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
