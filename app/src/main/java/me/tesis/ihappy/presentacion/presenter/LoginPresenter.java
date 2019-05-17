package me.tesis.ihappy.presentacion.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.tesis.ihappy.R;
import me.tesis.ihappy.data.entities.ResponseEntity;
import me.tesis.ihappy.data.entities.UserEntity;
import me.tesis.ihappy.data.entities.loginData;
import me.tesis.ihappy.data.entities.prueba;
import me.tesis.ihappy.data.local.SessionManager;
import me.tesis.ihappy.data.remote.ApiConstants;
import me.tesis.ihappy.data.remote.Request.UserRequest;
import me.tesis.ihappy.data.remote.ServiceFactory;
import me.tesis.ihappy.data.remote.ServiceGenerator;
import me.tesis.ihappy.presentacion.activities.LoginActivity;
import me.tesis.ihappy.presentacion.contract.LoginContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter implements LoginContract.Presenter {
    private LoginActivity view;
    private Context context;
    private SessionManager sessionManager;
    private ServiceFactory serviceFactory;
    private ServiceGenerator serviceGenerator;
    private loginData loginData;
    private prueba prueba;


    public LoginPresenter(LoginActivity view, @NonNull Context context) {
        this.view = view;
        sessionManager = new SessionManager(context);
        this.context = context;
        serviceFactory = new ServiceFactory();
        serviceGenerator = new ServiceGenerator();

    }


    @Override
    public void loginUser(final String email, final String password) {
        prueba = new prueba(email,password);
        view.setLoadingIndicator(true);
        final UserRequest userRequest = serviceGenerator.createService(UserRequest.class);
        Call<prueba> call = userRequest.loginUser(prueba);
        call.enqueue(new Callback<prueba>() {
            @Override
            public void onResponse(Call<prueba> call, Response<prueba> response) {
                Log.i("uwu", "re uwu");
                Log.i(email, String.valueOf(response.errorBody().toString()));
                if(response.isSuccessful()){
                    prueba prueba = response.body();
                    Log.i("Estado", "entro por aca");
                    if(prueba.equals(loginData.isStatus())){
                        view.successLoginUser();
                        view.setDialogMessage("Bienvenido");
                    }
                }
                view.setLoadingIndicator(false);
                view.setMessageError(context.getString(R.string.there_was_an_error_try_it_later));
            }

            @Override
            public void onFailure(Call<prueba> call, Throwable t) {
                view.setLoadingIndicator(false);
                view.setMessageError(context.getString(R.string.no_server_connection_try_it_later));
            }
        });
    }
}

