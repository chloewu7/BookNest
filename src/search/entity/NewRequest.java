package search.entity;

public class NewRequest implements Request{
    private final String keyword;

    private final String searchCriteria;
    NewRequest(String keyword, String searchCriteria){
        this.keyword = keyword;
        this.searchCriteria = searchCriteria;
    }
    public String getRequestKeyword(){return keyword;}
    public String getSearchCriteria(){return searchCriteria;}
}
