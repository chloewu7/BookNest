package search.service.search_request;

public class SearchRequestOutputData {
    private final String keyword;
    public SearchRequestOutputData(String keyword){
        this.keyword = keyword;
    }
    public String getKeyword(){return keyword;}
}
