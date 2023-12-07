package user_manage.data_access;

import user_manage.entity.History;
import user_manage.entity.HistoryFactory;
import user_manage.entity.Review;
import user_manage.entity.User;
//import user_manage.service.history.add_history.AddingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileHistoryDataAccessObject implements ReadingHistoryDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> header = new LinkedHashMap<>();
    private Map<String, List<String>> historyByUser = new HashMap<>();

    private HistoryFactory historyFactory;

    public FileHistoryDataAccessObject(String csvFilepath, HistoryFactory historyFactory) throws IOException {
        this.csvFile = new File(csvFilepath);
        this.historyFactory = historyFactory;

        header.put("username", 0);
        header.put("bookname", 1);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String headers = reader.readLine();

                assert headers.equals("username,bookname");

                String currentRow;
                while ((currentRow = reader.readLine()) != null) {
                    String[] historyInfo = currentRow.split(",");
                    String username = historyInfo[header.get("username")];
                    String bookName = historyInfo[header.get("bookname")];

                    History history = historyFactory.create(bookName);
                    List<String> userHistoryList = historyByUser.getOrDefault(username, new ArrayList<>());
                    userHistoryList.add(history.getBookName());
                    historyByUser.put(username, userHistoryList);



                }
                }catch (NullPointerException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addHistoryToUser(String user, String bookName) {
        List<String> userHistoryList = new ArrayList<>();
        if (historyByUser.containsKey(user)){
            userHistoryList = historyByUser.get(user); }

        userHistoryList.add(bookName);

        historyByUser.put(user, userHistoryList);
        save();
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(String.join(",", header.keySet()));
            writer.newLine();

            for (String users : historyByUser.keySet()) {
                for (String books : historyByUser.get(users)) {
                    writer.write(String.format("%s,%s", users, books));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getHistoryByUserId(String userId) {
        return historyByUser.get(userId);
    }

    public boolean user_exist(String username){
        return historyByUser.containsKey(username);
    }

    public boolean book_exist(String user, String book){
        return historyByUser.get(user).contains(book);
    }
}
