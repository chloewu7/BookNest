package user_manage.service.collection_management.create_list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateListOutputDataTest {

    @Test
    void testGetListNames() {
        List<String> listNames = List.of("List1", "List2", "List3");

        CreateListOutputData createListOutputData = new CreateListOutputData(listNames);

        assertEquals(listNames, createListOutputData.getListNames());
    }
}
