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
                // Define the API endpoint URL for book search
                String apiUrl = "https://openlibrary.org/search.json?title=";

                // Specify the book title you want to search for
                String bookTitle = "Java Programming";

                // Encode the book title for the URL
                String encodedTitle = URLEncoder.encode(bookTitle, "UTF-8");

                // Create the full API URL by combining the base URL and the encoded title
                String fullApiUrl = apiUrl + encodedTitle;

                // Create a URL object
                URL url = new URL(fullApiUrl);

                // Open a connection to the URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set the request method to GET
                connection.setRequestMethod("GET");

                // Get the response code
                int responseCode = connection.getResponseCode();

                // Check if the response code indicates success (HTTP 200)
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

                    // Print the API response
                    System.out.println(response.toString());
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
