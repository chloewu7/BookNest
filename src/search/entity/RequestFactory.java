package search.entity;

public interface RequestFactory {
    /** Requires: keyword is valid. */
     Request create(String choice, String keyword);
}
