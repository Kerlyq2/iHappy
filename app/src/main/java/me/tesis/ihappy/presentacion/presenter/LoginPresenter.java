package me.tesis.ihappy.presentacion.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;


import me.tesis.ihappy.R;
import me.tesis.ihappy.data.entities.ResponseEntity;
import me.tesis.ihappy.data.loginData;
import me.tesis.ihappy.data.local.SessionManager;
import me.tesis.ihappy.data.remote.Request.UserRequest;
import me.tesis.ihappy.data.remote.ServiceFactory;
import me.tesis.ihappy.data.remote.ServiceGenerator;
import me.tesis.ihappy.presentacion.activities.LoginActivity;
import me.tesis.ihappy.presentacion.constant.Constants;
import me.tesis.ihappy.presentacion.contract.LoginContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter implements LoginContract.Presenter {
    private LoginActivity view;
    private Context context;
    private ServiceGenerator serviceGenerator;
    private SessionManager sessionManager;
    private ServiceFactory serviceFactory;
    private loginData login;


    public LoginPresenter(LoginActivity view, @NonNull Context context) {
        this.view = view;
        sessionManager = new SessionManager(context);
        this.context = context;
        serviceGenerator = new ServiceGenerator();
        serviceFactory = new ServiceFactory();

    }

    @Override
    public void loginUser(final String username, final String password) {
        try {
            view.setLoadingIndicator(true);
            final UserRequest userRequest = serviceFactory.createService(UserRequest.class);
            Call<ResponseEntity> call = userRequest.loginUser(Constants.APP_JSON,username,password);
            Log.i("uwu", "luqi");
            Log.i("uwu", String.valueOf(password));
            call.enqueue(new Callback<ResponseEntity>() {
                @Override
                public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                    if (response.isSuccessful()) {
                        ResponseEntity responsebody = response.body();
                        Log.i("Estado", "entro por aca");
                        Log.i(username, String.valueOf(password));
                        if (responsebody.status) {
                            view.successLoginUser();
                            view.setDialogMessage("Bienvenido");
                        } else {
                            view.setLoadingIndicator(false);
                            view.setMessageError("El usuario y la contraseña no coinciden");
                        }
                    } else {
                        view.setLoadingIndicator(false);
                        view.setMessageError(context.getString(R.string.there_was_an_error_try_it_later));
                    }
                }

                @Override
                public void onFailure(Call<ResponseEntity> call, Throwable t) {
                    view.setLoadingIndicator(false);
                    view.setMessageError(context.getString(R.string.no_server_connection_try_it_later));
                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setLoadingIndicator(false);
    }

}
