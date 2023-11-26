package search.data_access;

import search.entity.Book;
import search.entity.BookFactory;
import user_manage.entity.Review;
import user_manage.entity.ReviewFactory;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class FileBookDataAccessObject implements ShowAllReviewsDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> header = new LinkedHashMap<>();
    private final Map<String, Book> books = new HashMap<>();
    private BookFactory bookFactory;

    public FileBookDataAccessObject(String csvFilePath, BookFactory bookFactory) throws IOException {
        this.csvFile = new File(csvFilePath);
        this.bookFactory = bookFactory;

        header.put("book_title", 0);
        header.put("book_author", 1);

        //TODO: 目前不清楚Book Data Access Object的设计 先不写具体逻辑，等之后把代码合并的时候再补充


    }

    @Override
    public boolean review_exists(String bookTitle) {
        if (books.containsKey(bookTitle)){
            return !books.get(bookTitle).getReviewList().isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public List<Review> retrieveAllReviews(String bookTitle) {
        if (!books.containsKey(bookTitle)){
            return new ArrayList<>();
        }
        return books.get(bookTitle).getReviewList();
    }

    @Override
    public float retrieveRating(String bookTitle) {
        if (!books.containsKey(bookTitle)){
            return 0;
        }
        return books.get(bookTitle).getRating();
    }
}
