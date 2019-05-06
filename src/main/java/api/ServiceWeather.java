package api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.Weather;

public interface ServiceWeather {
    @GET("forecast.json")
    Call<Weather> getWeather(@Query("key") String apikey, @Query("q") String city, @Query("days") int days );
}
