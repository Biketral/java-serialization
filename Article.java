import java.io.Serializable;
import java.time.Year;

public abstract class Article implements Serializable {

    private int Id;
    private String Title;
    private int year;
    private String publisher;
    private double base_price;

    public Article(int id, String title, int year, String publisher, double base_price) {

        if (title == null || publisher == null || title.isEmpty() || publisher.isEmpty()) {
            throw new IllegalArgumentException("Error: Invalid parameter.");
        }
        if (year % 1 != 0) {
            throw new IllegalArgumentException("Error: Invalid parameter.");
        }

        Year present_year = Year.now();
        int present_year_int = present_year.getValue();
        if (year > present_year_int) {
            throw new IllegalArgumentException("Error: Invalid release year.");
        }

        this.Id = id;
        this.Title = title;
        this.year = year;
        this.publisher = publisher;
        this.base_price = base_price;
    }

    public int getAge() {
        // this getAge() calculates the age of the book and returns it in Year format.
        Year current_year = Year.now();

        int now_int = current_year.getValue();
        int book_release_year = this.year;
        int difference = now_int - book_release_year;

        Year difference_in_year = Year.of(difference);
        int difference_int = difference_in_year.getValue();

        return difference_int;
    }

    public abstract double getDiscount();

    public double getPrice() {
        return base_price - getDiscount();
    }

    ;

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getBasePrice() {
        return base_price;
    }


}
