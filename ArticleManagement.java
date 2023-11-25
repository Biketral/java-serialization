import java.util.ArrayList;
import java.util.List;

public class ArticleManagement {

    private ArticleDAO articleDAO;

    public ArticleManagement(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    // • Return all data of all articles.
    public List<Article> returnAllArticles() {
        return articleDAO.getArticleList();
    }

    //• Return all data of a specific article.
    public Article returnSpecificArticle(int id) {
        List<Article> article_list = articleDAO.getArticleList();
        for (Article current_article : article_list) {
            if (current_article.getId() == id) {
                return current_article;
            }
        }
        throw new IllegalArgumentException("Error: Article not found. (id=" + id + ")");
    }

    //• Add a new article.
    public void addArticle(Article articleToAdd) {
        articleDAO.saveArticle(articleToAdd);
    }

    //• Delete an article.
    public void deleteArticle(int id) {
        articleDAO.deleteArticle(id);
    }

    //• Determine the total number of all articles.
    public int articleCount() {
        return articleDAO.getArticleList().size();
    }

    //• Determine the total number of all books.
    public int bookcount() {
        List<Article> article_list = articleDAO.getArticleList();
        int counter = 0;
        for (Article article : article_list) {
            if (article instanceof Book) {
                counter += 1;
            }
        }
        return counter;
    }

    //• Determine the total number of all dvds.
    public int dvdcount() {
        List<Article> article_list = articleDAO.getArticleList();
        int counter = 0;
        for (Article article : article_list) {
            if (article instanceof DVD) {
                counter += 1;
            }
        }
        return counter;
    }


    //• Determine the mean price of all articles.
    public double meanPriceArticles() {
        List<Article> article_list = articleDAO.getArticleList();
        double sum = 0;
        for (Article article : article_list) {
            sum += article.getPrice();
        }
        return sum / article_list.size();
    }


    //• Determine the ID(s) of the oldest article(s).
    public List<Integer> getOldestArticleID() {
        List<Article> article_list = articleDAO.getArticleList();
        List<Integer> list_of_IDs = new ArrayList<Integer>();

        int min = article_list.get(0).getYear();

        for (Article article : article_list) {
            int year = article.getYear();
            if (min > year) {
                min = year;
            }
        }

        for (Article article : article_list) {
            int year = article.getYear();
            if (year == min) {
                list_of_IDs.add(article.getId());
            }
        }

        return list_of_IDs;
    }


}
