import java.util.ArrayList;

/**
 * Represents a dog owner who can save places and leave reviews.
 */
public class DogOwner {
    private int ownerId;
    private String name;
    private String email;
    private ArrayList<DogFriendlyPlace> savedPlaces;

    /**
     * Creates a new dog owner.
     *
     * Precondition: The dog owner information is provided.
     * Postcondition: A DogOwner object is created with an empty saved places list.
     *
     * @param ownerId the dog owner ID
     * @param name the dog owner's name
     * @param email the dog owner's email
     */
    public DogOwner(int ownerId, String name, String email) {
        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
        this.savedPlaces = new ArrayList<DogFriendlyPlace>();
    }

    /**
     * Saves a dog-friendly place to the owner's saved list.
     *
     * Precondition: The place object already exists.
     * Postcondition: The place is added to the saved places list.
     *
     * @param place the place being saved
     */
    public void savePlace(DogFriendlyPlace place) {
        savedPlaces.add(place);
    }

    /**
     * Adds a review to a dog-friendly place.
     *
     * Precondition: The place and review already exist.
     * Postcondition: The review is added to the selected place.
     *
     * @param place the place being reviewed
     * @param review the review being added
     */
    public void leaveReview(DogFriendlyPlace place, Review review) {
        place.addReview(review);
    }

    /**
     * Returns the dog owner's ID.
     *
     * @return dog owner ID
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Returns the dog owner's name.
     *
     * @return dog owner name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the dog owner's email.
     *
     * @return dog owner email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the saved places list.
     *
     * @return saved places
     */
    public ArrayList<DogFriendlyPlace> getSavedPlaces() {
        return savedPlaces;
    }

    /**
     * Returns the dog owner information as text.
     *
     * @return dog owner information
     */
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}