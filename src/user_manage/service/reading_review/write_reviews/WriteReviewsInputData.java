package user_manage.service.reading_review.write_reviews;

import search.entity.Book;

public class WriteReviewsInputData {
    final private String reviewedBook;
    final private String reviewContent;
    final private String author;
    final private String reviewer;
    final private Integer rating;

    public WriteReviewsInputData(String reviewedBook, String author, String reviewer, String reviewContent, Integer rating){
        this.reviewedBook = reviewedBook;
        this.author = author;
        this.reviewer = reviewer;
        this.reviewContent = reviewContent;
        this.rating = rating;
    }

    public String getReviewedBook() {
        return reviewedBook;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Integer getRating(){
        return rating;
    }
    public String getReviewer() {
        return reviewer;
    }
    public String getAuthor() {
        return author;
    }
}
