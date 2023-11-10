package user_manage.service.signup;

public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatedpassword;

    public SignupInputData(String name, String password, String repeatpasword){
        this.username = name;
        this.password = password;
        this.repeatedpassword = repeatpasword;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRepeatedpassword(){
        return repeatedpassword;
    }
}
