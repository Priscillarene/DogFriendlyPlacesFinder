import java.util.ArrayList;

/**
 * Main controller for the Dog-Friendly Places Finder system.
 * This class stores places and handles searching, filtering, adding, and updating.
 */
public class DogFriendlyPlacesFinder {
    private ArrayList<DogFriendlyPlace> places;

    /**
     * Creates a new DogFriendlyPlacesFinder system.
     *
     * Precondition: None.
     * Postcondition: An empty list of dog-friendly places is created.
     */
    public DogFriendlyPlacesFinder() {
        places = new ArrayList<DogFriendlyPlace>();
    }

    /**
     * Adds a dog-friendly place to the system.
     *
     * Precondition: The place object already exists.
     * Postcondition: The place is added to the places list.
     *
     * @param place the place being added
     */
    public void addPlace(DogFriendlyPlace place) {
        places.add(place);
    }

    /**
     * Searches for places by keyword.
     *
     * Precondition: A keyword is provided.
     * Postcondition: A list of matching places is returned.
     *
     * @param keyword the word being searched
     * @return a list of places that match the keyword
     */
    public ArrayList<DogFriendlyPlace> searchPlaces(String keyword) {
        ArrayList<DogFriendlyPlace> results = new ArrayList<DogFriendlyPlace>();

        for (DogFriendlyPlace place : places) {
            if (place.getName().toLowerCase().contains(keyword.toLowerCase())
                    || place.getCategory().toLowerCase().contains(keyword.toLowerCase())
                    || place.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(place);
            }
        }

        return results;
    }

    /**
     * Filters places by a selected feature.
     *
     * Precondition: A feature name is provided.
     * Postcondition: A list of places with that feature is returned.
     *
     * @param featureName the feature being searched for
     * @return a list of places that have the selected feature
     */
    public ArrayList<DogFriendlyPlace> filterPlaces(String featureName) {
        ArrayList<DogFriendlyPlace> results = new ArrayList<DogFriendlyPlace>();

        for (DogFriendlyPlace place : places) {
            if (place.hasFeature(featureName)) {
                results.add(place);
            }
        }

        return results;
    }

    /**
     * Finds a place by its ID.
     *
     * Precondition: A place ID is provided.
     * Postcondition: The matching place is returned, or null if it is not found.
     *
     * @param placeId the ID of the place
     * @return the matching place, or null if no match is found
     */
    public DogFriendlyPlace findPlaceById(int placeId) {
        for (DogFriendlyPlace place : places) {
            if (place.getPlaceId() == placeId) {
                return place;
            }
        }

        return null;
    }

    /**
     * Updates an existing place.
     *
     * Precondition: The place should already exist in the system.
     * Postcondition: If the place is found, its details are updated.
     *
     * @param placeId the ID of the place being updated
     * @param name the updated place name
     * @param address the updated address
     * @param dogPolicy the updated dog policy
     */
    public void updatePlaceInfo(int placeId, String name, String address, String dogPolicy) {
        DogFriendlyPlace place = findPlaceById(placeId);

        if (place != null) {
            place.updateDetails(name, address, dogPolicy);
        }
    }

    /**
     * Displays a list of places in the console.
     *
     * Precondition: A list of places is provided.
     * Postcondition: The places are printed, or a no-results message is printed.
     *
     * @param placeList the list of places to display
     */
    public void displayPlaces(ArrayList<DogFriendlyPlace> placeList) {
        if (placeList.size() == 0) {
            System.out.println("No matching dog-friendly places were found.");
        } else {
            for (DogFriendlyPlace place : placeList) {
                System.out.println(place);
            }
        }
    }
}