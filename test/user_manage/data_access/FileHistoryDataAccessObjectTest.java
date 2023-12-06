package user_manage.data_access;

import org.junit.jupiter.api.Test;
import user_manage.entity.CommonHistory;
import user_manage.entity.History;
import user_manage.entity.HistoryFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileHistoryDataAccessObjectTest {

    private class MockHistoryFactory implements HistoryFactory{

        public History create(String bookName){


            return new CommonHistory(bookName);
        }
    }
    @Test
    void testConstuctor(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./historytest.csv"))){
            Map<String, Integer> header = new LinkedHashMap<>();
            header.put("username",0);
            header.put("bookname",1);
            writer.write(String.join(",", header.keySet()));
            writer.newLine();

            writer.write(String.format("%s,%s","123","mumu"));
            writer.newLine();
            writer.write(String.format("%s,%s","2","papa"));
            writer.newLine();}
        catch (IOException e){
            throw new RuntimeException(e);}
        try {
            MockHistoryFactory mockHistoryFactory = new MockHistoryFactory();
            mockHistoryFactory.create("mumu");
            mockHistoryFactory.create("papa");
            mockHistoryFactory.create("Ikea");
            FileHistoryDataAccessObject fileHistoryDataAccessObject = new
                    FileHistoryDataAccessObject("./historytest.csv", mockHistoryFactory);
            assertTrue(fileHistoryDataAccessObject.user_exist("123"));
            assertTrue(fileHistoryDataAccessObject.book_exist("123", "mumu"));
            assertTrue(fileHistoryDataAccessObject.book_exist("2","papa"));
            assertFalse(fileHistoryDataAccessObject.book_exist("123","papa"));
            //assertTrue(fileHistoryDataAccessObject.book_exist("123","papa"));
        }
        catch (IOException e ){
            throw new RuntimeException(e);


        }
    }
    @Test
    void addHistoryToUser() {
    }

    @Test
    void getHistoryByUserId() {
    }

    @Test
    void getUserByName() {
    }
}