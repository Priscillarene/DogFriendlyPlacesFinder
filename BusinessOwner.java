/**
 * Represents a business owner who can add or update dog-friendly place information.
 */
public class BusinessOwner {
    private int businessOwnerId;
    private String name;
    private String email;
    private String businessName;

    /**
     * Creates a new business owner.
     *
     * Precondition: The business owner information is provided.
     * Postcondition: A BusinessOwner object is created.
     *
     * @param businessOwnerId the business owner ID
     * @param name the business owner's name
     * @param email the business owner's email
     * @param businessName the business name
     */
    public BusinessOwner(int businessOwnerId, String name, String email, String businessName) {
        this.businessOwnerId = businessOwnerId;
        this.name = name;
        this.email = email;
        this.businessName = businessName;
    }

    /**
     * Adds a dog-friendly place to the system.
     *
     * Precondition: The system and place already exist.
     * Postcondition: The place is added to the system.
     *
     * @param system the system that stores the place
     * @param place the place being added
     */
    public void addPlace(DogFriendlyPlacesFinder system, DogFriendlyPlace place) {
        system.addPlace(place);
    }

    /**
     * Updates an existing dog-friendly place.
     *
     * Precondition: The place should already exist in the system.
     * Postcondition: If the place is found, its information is updated.
     *
     * @param system the system that contains the place
     * @param placeId the ID of the place being updated
     * @param name the updated place name
     * @param address the updated address
     * @param dogPolicy the updated dog policy
     */
    public void updatePlaceInfo(DogFriendlyPlacesFinder system, int placeId,
                                String name, String address, String dogPolicy) {
        system.updatePlaceInfo(placeId, name, address, dogPolicy);
    }

    /**
     * Returns the business owner ID.
     *
     * @return business owner ID
     */
    public int getBusinessOwnerId() {
        return businessOwnerId;
    }

    /**
     * Returns the business owner's name.
     *
     * @return business owner name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the business owner's email.
     *
     * @return business owner email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the business name.
     *
     * @return business name
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Returns the business owner information as text.
     *
     * @return business owner information
     */
    @Override
    public String toString() {
        return businessName + " managed by " + name + " (" + email + ")";
    }
}