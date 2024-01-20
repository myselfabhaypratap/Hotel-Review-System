import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Hotel {
    private String name;
    private List<Review> reviews;

    public Hotel(String name) {
        this.name = name;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }
}

class Review {
    private String user;
    private int rating;
    private String comment;

    public Review(String user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "User: " + user + ", Rating: " + rating + ", Comment: " + comment;
    }
}

public class HotelReviewSystem {
    public static void main(String[] args) {
        List<Hotel> hotels = new ArrayList<>();

        Hotel hotel1 = new Hotel("Hotel A");
        hotel1.addReview(new Review("User1", 4, "Great experience!"));
        hotel1.addReview(new Review("User2", 5, "Excellent service!"));

        Hotel hotel2 = new Hotel("Hotel B");
        hotel2.addReview(new Review("User3", 3, "Average stay"));
        hotel2.addReview(new Review("User4", 2, "Not satisfied"));

        hotels.add(hotel1);
        hotels.add(hotel2);

        displayMenu(hotels);
    }

    private static void displayMenu(List<Hotel> hotels) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. View all hotels");
                System.out.println("2. View hotel reviews");
                System.out.println("3. Sort hotels by average rating");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewAllHotels(hotels);
                        break;
                    case 2:
                        viewHotelReviews(hotels);
                        break;
                    case 3:
                        sortHotelsByRating(hotels);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void viewAllHotels(List<Hotel> hotels) {
        System.out.println("All Hotels:");
        for (Hotel hotel : hotels) {
            System.out.println("Hotel: " + hotel.getName() + ", Average Rating: " + hotel.getAverageRating());
        }
    }

    private static void viewHotelReviews(List<Hotel> hotels) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the hotel name:");
            String hotelName = scanner.nextLine();

            Hotel selectedHotel = null;
            for (Hotel hotel : hotels) {
                if (hotel.getName().equalsIgnoreCase(hotelName)) {
                    selectedHotel = hotel;
                    break;
                }
            }

            if (selectedHotel != null) {
                System.out.println("Reviews for " + selectedHotel.getName() + ":");
                for (Review review : selectedHotel.getReviews()) {
                    System.out.println(review);
                }
            } else {
                System.out.println("Hotel not found.");
            }
        }
    }

    private static void sortHotelsByRating(List<Hotel> hotels) {
        Collections.sort(hotels, Comparator.comparingDouble(Hotel::getAverageRating).reversed());
        System.out.println("Hotels sorted by average rating:");
        for (Hotel hotel : hotels) {
            System.out.println("Hotel: " + hotel.getName() + ", Average Rating: " + hotel.getAverageRating());
        }
    }
}
