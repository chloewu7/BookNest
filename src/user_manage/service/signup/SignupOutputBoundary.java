package user_manage.service.signup;

public interface SignupOutputBoundary {
    void prepareLoginView(SignupOutputData response);

    void prepareFailView(String error);
}
