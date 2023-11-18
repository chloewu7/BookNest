package search.service;

public class SearchInputData {
    final private String choice;
    final private String keyword;

    public SearchInputData(String choice, String keyword){
        this.choice = choice;
        this.keyword = keyword;
    }
    public String getChoice(){return choice;}
    public String getKeyword(){return keyword;}
}
