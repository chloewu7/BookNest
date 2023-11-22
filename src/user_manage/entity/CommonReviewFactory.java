package user_manage.entity;

import search.entity.Book;

import java.time.LocalDateTime;

public class CommonReviewFactory implements ReviewFactory{
    /**
     * Requires: password is valid.
     * @param reviewedBook
     * @param bookAuthor
     * @param rating
     * @param reviewContent
     * @return
     */
    public Review create(String reviewedBook, String bookAuthor, Integer rating,
                         String reviewContent, LocalDateTime creationTime){
        return new CommonReview(reviewedBook, bookAuthor, rating, reviewContent,
                creationTime);
    }
}
