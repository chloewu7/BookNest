package search.entity;

import user_manage.entity.Review;

import java.util.List;

public interface Book {
    String getTitle();
    String getAuthor();
    String getCategory();
    String getISBN();
    float getRating();
    List<Review> getReviewList();
    void setRating(Integer newRating);

    void setReviewList(Review newReview);

}
