package user_manage.data_access;

import search.entity.Book;
import user_manage.entity.Review;
import user_manage.entity.ReviewFactory;
import user_manage.service.reading_review.write_reviews.WriteReviewsDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileReviewDataAccessObject implements WriteReviewsDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> header = new LinkedHashMap<>();
    private final Map<String, Review> myReviews = new HashMap<>();
    private ReviewFactory reviewFactory;

    public FileReviewDataAccessObject(String csvFilePath, ReviewFactory reviewFactory) throws IOException {
        this.csvFile = new File(csvFilePath);
        this.reviewFactory = reviewFactory;

        header.put("book_title", 0);
        header.put("book_author", 1);
        header.put("rating", 2);
        header.put("review_content", 3);
        header.put("creation_time", 4);

        if (csvFile.length() == 0){
            save();
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
                String headers = reader.readLine();

                assert headers.equals("book_title,book_author,rating,review_content,creation_time");

                String currentRow;
                while ((currentRow = reader.readLine()) != null){
                    String[] reviewinfo = currentRow.split(",");
                    String bookTitle = String.valueOf(reviewinfo[header.get("book_title")]);
                    String bookAuthor = String.valueOf(reviewinfo[header.get("book_author")]);
                    Integer rating = Integer.valueOf(reviewinfo[header.get("rating")]);
                    String reviewContent = String.valueOf(reviewinfo[header.get("review_content")]);
                    LocalDateTime creationTime =
                            LocalDateTime.parse(String.valueOf(reviewinfo[header.get("creation_time")]));

                    Review review = reviewFactory.create(bookTitle,bookAuthor,rating,reviewContent,creationTime);
                    myReviews.put(bookTitle, review);
                }

            }
        }

    }

    @Override
    public boolean review_exists(String reviewedBook) {
        return myReviews.containsKey(reviewedBook);
    }

    @Override
    public void saveNewReview(Review newReview) {
        myReviews.put(newReview.getReviewedBook(), newReview);
        save();
    }

    @Override
    public void updateReview(Review updatedReview) {
        Review oldReview = myReviews.remove(updatedReview.getReviewedBook());
        myReviews.put(updatedReview.getReviewContent(), updatedReview);
        save();
    }


    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))){
            writer.write(String.join(",", header.keySet()));
            writer.newLine();

            for (Review reviews: myReviews.values()){
                writer.write(String.format("%s,%s,%s,%s,%s",
                        reviews.getReviewedBook(), reviews.getBookAuthor(), reviews.getRating(),
                        reviews.getReviewContent(), reviews.getCreationTime()));
                writer.newLine();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
