package user_manage.entity;

import search.entity.Book;

import java.time.LocalDateTime;

public class CommonReview implements Review{
    private final String reviewedBook;
    private final Integer rating;
    private final String bookAuthor;
    private final String reviewContent;
    private final LocalDateTime creationTime;

    public CommonReview(String reviewedBook, String bookAuthor, Integer rating,
                        String reviewContent, LocalDateTime creationTime){
        this.reviewedBook = reviewedBook;
        this.bookAuthor = bookAuthor;
        this.rating = rating;
        this.reviewContent = reviewContent;
        this.creationTime = creationTime;
    }

    @Override
    public String  getReviewedBook() {
        return reviewedBook;
    }

    @Override
    public String getBookAuthor() {
        return this.bookAuthor;
    }

    @Override
    public Integer getRating() {
        return this.rating;
    }

    @Override
    public String getReviewContent() {
        return reviewContent;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
