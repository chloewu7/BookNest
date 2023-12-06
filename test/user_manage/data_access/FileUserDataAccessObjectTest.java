package user_manage.data_access;

import org.junit.jupiter.api.Test;
import user_manage.entity.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileUserDataAccessObjectTest {
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private User user = new CommonUser("ezpz4.0", "password", LocalDateTime.of(2003, 7, 24, 12, 0));

    private class MockUserFactory implements UserFactory {

        @Override
        public User create(String name, String password, LocalDateTime ltd) {
            assertEquals("ezpz4.0", name);
            assertEquals("password", password);
            assertEquals(LocalDateTime.of(2003, 7, 24, 12, 0).format(formatter), ltd.format(formatter));
            return user;
        }
    }

    @Test
    void testSaveForConstructor(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./userTest.csv"))){
            Map<String, Integer> header = new LinkedHashMap<>();
            header.put("username", 0);
            header.put("password", 1);
            header.put("creation_time", 2);
            writer.write(String.join(",", header.keySet()));
            writer.newLine();


            writer.write(String.format(String.format("%s,%s,%s",
                    "ezpz4.0", "password", LocalDateTime.of(2003, 7, 24, 12, 0))));
            writer.newLine();

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        try {
            MockUserFactory mockUserFactory = new MockUserFactory();
            FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject("./userTest.csv", mockUserFactory);
            assertTrue(fileUserDataAccessObject.existsByName("ezpz4.0"));
            assertEquals(user, fileUserDataAccessObject.get("ezpz4.0"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //TODO:

    @Test
    void testBehaviorAfterSaveNewUser(){
        UserFactory userFactory = new CommonUserFactory();
        try {
            User user1 =  userFactory.create("user1", "password", LocalDateTime.of(2018, 9, 9, 9, 0));
            FileUserDataAccessObject userDAO2 = new FileUserDataAccessObject("./userDAOTest2.csv", userFactory);
            try (BufferedReader reader = new BufferedReader(new FileReader("./userDAOTest2.csv"))) {
                assertEquals("username,password,creation_time", reader.readLine());
            }
            userDAO2.deleteAll();
            assertFalse(userDAO2.existsByName("user1"));
            userDAO2.saveNewUser(user1);
            assertTrue(userDAO2.existsByName("user1"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}