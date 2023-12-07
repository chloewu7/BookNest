package user_manage.data_access;

import org.junit.jupiter.api.Test;
import user_manage.entity.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileCollectionDataAccessObjectTest {
    private class MockCollectionListFactory implements CollectionListFactory {

        @Override
        public CollectionList createCollectionList(String listName, String userName, Map<String, String> books) {
            assertEquals(listName, "Chill");
            assertEquals(userName, "Monica");
            Map<String, String> actualBooks = new HashMap<>();
            actualBooks.put("912 Happy Family", "RongManJiao");
            assertEquals(books, actualBooks);
            return new CommonCollectionList("Monica", "Chill", actualBooks);
        }
    }
    @Test
    void testSaveForConstructor(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./collectionTest.csv"))){
            Map<String, Integer> header = new LinkedHashMap<>();
            header.put("userName", 0);
            header.put("listName", 1);
            header.put("bookTitle", 2);
            header.put("bookAuthor", 3);
            writer.write(String.join(",", header.keySet()));
            writer.newLine();

            writer.write(String.format("%s,%s,%s,%s", "Monica", "Chill", "912 Happy Family", "RongManJiao"));
            writer.newLine();

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        try {
            FileCollectionDataAccessObjectTest.MockCollectionListFactory mockCollectionListFactory =
                    new FileCollectionDataAccessObjectTest.MockCollectionListFactory();
            FileCollectionDataAccessObject fileCollectionDataAccessObject =
                    new FileCollectionDataAccessObject(new File("./collectionTest.csv"), mockCollectionListFactory);
            //assertTrue(fileCollectionDataAccessObject.getListsName("Monica").contains("Like"));
            assertTrue(fileCollectionDataAccessObject.getListsName("Monica").contains("Chill"));
            assertFalse(fileCollectionDataAccessObject.getListsName("Monica").contains("Unlike"));

            assertTrue(fileCollectionDataAccessObject.getBooksInlist("Monica", "Chill").
                    containsKey("912 Happy Family"));
            assertTrue(fileCollectionDataAccessObject.getBooksInlist("Monica", "Chill").
                    containsValue("RongManJiao"));
            //assertFalse(fileCollectionDataAccessObject.getBooksInlist("Monica", "Like").containsKey("RongManJiao"));
            //assertFalse(fileCollectionDataAccessObject.getBooksInlist("Monica", "Like").containsValue("912 Happy Family"));
            assertEquals("RongManJiao", fileCollectionDataAccessObject.getBooksInlist("Monica", "Chill").
                    get("912 Happy Family"));
            //assertNull(fileCollectionDataAccessObject.getBooksInlist("Monica", "Like").keySet());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void testBehaviorAfterCreateNew(){
        CollectionListFactory collectionListFactory = new CommonCollectionListFactory();
        try {
            Map<String,String> books1 = new HashMap<>();
            books1.put("ShuMing", "Zuozhe");
            CollectionList collectionList1 = new CommonCollectionList("Cara","Sleep",books1);

            Map<String,String> books2 = new HashMap<>();
            books2.put("English", "teacher");
            books2.put("Chinese", "laoshi");
            CollectionList collectionList2 = new CommonCollectionList("Cara","Study", books2);

//            Map<String,String> books3 = new HashMap<>();
//            CollectionList collectionList1 = new CommonCollectionList("Cara","Sleep",);
//            Map<String,String> books4 = new HashMap<>();
//            CollectionList collectionList1 = new CommonCollectionList("Cara","Sleep",);
//            Map<String,String> books5 = new HashMap<>();
//            CollectionList collectionList1 = new CommonCollectionList("Cara","Sleep",);
//            Map<String,String> books6 = new HashMap<>();
//            CollectionList collectionList1 = new CommonCollectionList("Cara","Sleep",);

            FileCollectionDataAccessObject fileCollectionDAO2 =
                    new FileCollectionDataAccessObject(new File("./collectionDAOTest2.csv"), collectionListFactory);
            fileCollectionDAO2.createCollectionList("Cara","Health");
            assertTrue(fileCollectionDAO2.getListsName("Cara").contains("Health"));

            fileCollectionDAO2.addToCollectionList("Cara","Health","Eat", "prof");
            assertTrue(fileCollectionDAO2.getBooksInlist("Cara", "Health").containsKey("Eat"));
            assertTrue(fileCollectionDAO2.getBooksInlist("Cara", "Health").containsValue("prof"));

            //fileCollectionDAO2.deleteAll();
            //assertNull(fileCollectionDAO2.getUserLists());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}

