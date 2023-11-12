package search.entity;

public class NewRequestFactory implements RequestFactory{
    public Request create(String keyword, String searchCriteria){
        return new NewRequest(keyword, searchCriteria);}
}
