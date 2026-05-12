/**
 * Represents a review left by a dog owner for a dog-friendly place.
 */
public class Review {
    private int reviewId;
    private int rating;
    private String comment;
    private String reviewDate;

    /**
     * Creates a new review.
     *
     * Precondition: The review information is provided.
     * Postcondition: A Review object is created.
     *
     * @param reviewId the review ID
     * @param rating the rating from 1 to 5
     * @param comment the review comment
     * @param reviewDate the date the review was written
     */
    public Review(int reviewId, int rating, String comment, String reviewDate) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    /**
     * Returns the review ID.
     *
     * @return review ID
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * Returns the rating.
     *
     * @return rating from 1 to 5
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns the review comment.
     *
     * @return review comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the review date.
     *
     * @return review date
     */
    public String getReviewDate() {
        return reviewDate;
    }

    /**
     * Updates the rating and comment.
     *
     * Precondition: The new rating and comment are provided.
     * Postcondition: The review rating and comment are updated.
     *
     * @param rating the updated rating
     * @param comment the updated comment
     */
    public void editReview(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Returns the review details as readable text.
     *
     * Precondition: The review object exists.
     * Postcondition: A formatted review String is returned.
     *
     * @return review details
     */
    public String getReviewDetails() {
        return "Rating: " + rating + "/5 | " + comment + " | Date: " + reviewDate;
    }

    /**
     * Returns the review information as text.
     *
     * @return review details
     */
    @Override
    public String toString() {
        return getReviewDetails();
    }
}