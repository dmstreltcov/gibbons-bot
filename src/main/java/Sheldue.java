import api.ServiceNews;
import news.Article;
import news.Item;
import retrofit2.Response;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Sheldue {

    private Bot bot;
    private ScheduledExecutorService ses;
    private ScheduledFuture result;
    private String chat_id;
    private Article article;
    private Publicist publicist = new Publicist();
    private ServiceNews service = publicist.createRequest().create(ServiceNews.class);
    public ArrayDeque<Article> articles = new ArrayDeque<>();

    Sheldue(String chat_id){
        this.chat_id = chat_id;
    }

    private Item getNews() throws IOException{
        System.out.println("getNews method");
        Response response = service.getNews("ru","technology", System.getenv("news_token")).execute();
        assert response.body() != null;
        return (Item) response.body();
    }

    public ArrayDeque<Article> getArticles(Item item){
        System.out.println("getArticles method");
        return articles = new ArrayDeque<>(item.getArticles());
    }

    private boolean isArticlesIsEmpty(){
        System.out.println("isArticlesIsEmpty method");
        System.out.println(articles);
       return articles.isEmpty();
    }

    private void sendNews(){
        if(isArticlesIsEmpty()){
            try {
                System.out.println("Try to get articles");
                getArticles(getNews());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Take one article from new");

            article =  articles.pollFirst();
        }
        else{
            System.out.println("Take one article");
            article = articles.pollFirst();
        }
        System.out.println("Sending message");
        bot.sendMessage(chat_id,article.getUrl());
    }


    public void onStart() {
        bot = new Bot();
        ses = Executors.newScheduledThreadPool(1);
        Runnable pinger = new Runnable() {
            public void run(){
                sendNews();
            }
        };
       result = ses.scheduleWithFixedDelay(pinger, 0, 5, TimeUnit.MINUTES);
    }

    public void onStop(){
        result.cancel(true);
    }

}
