import java.text.DecimalFormat;

public class DVD extends Article {
    private int length;
    private int age_rating;

    public DVD(Integer id, String title, int year, String publisher, double base_price, int length,
               int age_rating) {
        super(id, title, year, publisher, base_price);

        if (!(age_rating == 0 || age_rating == 6 || age_rating == 12 || age_rating == 16 || age_rating == 18)) {
            throw new IllegalArgumentException("Error: Invalid age rating.");
        }

        this.length = length;
        this.age_rating = age_rating;
    }

    @Override
    public double getDiscount() {
        // For a DVD, the discount depends on the age rating: no age restriction - 20%
        // discount, for ages 6 and up - 15% discount, for ages 12 and up - 10%
        // discount, for ages
        // 16 and up - 5% discount, for ages 18 and up - 0% discount.
        int age_rating_int = this.age_rating;
        double discount = 0.0f;

        if (age_rating_int == 0) {
            discount = 0.2;
        } else if (age_rating_int == 6) {
            discount = 1.5;
        } else if (age_rating_int == 12) {
            discount = 1.0;
        } else if (age_rating_int == 16) {
            discount = 0.5;
        }

        return discount;
    }

    @Override
    public String toString() {
        String all_data = "";
        all_data += "Type:       DVD\n";
        all_data += "Id:         " + this.getId() + "\n";
        all_data += "Title:      " + getTitle() + "\n";
        all_data += "Year:       " + getYear() + "\n";
        all_data += "Publisher:  " + getPublisher() + "\n";
        all_data += "Base price: " + getBasePrice() + "\n";
        DecimalFormat df = new DecimalFormat("0.00");
        all_data += "Price:      " + df.format(getPrice()) + "\n";
        all_data += "Length:     " + getLength() + "\n";
        all_data += "Age rating: " + getAgeRating();
        return all_data;
    }


    public double getPrice() {
        return getBasePrice() - (getDiscount() * getBasePrice());
    }

    public int getLength() {
        return this.length;
    }

    public int getAgeRating() {
        return this.age_rating;
    }

};
