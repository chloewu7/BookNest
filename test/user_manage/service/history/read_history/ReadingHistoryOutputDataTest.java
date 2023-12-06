package user_manage.service.history.read_history;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadingHistoryOutputDataTest {


    @Test
    void testGetHistory() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        ReadingHistoryOutputData outputData = new ReadingHistoryOutputData(list);

        List www = outputData.getHistory();
        assertEquals(list, www);


    }
}