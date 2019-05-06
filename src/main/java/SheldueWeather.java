import api.ServiceNews;
import api.ServiceWeather;
import news.Article;
import news.Item;
import retrofit2.Response;
import weather.Day;
import weather.Forecast;
import weather.Forecastday;
import weather.Weather;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SheldueWeather {

    private Bot bot;
    private ScheduledExecutorService ses;
    private ScheduledFuture result;
    private String chat_id;
    private Meteorologist meteorologist = new Meteorologist();
    private ServiceWeather service = meteorologist.createRequest().create(ServiceWeather.class);
    public ArrayDeque<Article> articles = new ArrayDeque<>();
    private List<Forecastday> forecastdays;
    private String day, condition;
    private Integer max_temp;
    private Double min_temp,wind;

    SheldueWeather(String chat_id){
        this.chat_id = chat_id;

    }

    private Weather sendWeather() throws IOException{
        System.out.println("Send weather method");
        Response response = service.getWeather(System.getenv("weather_token"),"Dubna",7).execute();
        assert response.body() != null;
        return (Weather) response.body();
    }

    public Forecast getForecast(Weather weather){
        System.out.println("getForecast method");
        return weather.getForecast();
    }

    public List<Forecastday> getForecastdays(Forecast forecast){
        return forecastdays = new ArrayList<>(forecast.getForecastday());
    }

    public String createMessage(List<Forecastday> forecastdays){
        String message = "";
        for(int i = 0; i < forecastdays.size(); i++){
            day = forecastdays.get(i).getDate();
            max_temp = forecastdays.get(i).getDay().getMaxtempC();
            min_temp = forecastdays.get(i).getDay().getMintempC();
            wind = forecastdays.get(i).getDay().getMaxwindKph();
            condition = forecastdays.get(i).getDay().getCondition().getText();
            message += "Day: " + day + "\n" +
                       "Max temp: " + max_temp + "\n" +
                        "Min temp: " + min_temp + "\n" +
                        "Wind: " + wind + "\n" +
                        "Condition: " + condition + "\n" + "\n";
        }
        return message;
    }

    private void sendNews() throws IOException{
        System.out.println("sending message");
        bot.sendMessage(chat_id,createMessage(getForecastdays(getForecast(sendWeather()))));
    }


    public ScheduledFuture onStart(){
        bot = new Bot();
        ses = Executors.newScheduledThreadPool(1);
        Runnable pinger = new Runnable() {
            public void run(){
                try {
                    sendNews();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return result = ses.scheduleWithFixedDelay(pinger, 0, 5, TimeUnit.MINUTES);
    }

    public void onStop(){
        System.out.println("try to stop bot");
        result.cancel(true);
    }

}
