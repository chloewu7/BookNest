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

    @Override
    public Review create(String reviewedBook, String bookAuthor, String reviewer,  Integer rating, String reviewContent, LocalDateTime creationTime) {
        return new CommonReview(reviewedBook, bookAuthor, reviewer, rating, reviewContent, creationTime);
    }
}
