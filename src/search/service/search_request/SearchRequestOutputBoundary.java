package search.service.search_request;

public interface SearchRequestOutputBoundary {
    void sendRequestView(SearchRequestOutputData keyword);
    void invalidKeywordView(String error);
}
