package user_manage.data_access;

import user_manage.entity.User;
import user_manage.entity.UserFactory;
import user_manage.service.signup.SignupDataAccessInterface;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> header = new LinkedHashMap<>();
    private final Map<String, User> userAccounts = new HashMap<>();
    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvFilePath, UserFactory userFactory) throws IOException {
        this.csvFile = new File(csvFilePath);
        this.userFactory = userFactory;

        header.put("username", 0);
        header.put("password", 1);
        header.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
                String headers = reader.readLine();

                assert headers.equals("username,password,creation_time");

                String currentRow;
                while((currentRow = reader.readLine()) != null){
                    String[] userinfo = currentRow.split(",");
                    String username = String.valueOf(userinfo[header.get("username")]);
                    String password = String.valueOf(userinfo[header.get("password")]);
                    String creationTimeText = String.valueOf(userinfo[header.get("creation_time")]);
                    LocalDateTime creationTime = LocalDateTime.parse(creationTimeText);

                    User user = userFactory.create(username, password, creationTime);
                    userAccounts.put(username, user);
                }
            }
        }
    }
    /**
     * Return whether a user exists with username identifier.
     * @param username the username to check.
     * @return whether a user exists with username
     */
    @Override
    public boolean existsByName(String username) {
        return userAccounts.containsKey(username);
    }

    @Override
    public void saveNewUser(User newuser) {
        userAccounts.put(newuser.getName(), newuser);
        save();
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))){
            writer.write(String.join(",", userAccounts.keySet()));
            writer.newLine();

            for (User users : userAccounts.values()){
                writer.write(String.format("%s,%s,%s",
                        users.getName(), users.getPassword(), users.getCreationTime()));
                writer.newLine();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

}
