package api;

import entity.Search;
import entity.UserManagement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LibrarySystemImplement implements LibrarySystem{
    public static class OpenLibraryBookSearch {

        public static void main(String[] args) {
            try {
                String apiUrl = "https://openlibrary.org/search.json?title=";
                String bookTitle = "Java Programming";

                String encodedTitle = URLEncoder.encode(bookTitle, "UTF-8");
                String fullApiUrl = apiUrl + encodedTitle;
                URL url = new URL(fullApiUrl);  // Create a URL object

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET"); //request method to GET
                int responseCode = connection.getResponseCode(); // Get the response code

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Create a BufferedReader to read the response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    // Read the response line by line
                    while ((inputLine = reader.readLine()) != null) {
                        response.append(inputLine);
                    }
                    reader.close();

                    System.out.println(response.toString()); // Print the API response
                } else {
                    System.out.println("Error: " + responseCode);
                }

                // Close the connection
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
