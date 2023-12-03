package user_manage.data_access;

import user_manage.entity.CommonHistory;
import user_manage.entity.History;
import user_manage.entity.User;
import user_manage.service.history.add_history.AddingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHistoryDataAccessObject implements AddingHistoryDataAccessInterface, ReadingHistoryDataAccessInterface {

    private final String filePath;
    private final FileUserDataAccessObject userDataAccessObject;

    public FileHistoryDataAccessObject(String filePath, FileUserDataAccessObject userDataAccessObject) {
        this.filePath = filePath;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public User getUserByName(String username) {
        return userDataAccessObject.getUserByName(username);
    }

    @Override
    public void addHistoryToUser(User user, History history) {
        try (FileWriter fw = new FileWriter(filePath, true);
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
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
}