package user_manage.data_access;

import user_manage.entity.Review;
import user_manage.entity.ReviewFactory;
import user_manage.service.reading_review.write_reviews.WriteReviewsDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileReviewDataAccessObject implements WriteReviewsDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> header = new LinkedHashMap<>();
    private Map<String, Map<String, Review>> reviewsByUser = new HashMap<>();
    private Map<String, List<Review>> reviewsByBook = new HashMap<>();

    private ReviewFactory reviewFactory;

    public FileReviewDataAccessObject(String csvFilePath, ReviewFactory reviewFactory) throws IOException {
        this.csvFile = new File(csvFilePath);
        this.reviewFactory = reviewFactory;

        header.put("book_title", 0);
        header.put("book_author", 1);
        header.put("reviewer", 2);
        header.put("rating", 3);
        header.put("review_content", 4);
        header.put("creation_time", 5);

        if (csvFile.length() == 0){
            save();
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
                String headers = reader.readLine();

                assert headers.equals("book_title,book_author,reviewer,rating,review_content,creation_time");

                String currentRow;
                while ((currentRow = reader.readLine()) != null){
                    String[] reviewInfo = currentRow.split(",");
                    String bookTitle = String.valueOf(reviewInfo[header.get("book_title")]);
                    String bookAuthor = String.valueOf(reviewInfo[header.get("book_author")]);
                    String reviewer = String.valueOf(reviewInfo[header.get("reviewer")]);
                    Integer rating = Integer.valueOf(reviewInfo[header.get("rating")]);
                    String reviewContent = String.valueOf(reviewInfo[header.get("review_content")]);
                    LocalDateTime creationTime =
                            LocalDateTime.parse(String.valueOf(reviewInfo[header.get("creation_time")]));

                    Review review = reviewFactory.create(bookTitle,bookAuthor,reviewer, rating,reviewContent,creationTime);
                    Map<String, Review> userReviewList = new HashMap<>();
                    if (reviewsByUser.containsKey(reviewer)){
                        userReviewList = reviewsByUser.get(reviewer); }

                    if (userReviewList.containsKey(bookTitle)){
                        userReviewList.replace(bookTitle, review);
                    } else {
                        userReviewList.put(bookTitle, review);
                    }
                    reviewsByUser.put(reviewer, userReviewList);

                    List<Review> bookReviewList = new ArrayList<>();
                    if (reviewsByBook.containsKey(bookTitle)){
                        bookReviewList = reviewsByBook.get(bookTitle); }
                    bookReviewList.add(review);
                    reviewsByBook.put(reviewer, bookReviewList);
                }

            }
        }

    }


    @Override
    public void saveNewReview(Review newReview) {
        String bookTitle = newReview.getReviewedBook();
        String reviewer = newReview.getReviewer();

        //Save review by different book
        Map<String, Review> userReviewList = new HashMap<>();
        if (reviewsByUser.containsKey(reviewer)){
            userReviewList = reviewsByUser.get(reviewer); }

        if (userReviewList.containsKey(bookTitle)){
            userReviewList.replace(bookTitle, newReview);
        } else {
            userReviewList.put(bookTitle, newReview);
        }
        reviewsByUser.put(reviewer, userReviewList);

        //Save review by different user
        List<Review> bookReviewList = new ArrayList<>();
        if (reviewsByBook.containsKey(bookTitle)){
            bookReviewList = reviewsByBook.get(bookTitle); }
        bookReviewList.add(newReview);
        reviewsByBook.put(reviewer, bookReviewList);

        save();
    }



    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))){
            writer.write(String.join(",", header.keySet()));
            writer.newLine();

            for (String books: reviewsByBook.keySet()){
                for (Review reviews: reviewsByBook.get(books)){
                    writer.write(String.format("%s,%s,%s,%s,%s,%s",
                            reviews.getReviewedBook(), reviews.getBookAuthor(), reviews.getReviewer(),
                            reviews.getRating(), reviews.getReviewContent(), reviews.getCreationTime()));
                    writer.newLine();
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
