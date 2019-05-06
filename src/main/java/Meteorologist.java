import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Meteorologist {
    private Retrofit retrofit;

    public Retrofit createRequest(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
