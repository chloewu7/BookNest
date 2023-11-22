package user_manage.service.reading_review.write_reviews;

import search.entity.Book;

public class WriteReviewsInputData {
    final private Book reviewedBook;

    final private String reviewContent;

    final private Integer rating;

    public WriteReviewsInputData(Book reviewedBook, String reviewContent, Integer rating){
        this.reviewedBook = reviewedBook;
        this.reviewContent = reviewContent;
        this.rating = rating;
    }

    public Book getReviewedBook() {
        return reviewedBook;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Integer getRating(){
        return rating;
    }
}
