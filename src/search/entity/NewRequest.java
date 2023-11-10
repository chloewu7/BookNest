package search.entity;

public class NewRequest implements Request{
    private final String keyword;
    NewRequest(String keyword){
        this.keyword = keyword;
    }
    public String getRequestKeyword(){return keyword;}
}
