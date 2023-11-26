package search.data_access;

import search.entity.BookFactory;
import search.entity.CommonBookFactory;
import search.service.SearchDataAccessInterface;
import search.entity.Book;
import java.util.List;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchDataAccessObject implements SearchDataAccessInterface {
    private static final String API_URL = "https://openlibrary.org/search.json";
    public List<Book> searchByTitle(String title){
        return search("title", title);
    }
    public List<Book> searchByAuthor(String author){
        return search("author", author);
    }
    public List<Book> searchByCategory(String category){
        return search("subject", category);
    }
    public List<Book> searchByISBN(String ISBN){
        return search("isbn", ISBN);
    }

    private List<Book> search(String field, String value){
        String urlString = API_URL + "?" + field + "=" + encodeValue(value);
        String response = makeApiCall(urlString);
        return parseBooksFromResponse(response);
    }

    private String makeApiCall(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200){
                throw new RuntimeException("HttpResponseCode:" + responseCode);
            }
            else{
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                return informationString.toString();
            }
        } catch(IOException e){
            throw new RuntimeException("Error during API calling", e);
        }
    }

    private String encodeValue(String value){
        try{
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e){
            throw new RuntimeException("Error encoding URL parameter", e);
        }
    }

    private List<Book> parseBooksFromResponse(String response){
        JSONObject jsonObject = new JSONObject(response);
        JSONArray docs = jsonObject.getJSONArray("docs");

        List<Book> books = new ArrayList<>();
        BookFactory bookFactory = new CommonBookFactory();

        for(int i= 0; i < docs.length(); i++){
            JSONObject jsonBook = docs.getJSONObject(i);

            String title = jsonBook.optString("title");
            String author = jsonBook.optJSONArray("author_name") != null ?
                    jsonBook.optJSONArray("author_name").join(", ").replaceAll("\"", "")
                    : "Unknown Author";
            String category = jsonBook.optJSONArray("subject") != null ?
                    jsonBook.optJSONArray("subject").join(", ").replaceAll("\"", "")
                    : "Unknown Category";
            String isbn = jsonBook.optJSONArray("isbn") != null ? jsonBook.optJSONArray("isbn").
                    getString(0) : "Unknown ISBN";

            Book book = bookFactory.createBook(title, author, category, isbn);
            books.add(book);
        }
        return books;
    }


}
