package search.entity;

public class NewRequestFactory implements RequestFactory{
    public Request create(String keyword){return new NewRequest(keyword);}
}
