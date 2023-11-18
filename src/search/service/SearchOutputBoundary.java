package search.service;

public interface SearchOutputBoundary {
    void sendResponseView(SearchOutputData response);
    void invalidRequestView(String error);
}
