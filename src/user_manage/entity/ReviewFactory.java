package user_manage.entity;

import search.entity.Book;

import java.time.LocalDateTime;

public interface ReviewFactory {
    Review create(String reviewedBook, String bookAuthor, Integer rating, String reviewContent,
                  LocalDateTime creationTime);
}
