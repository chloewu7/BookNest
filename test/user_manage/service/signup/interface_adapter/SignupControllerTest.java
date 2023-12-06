package user_manage.service.signup.interface_adapter;

import org.junit.jupiter.api.Test;
import user_manage.service.signup.SignupInputBoundary;
import user_manage.service.signup.SignupInputData;

import static org.junit.jupiter.api.Assertions.*;

class SignupControllerTest {

    private class SignupInteractorMock implements SignupInputBoundary {

        @Override
        public void execute(SignupInputData signupInputData) {
            assertEquals("name",signupInputData.getUsername());
            assertEquals("p",signupInputData.getPassword());
            assertEquals("rp",signupInputData.getRepeatedpassword());
        }
    }

    @Test
    void testExecute() {
        SignupInteractorMock signupInteractorMock = new SignupInteractorMock();
        SignupController signupController = new SignupController(signupInteractorMock);
        signupController.execute("name", "p", "rp");
    }
}