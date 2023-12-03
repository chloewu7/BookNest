package user_manage.entity;

public class CommonHistory implements History {

    private final String bookName;

    public CommonHistory(String bookName){
        this.bookName = bookName;
    }

    @Override
    public String getBookName() {
        return this.bookName;
    }
}
