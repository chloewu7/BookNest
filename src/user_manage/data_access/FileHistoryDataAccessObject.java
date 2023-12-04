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

    private final Map<String,Integer> header = new LinkedHashMap<>();

    private ArrayList<String> historyDatabase = new ArrayList<>();

    private Map<String, List<String>> historyByUser = new HashMap<>();
    private final HistoryFactory historyFactory;

    public FileHistoryDataAccessObject(File csvFilePath, HistoryFactory historyFactory) throws IOException {
        this.csvFile = csvFilePath;
        this.historyFactory = historyFactory;

        header.put("username", 0);
        header.put("book_name", 1);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String headers = reader.readLine();

                assert headers.equals("username,book_name");

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
    /*
    @Override
    public void addHistoryToUser(User user, History history) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(user.getName() + "," + history.getBookName());
        } catch (IOException e) {
            System.err.println("Error writing to history file: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getHistoryByUserId(String userId) {
        ArrayList<String> historyRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[0].equals(userId)) {
                    History history = new CommonHistory(parts[1]); // Create a CommonHistory object
                    historyRecords.add(history.getBookName());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from history file: " + e.getMessage());
        }
        return historyRecords;
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }
}

     */