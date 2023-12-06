package user_manage.service.login;

import user_manage.entity.User;

public interface LoginDataAccessInterface {

    void saveNewUser(User user);

    User get(String name);

    boolean existsByName(String identifier);
}
