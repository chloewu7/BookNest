package user_manage.data_access;

import search.entity.Book;
import user_manage.entity.CollectionList;
import user_manage.entity.CollectionListFactory;
import user_manage.entity.CommonCollectionList;
import user_manage.entity.Review;
import user_manage.service.collection_management.add_book.AddBookDataAccessInterface;
import user_manage.service.collection_management.create_list.CreateListDataAccessInterface;
import user_manage.service.collection_management.show_all_lists.ShowAllListsDataAccessInterface;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileCollectionDataAccessObject implements AddBookDataAccessInterface, CreateListDataAccessInterface,
        ShowAllListsDataAccessInterface, ShowBooksInListDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> header = new LinkedHashMap<>();
    private Map<String, Map<String, Map<String, String>>> userLists = new HashMap<>(); //<userName, listsMap>
    private CollectionListFactory collectionListFactory;

    public FileCollectionDataAccessObject(File csvFile, CollectionListFactory collectionListFactory) throws IOException {
        this.csvFile = csvFile;
        this.collectionListFactory = collectionListFactory;

        header.put("userName", 0);
        header.put("listName", 1);
        header.put("bookTitle", 2);
        header.put("bookAuthor", 3);

        if (csvFile.length() == 0){
            save();
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
                String headers = reader.readLine();

                assert headers.equals("userName,listName,bookTitle,bookAuthor");

                String currentRow;
                while ((currentRow = reader.readLine()) != null){
                    String[] listInfo = currentRow.split(",");
                    String userName = String.valueOf(listInfo[header.get("userName")]);
                    String listName = String.valueOf(listInfo[header.get("listName")]);
                    String bookTitle = String.valueOf(listInfo[header.get("bookTitle")]);
                    String bookAuthor = String.valueOf(listInfo[header.get("bookAuthor")]);

                    Map<String,String> bookInfo = new HashMap<>();
                    bookInfo.put(bookTitle, bookAuthor);
                    if (userLists.containsKey(userName)){
                        if (userLists.get(userName).containsKey(listName)){
                            Map<String, Map<String, String>> lists = userLists.get(userName);
                            lists.get(listName).put(bookTitle, bookAuthor);
                        }else{
                            Map<String, Map<String, String>> lists = userLists.get(userName);
                            lists.put(listName,bookInfo);
                        }
                    }else{
                        Map<String, Map<String, String>> list = new HashMap<>();
                        list.put(listName, bookInfo);
                        userLists.put(userName, list);
                    }
                }
            }
        }
    }    @Override
    public List<String> getListsName(String userName) {
        if(!userLists.containsKey(userName)){
            createCollectionList(userName, "Like");
            return new ArrayList<>(userLists.get(userName).keySet());
        }
        Set<String> nameSet = userLists.get(userName).keySet();
//        for (String x : nameSet) {
//            nameList.add(x);
//        }
        return new ArrayList<>(nameSet);
    }

    @Override
    public void createCollectionList(String userName, String listName) {
        Map<String, Map<String, String>> newList = new HashMap<>();
        Map<String, String> emptyBook = new HashMap<>();
        emptyBook.put(" "," ");
        if (userLists.containsKey(userName)){
            userLists.get(userName).put(listName, emptyBook);
            save();
        }else{
            userLists.put(userName,newList);
            userLists.get(userName).put(listName, emptyBook);
            save();
        }
    }

    @Override
    public void addToCollectionList(String userName, String listName, String bookTitle, String bookAuthor) {
        if (!userLists.containsKey(userName)) {
            return;
        }
        if (!userLists.get(userName).containsKey(listName)){
            return;
        }
        Map<String, Map<String, String>> lists =
                userLists.get(userName);
        Map<String, String> books = lists.get(listName);
        books.put(bookTitle, bookAuthor);
        save();
    }



    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))){
            writer.write(String.join(",", header.keySet()));
            writer.newLine();

            for (String userName: userLists.keySet()){
                for (String listName: userLists.get(userName).keySet()) {
                    Map<String, String> books = userLists.get(userName).get(listName);
                    for(String bookTitle: books.keySet()){
                        String bookAuthor = books.get(bookTitle);
                        writer.write(String.format("%s,%s,%s,%s",
                                userName,listName, bookTitle, bookAuthor));
                        writer.newLine();
//                            if (bookTitle == null){
//                                writer.write(String.format("%s,%s,%s,%s",
//                                        userName,listName, "", ""));
//                                writer.newLine();
//                            }

                    }

                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Map<String, Map<String, Map<String, String>>> getUserLists() {
        return userLists;
    }

    @Override
    public Map<String, String> getBooksInlist(String userName, String listName) {
        Map<String, String> books = new HashMap<>();
        if (!userLists.containsKey(userName)) {
            return books;
        }if (!userLists.get(userName).containsKey(listName)) {
            return books;
        }
        Map<String, Map<String, String>> lists =
                userLists.get(userName);
        books = lists.get(listName);
        return books;
    }


}
