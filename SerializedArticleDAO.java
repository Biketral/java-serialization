import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SerializedArticleDAO implements ArticleDAO, Serializable {

    private String file_name;

    public SerializedArticleDAO(String file_name) {
        this.file_name = file_name;
        File file = new File(file_name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getArticleList() {
        List<Article> article_list = new ArrayList<>();
        File file = new File(file_name);
        if (file.length() == 0) {
            return article_list;
        }
        try {
            ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream(file_name));
            article_list = (List<Article>) deserializer.readObject();
            deserializer.close();
        } catch (Exception e) {
            System.err.println("Error during deserialization.");
            System.exit(1);
        }
        return article_list;
    }

    @Override
    public Article getArticle(int id) {
        for (Article article : getArticleList()) {
            if (article.getId() == id) {
                return article;
            }
        }
        throw new IllegalArgumentException("Error: Article not found. (id" + id + ")");
    }

    @Override
    public void saveArticle(Article article) {

        List<Article> articles = getArticleList();
        int article_ID = article.getId();


        for (Article current_article : articles) {
            if (article_ID == current_article.getId()) {
                throw new IllegalArgumentException("Error: Article already exists. (id=" + article_ID + ")" + "\n");
            }
        }

        File file = new File(file_name);
        articles.add(article);
        try {
            ObjectOutputStream serializer_writer = new ObjectOutputStream(new FileOutputStream(file));
            serializer_writer.writeObject(articles);
            serializer_writer.close();
        } catch (Exception e) {
            System.err.println("Error during serialization.");
            System.exit(1);
        }
    }

    @Override
    public void deleteArticle(int id) {
        boolean found = false;
        List<Article> article_list = new ArrayList<>(getArticleList());
        Article article_to_delete = null;
        for (Article current_article : article_list) {
            if (current_article.getId() == id) {
                found = true;
                article_to_delete = current_article;
                break;
            }
        }

        if (found == false) {
            throw new IllegalArgumentException("Error: Article not found. (id=" + id + ")");
        }

        File file = new File(file_name);
        file.delete();

        article_list.remove(article_to_delete);

        for (Article article : article_list) {
            saveArticle(article);
        }
    }

}
