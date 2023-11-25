import java.text.DecimalFormat;

public class Book extends Article {
    private int pages;

    public Book(Integer id, String title, int year, String publisher, double base_price, int pages) {
        super(id, title, year, publisher, base_price);
        this.pages = pages;
    }

    public int getPages() {
        return this.pages;
    }

    @Override
    public String toString() {
        String all_data = "";
        all_data += "Type:       Book\n";
        all_data += "Id:         " + this.getId() + "\n";
        all_data += "Title:      " + getTitle() + "\n";
        all_data += "Year:       " + getYear() + "\n";
        all_data += "Publisher:  " + getPublisher() + "\n";
        all_data += "Base price: " + getBasePrice() + "\n";
        DecimalFormat df = new DecimalFormat("0.00");
        all_data += "Price:      " + df.format(getPrice()) + "\n";
        all_data += "Pages:      " + this.pages;
        return all_data;
    }

    public double getPrice() {
        return getBasePrice() - (getDiscount() * getBasePrice());
    }


    @Override
    public double getDiscount() {
        int years_int = this.getAge();
        double discount_in_percent = 0.0f;

        if (years_int >= 5) {
            discount_in_percent = 0.3;
        } else {
            discount_in_percent = 0.05 * years_int;
        }

        if (this.getPages() > 1000) {
            discount_in_percent += 3;
        }
        return discount_in_percent;
    }

};
