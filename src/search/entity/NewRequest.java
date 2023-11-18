package search.entity;

public class NewRequest implements Request{
    private final String searchChoice;
    private final String keyword;

    NewRequest(String searchChoice, String keyword){
        this.keyword = keyword;
        this.searchChoice = searchChoice;
    }
    public String getChoice(){return searchChoice;}
    public String getRequestKeyword(){return keyword;}
}
