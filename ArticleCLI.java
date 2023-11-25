import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ArticleCLI {


    public static void main(String[] args) {

        try {
            if (args.length < 2) {
                throw new IllegalArgumentException("Error: Invalid parameter.");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        List<String> parameter_list = new ArrayList<>();
        String command = args[1];

        if (args.length > 2) { // wenn viele argumente gepasst werden
            for (int i = 2; i < args.length; i++) {
                parameter_list.add(args[i]);
            }
        } else if (args.length == 3) { // wenn command und id/count gepasst wird
            parameter_list.clear();
            String id = args[2];
            parameter_list.add(id);
        }
        try {
            if (command.equals("add") && parameter_list.size() < 7) {
                throw new IllegalArgumentException("Error: Invalid parameter.");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        try {
            if (!command.equals("add") && !command.equals("list") && !command.equals("count") && !command.equals("meanprice") &&
                    !command.equals("oldest") && !command.equals("delete")) {
                throw new IllegalArgumentException("Error: Invalid parameter.");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            //return;
        }

        try {
            SerializedArticleDAO serializer = new SerializedArticleDAO(args[0]);
            ArticleManagement article_manager = new ArticleManagement(serializer);

            switch (command) {
                case "add":
                    addArticle(article_manager, parameter_list);
                    break;

                case "list":

                    if (parameter_list.isEmpty()) {
                        listArticles(article_manager);
                        break;
                    }
                    if (parameter_list.size() == 1) {
                        String id_to_list = parameter_list.get(0);
                        listArticle(article_manager, id_to_list);
                        break;
                    }


                case "delete":
                    String id = parameter_list.get(0);
                    deleteArticle(article_manager, id);
                    break;

                case "count":
                    if (parameter_list.isEmpty()) {
                        counter(article_manager);
                        break;
                    }
                    String type_class = parameter_list.get(0);
                    if (type_class.equals("dvd")) {
                        count_dvds(article_manager);
                        break;
                    } else if (type_class.equals("book")) {
                        count_books(article_manager);
                        break;
                    }
                    break;
                case "meanprice":
                    meanprice(article_manager);
                    break;
                case "oldest":
                    oldest(article_manager);
                    break;
                default:
                    throw new IllegalArgumentException("Error: Invalid parameter.");
            }
        } catch (Exception e) {
            //System.out.print("Error: Invalid parameter.");
        }

    }

    private static void addArticle(ArticleManagement article_manager, List<String> parameter_list) {
        try {
            String class_type = parameter_list.get(0);
            if (!class_type.equals("book") && !class_type.equals("dvd")) {
                throw new IllegalArgumentException("Error: Invalid parameter." + "\n");
            }

            int id = Integer.parseInt(parameter_list.get(1));
            String title = parameter_list.get(2);
            String publisher = parameter_list.get(3);
            int year = Integer.parseInt(parameter_list.get(4));
            double base_price = Double.parseDouble(parameter_list.get(5));
        } catch (Exception e) {
            System.out.print("Error: Invalid parameter." + "\n");
            return;
        }

        String class_type = parameter_list.get(0);
        int id = Integer.parseInt(parameter_list.get(1));
        String title = parameter_list.get(2);
        String publisher = parameter_list.get(3);
        int year = Integer.parseInt(parameter_list.get(4));
        double base_price = Double.parseDouble(parameter_list.get(5));

        Boolean checker = true;

        if (class_type.equals("book")) {

            try {
                int pages = Integer.parseInt(parameter_list.get(6));
                Book new_book = new Book(id, title, year, publisher, base_price, pages);
                article_manager.addArticle(new_book);
            } catch (Exception e) {
                checker = false;
                System.out.print(e.getMessage());
            }

        } else if (class_type.equals("dvd")) {
            if (parameter_list.size() < 7) {
                throw new IllegalArgumentException("Error: Invalid parameter.");
            }

            try {
                int dvd_length = Integer.parseInt(parameter_list.get(6));
                int age_rating = Integer.parseInt(parameter_list.get(7));
                DVD new_dvd = new DVD(id, title, year, publisher, base_price, dvd_length, age_rating);
                article_manager.addArticle(new_dvd);
            } catch (Exception e) {
                checker = false;
                System.out.print(e.getMessage());
            }
        }
        if (checker) {
            System.out.print("Info: Article " + id + " added." + "\n");
            checker = true;
        }
    }

    private static void listArticles(ArticleManagement article_manager) {
        List<Article> article_list = article_manager.returnAllArticles();
        for (Article article : article_list) {
            System.out.println(article.toString() + "\n");
        }
    }

    private static void listArticle(ArticleManagement article_manager, String id) {

        Article article = null;
        try {
            int id_int = Integer.parseInt(id);
            article = article_manager.returnSpecificArticle(id_int);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }
        System.out.print(article.toString());
    }

    private static void deleteArticle(ArticleManagement article_manager, String id) {
        int id_int = Integer.parseInt(id);
        Boolean found = true;
        try {
            article_manager.deleteArticle(id_int);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            found = false;
        }
        if (found == true) {
            System.out.print("Info: Article " + id + " deleted.");
        }
    }

    private static void counter(ArticleManagement article_manager) {
        System.out.print(article_manager.articleCount());
    }

    private static void count_books(ArticleManagement article_manager) {
        System.out.print(article_manager.bookcount());
    }

    private static void count_dvds(ArticleManagement article_manager) {
        System.out.print(article_manager.dvdcount());
    }

    private static void meanprice(ArticleManagement article_manager) {
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print(df.format(article_manager.meanPriceArticles()));
    }

    private static void oldest(ArticleManagement article_manager) {
        List<Integer> ids = article_manager.getOldestArticleID();
        for (Integer id : ids) {
            System.out.println("Id: " + id);
        }
    }


} // end of class
