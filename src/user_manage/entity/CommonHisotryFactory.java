package user_manage.entity;

public class CommonHisotryFactory implements HistoryFactory {
    @Override
    public History create(String bookName) {
        return new CommonHistory(bookName);
    }
}
