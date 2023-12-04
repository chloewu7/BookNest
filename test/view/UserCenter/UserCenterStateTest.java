package view.UserCenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCenterStateTest {

    private UserCenterState userCenterState;

    @BeforeEach
    public void setUp() {
        userCenterState = new UserCenterState();
    }

    @Test
    void getUsername() {
        String expectedUsername = "testUser";

        userCenterState.setUsername(expectedUsername);
        String actualUsername = userCenterState.getUsername();

        assertEquals( expectedUsername, actualUsername, "Username should match the set value");
    }

    @Test
    void setUsername() {
        String expectedUsername = "testUser";

        userCenterState.setUsername(expectedUsername);
        String actualUsername = userCenterState.getUsername();

        assertEquals( expectedUsername, actualUsername, "Username should match the set value");
    }
}