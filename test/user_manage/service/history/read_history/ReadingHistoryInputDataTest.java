package user_manage.service.history.read_history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadingHistoryInputDataTest {

    ReadingHistoryInputData readingHistoryInputData = new ReadingHistoryInputData("wuhu", "1");

    @Test
    void getUserName() {
        ReadingHistoryInputData readingHistoryInputData = new ReadingHistoryInputData("nikick", "alpha");
        String actualusername = readingHistoryInputData.getUserName();

        assertEquals("nikick", actualusername, "user name should be nikick");
    }

    @Test
    void getBookName() {
        String aa = readingHistoryInputData.getBookName();
        assertEquals("1", aa, "bookname should be 1");
    }


}