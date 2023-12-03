package user_manage.data_access;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import user_manage.entity.CommonReview;
import user_manage.entity.CommonReviewFactory;
import user_manage.entity.Review;
import user_manage.entity.ReviewFactory;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInputBoundary;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInputData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileReviewDataAccessObjectTest {

    private class MockReviewFactory implements ReviewFactory {
        @Override
        public Review create(String reviewedBook, String bookAuthor, String reviewer, Integer rating, String reviewContent, LocalDateTime creationTime) {
            assertEquals(reviewedBook, "ezpz4.0");
            assertEquals(bookAuthor, "excellent professor");
            assertEquals(reviewer, "4.0student");
            assertEquals(rating, 5);
            return new CommonReview("ezpz4.0", "excellent professor", "4.0student", 5, "excellent book", LocalDateTime.of(2003, 7, 24, 12, 0));
        }
    }
    @Test
    void testSaveForConstructor(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./reviewTest.csv"))){
            Map<String, Integer> header = new LinkedHashMap<>();
            header.put("book_title", 0);
            header.put("book_author", 1);
            header.put("reviewer", 2);
            header.put("rating", 3);
            header.put("review_content", 4);
            header.put("creation_time", 5);
            writer.write(String.join(",", header.keySet()));
            writer.newLine();


            writer.write(String.format("%s,%s,%s,%s,%s,%s", "ezpz4.0", "excellent professor",
                    "4.0student",  5, "excellent book",
                    LocalDateTime.of(2003, 7, 24, 12, 0)));
            writer.newLine();

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        try {
            MockReviewFactory mockReviewFactory = new MockReviewFactory();
            FileReviewDataAccessObject fileReviewDataAccessObject = new FileReviewDataAccessObject("./reviewTest.csv", mockReviewFactory);
            assertTrue(fileReviewDataAccessObject.book_review_exists("ezpz4.0"));
            assertFalse(fileReviewDataAccessObject.book_review_exists("no exist"));
            assertTrue(fileReviewDataAccessObject.user_review_exists("4.0student"));
            assertFalse(fileReviewDataAccessObject.user_review_exists("no name"));
            assertEquals("4.0student\n" + "rating: 5\n" + "excellent book\n" + "2003-07-24", fileReviewDataAccessObject.retrieveAllBookReviews("ezpz4.0").get(0));
            assertEquals("ezpz4.0       by excellent professor\n" + "rating: 5\n"+ "excellent book\n" + "2003-07-24", fileReviewDataAccessObject.retrieveAllMyReviews("4.0student").get(0));
            assertEquals("ezpz4.0       by excellent professor\n" + "rating: 5\n"+ "excellent book\n" +
                    "2003-07-24", fileReviewDataAccessObject.retrieveAllMyReviews("4.0student").get(0));
            assertEquals(5.0F, fileReviewDataAccessObject.retrieveRating("ezpz4.0"));

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void testBehaviorAfterSaveNewReview(){
        ReviewFactory reviewFactory = new CommonReviewFactory();
        try {
            Review review1 = new CommonReview("good book", "good author",
                    "good reviewer",  5, "very good book",
                    LocalDateTime.of(1999, 9, 9, 9, 0));
            Review review2 = new CommonReview("good book", "good author",
                    "another good reviewer",  4, "good book",
                    LocalDateTime.of(1998, 9, 9, 9, 0));
            Review review3 = new CommonReview("normal book", "normal author", "reviewer",  3, "normal book",
                    LocalDateTime.of(2015, 9, 9, 9, 0));
            Review review4 = new CommonReview("normal book", "normal author", "good reviewer",  5, "normal book but I only gives 5 star",
                    LocalDateTime.of(2018, 9, 9, 9, 0));
            Review review5 = new CommonReview("good book", "good author",
                    "good reviewer",  5, "very good book so I read it for second time",
                    LocalDateTime.of(2023, 9, 9, 9, 0));
            FileReviewDataAccessObject fileReviewDAO2 = new FileReviewDataAccessObject("./reviewDAOTest2.csv", reviewFactory);
            fileReviewDAO2.deleteAll();
            assertFalse(fileReviewDAO2.book_review_exists("good book"));
            fileReviewDAO2.saveNewReview(review1);
            assertEquals(5.0F, fileReviewDAO2.retrieveRating("good book"));
            assertTrue(fileReviewDAO2.book_review_exists("good book"));
            fileReviewDAO2.saveNewReview(review2);
            assertEquals((5.0F + 4.0F)/2, fileReviewDAO2.retrieveRating("good book"));
            List<String> reviewList = fileReviewDAO2.retrieveAllBookReviews("good book");
            assertEquals("good reviewer\n" + "rating: 5\n" + "very good book\n" + "1999-09-09", reviewList.get(0));
            assertEquals("another good reviewer\n" + "rating: 4\n" + "good book\n" + "1998-09-09", reviewList.get(1));
            fileReviewDAO2.saveNewReview(review3);
            fileReviewDAO2.saveNewReview(review4);
            assertEquals((3.0F + 5.0F) / 2, fileReviewDAO2.retrieveRating("normal book"));
            List<String> userReviewList = fileReviewDAO2.retrieveAllMyReviews("good reviewer");
            assertEquals("good book       by good author\n" + "rating: 5\n" + "very good book\n" + "1999-09-09", userReviewList.get(0));
            assertEquals("normal book       by normal author\n" + "rating: 5\n" + "normal book but I only gives 5 star\n" + "2018-09-09", userReviewList.get(1));
            fileReviewDAO2.saveNewReview(review5);
            List<String> userReviewList2 = fileReviewDAO2.retrieveAllMyReviews("good reviewer");
            assertEquals("good book       by good author\n" + "rating: 5\n" + "very good book so I read it for second time\n" + "2023-09-09", userReviewList2.get(0));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }



}