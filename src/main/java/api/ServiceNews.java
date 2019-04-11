package api;

import news.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceNews {
    @GET("/top-headlines")
    Call<Item> getNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey );
}
