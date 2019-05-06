package api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.Weather;

public interface ServiceWeather {
    @GET("forecast.json")
    Call<Weather> getWeather(@Query("key") String apikey, @Query("q") String city, @Query("days") String days );
}
//http://api.apixu.com/v1/forecast.json?key=7d675e39903f4485b9f110957190605&q=Moscow&days=7
