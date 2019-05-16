package me.tesis.ihappy.data.remote.Request;


import me.tesis.ihappy.data.entities.PersonEntity;
import me.tesis.ihappy.data.entities.ResponseEntity;
import me.tesis.ihappy.data.loginData;
import me.tesis.ihappy.data.remote.ApiConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserRequest {
    @FormUrlEncoded
    @POST(ApiConstants.LOGIN)
    Call<ResponseEntity> loginUser(@Header("Content-type") String contentType, @Field("username") String username,@Field("password") String password);
    /*Call<loginData> loginUser (@Body loginData loginData);*/
    @GET("/api/usuarios/{id}")
    Call<PersonEntity> getPerson(@Path("id") String person_id);

    @FormUrlEncoded
    @POST(ApiConstants.REGISTER)
    Call<ResponseEntity> registerUser (@Header("Content-type") String contentType, @Field("first_name") String firstname,
                                       @Field("last_name") String lastname, @Field("email") String email,
                                       @Field("username") String username, @Field("password") String password,
                                       @Field("genre") String genre, @Field("born_date") String borndate);



}