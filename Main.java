import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        DogFriendlyPlacesFinder system = new DogFriendlyPlacesFinder();

        DogOwner dogOwner = new DogOwner(1, "Priscilla", "priscilla@email.com");
        BusinessOwner businessOwner = new BusinessOwner(1, "Jacob", "jacob@email.com", "Austin Pet Patio");

        PlaceFeature waterAccess = new PlaceFeature(1, "water access", "Has water available for dogs");
        PlaceFeature outdoorSeating = new PlaceFeature(2, "outdoor seating", "Has a patio or outdoor area");
        PlaceFeature shade = new PlaceFeature(3, "shade", "Has shaded areas for dogs and owners");
        PlaceFeature offLeash = new PlaceFeature(4, "off-leash area", "Allows dogs to be off leash");

        DogFriendlyPlace park = new DogFriendlyPlace(
                101,
                "Bastrop Dog Park",
                "100 Park Road, Bastrop, TX",
                "Park",
                "A local park with open space for dogs.",
                "Dogs allowed. Must be leashed outside off-leash areas."
        );

        park.addFeature(waterAccess);
        park.addFeature(shade);
        park.addFeature(offLeash);

        DogFriendlyPlace patio = new DogFriendlyPlace(
                102,
                "Austin Pet Patio",
                "200 Patio Lane, Austin, TX",
                "Restaurant",
                "A restaurant patio that welcomes dogs.",
                "Dogs allowed on patio only."
        );

        patio.addFeature(waterAccess);
        patio.addFeature(outdoorSeating);
        patio.addFeature(shade);

        businessOwner.addPlace(system, park);
        businessOwner.addPlace(system, patio);

        dogOwner.leaveReview(park, new Review(1, 5, "Great park with plenty of space!", "05/01/2026"));
        dogOwner.leaveReview(patio, new Review(2, 4, "Cute patio and good water access for dogs.", "05/02/2026"));

        System.out.println("===== DOG-FRIENDLY PLACES FINDER =====");

        System.out.println("\nSearch Results for 'park':");
        ArrayList<DogFriendlyPlace> searchResults = system.searchPlaces("park");
        system.displayPlaces(searchResults);

        System.out.println("\nFilter Results for 'water access':");
        ArrayList<DogFriendlyPlace> filterResults = system.filterPlaces("water access");
        system.displayPlaces(filterResults);

        System.out.println("\nViewing Details for Place ID 101:");
        DogFriendlyPlace selectedPlace = system.findPlaceById(101);

        if (selectedPlace != null) {
            System.out.println(selectedPlace.getPlaceDetails());
        }

        System.out.println("\nUpdating Place Information for Place ID 102:");
        businessOwner.updatePlaceInfo(
                system,
                102,
                "Austin Pet Patio Cafe",
                "200 Patio Lane, Austin, TX",
                "Dogs allowed on the patio with leash required."
        );

        DogFriendlyPlace updatedPlace = system.findPlaceById(102);

        if (updatedPlace != null) {
            System.out.println(updatedPlace.getPlaceDetails());
        }

        System.out.println("===== END OF PROGRAM =====");
    }
}