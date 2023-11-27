package search.service;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData response);
    void prepareNotFoundView(String message);
    void prepareFailView(String error);
}
