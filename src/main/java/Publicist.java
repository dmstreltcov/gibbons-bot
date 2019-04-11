import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Publicist {

    private Retrofit retrofit;

    public Retrofit createRequest(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       return retrofit;
    }



}
