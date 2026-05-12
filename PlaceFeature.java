/**
 * Represents a feature or amenity that a dog-friendly place may have.
 * Examples include water access, outdoor seating, shade, or off-leash areas.
 */
public class PlaceFeature {
    private int featureId;
    private String featureName;
    private String description;

    /**
     * Creates a new place feature.
     *
     * Precondition: The feature information is provided.
     * Postcondition: A PlaceFeature object is created.
     *
     * @param featureId the feature ID
     * @param featureName the feature name
     * @param description a short description of the feature
     */
    public PlaceFeature(int featureId, String featureName, String description) {
        this.featureId = featureId;
        this.featureName = featureName;
        this.description = description;
    }

    /**
     * Returns the feature ID.
     *
     * @return feature ID
     */
    public int getFeatureId() {
        return featureId;
    }

    /**
     * Returns the feature name.
     *
     * @return feature name
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * Returns the feature description.
     *
     * @return feature description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the feature name and description.
     *
     * Precondition: The new feature information is provided.
     * Postcondition: The feature name and description are updated.
     *
     * @param featureName the updated feature name
     * @param description the updated description
     */
    public void updateFeature(String featureName, String description) {
        this.featureName = featureName;
        this.description = description;
    }

    /**
     * Returns the feature information as text.
     *
     * @return feature name and description
     */
    @Override
    public String toString() {
        return featureName + " - " + description;
    }
}