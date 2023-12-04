package view.UserCenter;

import user_manage.service.signup.interface_adapter.SignupState;

public class UserCenterState {

    private String username;

    public UserCenterState(UserCenterState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public UserCenterState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
