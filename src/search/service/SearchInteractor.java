package search.service;

import search.entity.Book;
import search.entity.Response;
import search.entity.ResponseFactory;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary {
    final SearchDataAccessInterface searchDataAccessObject;
    final SearchOutputBoundary searchPresenter;
    final ResponseFactory responseFactory;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessObject,
                            SearchOutputBoundary searchPresenter,
                            ResponseFactory responseFactory){
        this.searchDataAccessObject = searchDataAccessObject;
        this.searchPresenter = searchPresenter;
        this.responseFactory = responseFactory;
    }
    public void execute(SearchInputData searchInputData){
        String choice = searchInputData.getChoice();
        String keyword = searchInputData.getKeyword();
        List<Book> books = null;

        try{
            switch(choice){
                case "Title":
                    books = searchDataAccessObject.searchByTitle(keyword);
                    break;
                case "Author":
                    books = searchDataAccessObject.searchByAuthor(keyword);
                    break;
                case "Category":
                    books = searchDataAccessObject.searchByCategory(keyword);
                    break;
                case "ISBN":
                    books = searchDataAccessObject.searchByISBN(keyword);
                    break;
            }
            if (books == null || books.isEmpty()){
                searchPresenter.prepareNotFoundView("No books found for this given search criteria");
            }
            else{
                Response response = responseFactory.create(books);
                SearchOutputData searchOutputData = new SearchOutputData(response.getResponse(),
                        false);
                searchPresenter.prepareSuccessView(searchOutputData);
            }

        } catch (Exception e){
            searchPresenter.prepareFailView("Search failed, please try again.");
        }




    }

}
