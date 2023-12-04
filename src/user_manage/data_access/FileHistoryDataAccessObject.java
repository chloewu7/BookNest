package user_manage.data_access;

import user_manage.entity.History;
import user_manage.entity.HistoryFactory;
import user_manage.entity.User;
import user_manage.service.history.add_history.AddingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileHistoryDataAccessObject implements AddingHistoryDataAccessInterface, ReadingHistoryDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> header = new LinkedHashMap<>();
    private Map<String, List<String>> historyByUser = new HashMap<>();

    private HistoryFactory historyFactory;

    public FileHistoryDataAccessObject(File csvFile, HistoryFactory historyFactory) throws IOException {
        this.csvFile = csvFile;
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
                    String bookName = historyInfo[header.get("book_name")];

                    History history = historyFactory.create(bookName);
                    List<String> userHistoryList = historyByUser.getOrDefault(username, new ArrayList<>());
                    userHistoryList.add(history.getBookName());
                    historyByUser.put(username, userHistoryList);

                }
            }
        }
    }

    @Override
    public void addHistoryToUser(User user, History history) {
        List<String> userHistoryList = historyByUser.getOrDefault(user.getName(), new ArrayList<>());
        userHistoryList.add(history.getBookName());
        historyByUser.put(user.getName(), userHistoryList);
        save();
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(String.join(",", header.keySet()));
            writer.newLine();

            for (Map.Entry<String, List<String>> entry : historyByUser.entrySet()) {
                for (String bookName : entry.getValue()) {
                    writer.write(String.format("%s,%s", entry.getKey(), bookName));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getHistoryByUserId(String userId) {
        return historyByUser.getOrDefault(userId, new ArrayList<>());
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }
}
