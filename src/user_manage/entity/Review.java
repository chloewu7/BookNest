package user_manage.entity;


import java.time.LocalDateTime;

public interface Review {
    String getReviewedBook();

    String getBookAuthor();

    Integer getRating();

    String getReviewContent();

    LocalDateTime getCreationTime();
}
