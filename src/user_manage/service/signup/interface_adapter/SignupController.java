package user_manage.service.signup.interface_adapter;

import user_manage.service.signup.SignupInputBoundary;
import user_manage.service.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary signupInputBoundary;

    public SignupController(SignupInputBoundary signupInputBoundary){
        this.signupInputBoundary = signupInputBoundary;
    }

    private void execute(String username, String password, String repeatPassword){
        SignupInputData signupInputData = new SignupInputData(username, password, repeatPassword);

        signupInputBoundary.execute(signupInputData);
    }
}
