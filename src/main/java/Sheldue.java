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
    public ArrayDeque<Article> articles;

    Sheldue(String chat_id){
        this.chat_id = chat_id;
    }

    private Item getNews() throws IOException{
        Response response = service.getNews("ru","technology", System.getenv("news_token")).execute();
        assert response.body() != null;
        return (Item) response.body();
    }

    public ArrayDeque<Article> getArticles(Item item){
        return articles = new ArrayDeque<>(item.getArticles());
    }

    private boolean isArticlesIsEmpty(){
       return articles.isEmpty();
    }



    public void onStart() {
        bot = new Bot();
        ses = Executors.newScheduledThreadPool(1);
        Runnable pinger = new Runnable() {
            public void run(){
                if(isArticlesIsEmpty()){
                    try {
                        getArticles(getNews());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    article =  articles.pollFirst();
                    bot.sendMessage(chat_id,article.getTitle());
                }
                else{
                article = articles.pollFirst();
                }
                bot.sendMessage(chat_id,article.getTitle());
            }
        };
       result = ses.scheduleWithFixedDelay(pinger, 5, 30, TimeUnit.MINUTES);
    }

    public void onStop(){
        result.cancel(true);
    }

}
