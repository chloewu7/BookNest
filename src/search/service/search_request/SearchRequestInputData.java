package search.service.search_request;

public class SearchRequestInputData {
    final private String choice;
    final private String keyword;

    public SearchRequestInputData(String keyword, String choice){
        this.keyword = keyword;
        this.choice = choice;
    }

    public String getKeyword(){return keyword;}
    public String getChoice(){return choice;}
}
