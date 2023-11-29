package user_manage.data_access;

import user_manage.entity.CommonUser;
import user_manage.entity.User;
import user_manage.service.history.add_history.AddingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FileHistoryDataAccessObject implements AddingHistoryDataAccessInterface, ReadingHistoryDataAccessInterface {

    private final String filePath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final FileUserDataAccessObject userDataAccessObject;


    public FileHistoryDataAccessObject(String filePath, FileUserDataAccessObject userDataAccessObject) {
        this.filePath = filePath;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public User getUserByName(String username) {
        if (userDataAccessObject.getUserByName(username) == null){
            return null;}
        else{
            return userDataAccessObject.getUserByName(username);}
    }


    @Override
    public void addHistoryToUser(User user, String historyRecord) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            String record = user.getName() + "," + LocalDateTime.now().format(formatter) + "," + historyRecord;
            out.println(record);
        } catch (IOException e) {
            System.err.println("Error writing to history file: " + e.getMessage());
        }
    }

    @Override
    public Map<LocalDateTime, String> getHistoryByUserId(String userId) {
        Map<LocalDateTime, String> historyRecords = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 2 && parts[0].equals(userId)) {
                    LocalDateTime timestamp = LocalDateTime.parse(parts[1], formatter);
                    String historyDetail = String.join(",", parts[2], parts[3]); // Assuming the rest of the parts are the history details
                    historyRecords.put(timestamp, historyDetail);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from history file: " + e.getMessage());
        }
        return historyRecords;
    }
}