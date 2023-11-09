package user_manage.service.signup;

import user_manage.entity.User;

public interface SignupDataAccessInterface {
    boolean existsByName(String username);

    void saveNewUser(User newuser);
}
