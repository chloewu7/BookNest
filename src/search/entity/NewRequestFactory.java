package search.entity;

public class NewRequestFactory implements RequestFactory{
    public Request create(String searchChoice, String keyword){
        return new NewRequest(searchChoice, keyword);}
}
