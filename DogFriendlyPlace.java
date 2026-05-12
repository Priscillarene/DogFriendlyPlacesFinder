import java.util.ArrayList;

/**
 * Represents a dog-friendly place.
 */
public class DogFriendlyPlace {
    private int placeId;
    private String name;
    private String address;
    private String category;
    private String description;
    private String dogPolicy;
    private ArrayList<PlaceFeature> features;
    private ArrayList<Review> reviews;

    /**
     * Creates a new dog-friendly place.
     *
     * Precondition: The place information is provided.
     * Postcondition: A DogFriendlyPlace object is created with empty feature and review lists.
     *
     * @param placeId the place ID
     * @param name the place name
     * @param address the place address
     * @param category the place category
     * @param description a short description
     * @param dogPolicy the dog policy
     */
    public DogFriendlyPlace(int placeId, String name, String address, String category,
                            String description, String dogPolicy) {
        this.placeId = placeId;
        this.name = name;
        this.address = address;
        this.category = category;
        this.description = description;
        this.dogPolicy = dogPolicy;
        this.features = new ArrayList<PlaceFeature>();
        this.reviews = new ArrayList<Review>();
    }

    /**
     * Returns the place ID.
     *
     * @return place ID
     */
    public int getPlaceId() {
        return placeId;
    }

    /**
     * Returns the place name.
     *
     * @return place name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the place category.
     *
     * @return place category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the place description.
     *
     * @return place description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the dog policy.
     *
     * @return dog policy
     */
    public String getDogPolicy() {
        return dogPolicy;
    }

    /**
     * Returns the list of features.
     *
     * @return feature list
     */
    public ArrayList<PlaceFeature> getFeatures() {
        return features;
    }

    /**
     * Returns the list of reviews.
     *
     * @return review list
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Updates the main place details.
     *
     * Precondition: The updated place information is provided.
     * Postcondition: The place name, address, and dog policy are changed.
     *
     * @param name the updated name
     * @param address the updated address
     * @param dogPolicy the updated dog policy
     */
    public void updateDetails(String name, String address, String dogPolicy) {
        this.name = name;
        this.address = address;
        this.dogPolicy = dogPolicy;
    }

    /**
     * Adds a feature to this place.
     *
     * Precondition: The feature already exists.
     * Postcondition: The feature is added to this place.
     *
     * @param feature the feature being added
     */
    public void addFeature(PlaceFeature feature) {
        features.add(feature);
    }

    /**
     * Adds a review to this place.
     *
     * Precondition: The review already exists.
     * Postcondition: The review is added to this place.
     *
     * @param review the review being added
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Checks if this place has a selected feature.
     *
     * Precondition: A feature name is provided.
     * Postcondition: The method returns true or false.
     *
     * @param featureName the feature being searched for
     * @return true if the place has the feature, false if it does not
     */
    public boolean hasFeature(String featureName) {
        for (PlaceFeature feature : features) {
            if (feature.getFeatureName().equalsIgnoreCase(featureName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the average rating for this place.
     *
     * Precondition: The place may or may not have reviews.
     * Postcondition: The average rating is returned.
     *
     * @return average rating, or 0.0 if there are no reviews
     */
    public double calculateAverageRating() {
        if (reviews.size() == 0) {
            return 0.0;
        }

        int total = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }

        return (double) total / reviews.size();
    }

    /**
     * Builds a readable summary of the place.
     *
     * Precondition: The place object exists.
     * Postcondition: A formatted String with place details is returned.
     *
     * @return place details
     */
    public String getPlaceDetails() {
        String details = "";
        details += "Name: " + name + "\n";
        details += "Address: " + address + "\n";
        details += "Category: " + category + "\n";
        details += "Description: " + description + "\n";
        details += "Dog Policy: " + dogPolicy + "\n";
        details += "Average Rating: " + String.format("%.1f", calculateAverageRating()) + "/5\n";

        details += "Features:\n";
        for (PlaceFeature feature : features) {
            details += "- " + feature + "\n";
        }

        details += "Reviews:\n";
        for (Review review : reviews) {
            details += "- " + review + "\n";
        }

        return details;
    }

    /**
     * Returns a short version of the place information.
     *
     * @return place ID, name, and category
     */
    @Override
    public String toString() {
        return placeId + " - " + name + " (" + category + ")";
    }
}