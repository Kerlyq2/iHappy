package me.tesis.ihappy.data.remote.Request;


import me.tesis.ihappy.data.entities.PersonEntity;
import me.tesis.ihappy.data.entities.ResponseEntity;
import me.tesis.ihappy.data.entities.UserEntity;
import me.tesis.ihappy.data.entities.loginData;
import me.tesis.ihappy.data.entities.prueba;
import me.tesis.ihappy.data.remote.ApiConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserRequest {
    @POST(ApiConstants.LOGIN)
    Call<prueba> loginUser(@Body prueba prueba);
    /*Call<prueba> loginUser(@Header("Content-type") String contentType, @Field("email") String email,@Field("password") String password);*/


/*    @GET(ApiConstants.LOGIN)
    Call<ResponseEntity> getlogin(@Query("username") String username, @Query("password") String password);*/

    @GET(ApiConstants.LOGIN)
    Call<prueba> getlogin(@Field("username") String username, @Query("password") String password);
    @GET("/api/usuarios/{id}")
    Call<PersonEntity> getPerson(@Path("id") String person_id);

    @FormUrlEncoded
    @POST(ApiConstants.REGISTER)
    Call<ResponseEntity> registerUser (@Header("Content-type") String contentType, @Field("first_name") String firstname,
                                       @Field("last_name") String lastname, @Field("email") String email,
                                       @Field("username") String username, @Field("password") String password,
                                       @Field("genre") String genre, @Field("born_date") String borndate);



}