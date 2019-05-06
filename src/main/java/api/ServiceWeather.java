package api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.Weather;

public interface ServiceWeather {
    @GET("forecast.json?key=7d675e39903f4485b9f110957190605&q=Moscow&days=7")
    Call<Weather> getWeather();
}
//http://api.apixu.com/v1/forecast.json?key=7d675e39903f4485b9f110957190605&q=Moscow&days=7
//@Query("key") String key, @Query("q") String q, @Query("dayssdf") String days