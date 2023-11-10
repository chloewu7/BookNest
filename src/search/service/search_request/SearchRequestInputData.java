package search.service.search_request;

public class SearchRequestInputData {
    final private String keyword;

    public SearchRequestInputData(String keyword){
        this.keyword = keyword;
    }

    public String getKeyword(){return keyword;}
}
