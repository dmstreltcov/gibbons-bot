import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class testTask {

    public Bot bot;
    public ScheduledExecutorService ses;
    public ScheduledFuture result;


    public void onPing(final String chatId){
        bot = new Bot();
        ses = Executors.newScheduledThreadPool(1);
        Runnable pinger = new Runnable() {
            public void run() {
                bot.sendMessage(chatId, "this is test ping");
            }
        };
       result = ses.scheduleAtFixedRate(pinger, 5, 10, TimeUnit.SECONDS);
    }

    public void onStop(){
        result.cancel(true);
    }

}
