package user_manage.entity;


import java.time.LocalDateTime;

public interface Review {
    String getReviewedBook();

    String getBookAuthor();

    String getReviewer();

    Integer getRating();

    String getReviewContent();

    LocalDateTime getCreationTime();
}
