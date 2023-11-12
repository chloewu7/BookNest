package search.service.search_criteria;

import search.entity.Request;

public class SearchCriteriaInteractor implements SearchCriteriaInputBoundary{

    final Request request;

    public SearchCriteriaInteractor(Request request) {
        this.request = request;
    }

    @Override
    public void execute(SearchCriteriaInputData searchCriteriaInputData) {
        switch (request.getSearchCriteria()) {
            case "Title":
                return;
            case "Author":
                return;
            case "Subject":
                return;
            case "ISBN":
                return;

        }
    }
}
