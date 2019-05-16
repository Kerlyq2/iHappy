package me.tesis.ihappy.data.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceFactory {
    public  <S> S createService(Class<S> serviceClass) {

        OkHttpClient httpClient = new OkHttpClient();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(WebService.BASE)
                        .addConverterFactory(GsonConverterFactory.create());
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = builder.client(httpClient).client(client).build();
        return retrofit.create(serviceClass);
    }
}
