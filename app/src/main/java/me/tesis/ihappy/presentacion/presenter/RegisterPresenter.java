package me.tesis.ihappy.presentacion.presenter;

import android.content.Context;

import me.tesis.ihappy.R;
import me.tesis.ihappy.data.entities.PersonEntity;
import me.tesis.ihappy.data.entities.ResponseEntity;
import me.tesis.ihappy.data.entities.ResultRegisterEntity;
import me.tesis.ihappy.data.local.SessionManager;
import me.tesis.ihappy.data.remote.ApiConstants;
import me.tesis.ihappy.data.remote.Request.UserRequest;
import me.tesis.ihappy.data.remote.ServiceFactory;
import me.tesis.ihappy.presentacion.Fragments.RegisterFragment;
import me.tesis.ihappy.presentacion.contract.RegisterUserContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterUserContract.Presenter {

    private RegisterFragment view;
    private Context context;
    private SessionManager sessionManager;
    private ServiceFactory serviceFactory;

    public RegisterPresenter(RegisterFragment view, Context context) {
        this.view = view;
        sessionManager =new SessionManager(context);
        this.context = context;
        serviceFactory = new ServiceFactory();
    }

    @Override
    public void register(String first_name, String last_name, String email, String username, String password, String genre, String born_of_date) {
        view.setLoadingIndicator(true);
        UserRequest userRequest = serviceFactory.createService(UserRequest.class);
        Call<ResponseEntity> call = userRequest.registerUser(ApiConstants.CONTENT_TYPE, first_name, last_name,
                email, username, password, genre, born_of_date);
        call.enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                if (response.isSuccessful()) {
                    ResponseEntity responseEntity = response.body();
                    if (responseEntity.isStatus()) {
                       openHome(responseEntity);
                    } else {
                        view.setLoadingIndicator(false);
                        view.setMessageError(context.getString(R.string.there_was_an_error_try_it_later));
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


    }
    private void openHome(final ResponseEntity responseEntity) {
        UserRequest userRequest = serviceFactory.createService(UserRequest.class);
        Call<PersonEntity> call = userRequest.getPerson(String.valueOf(responseEntity.getPerson_id()));
        call.enqueue(new Callback<PersonEntity>() {
            @Override
            public void onResponse(Call<PersonEntity> call, Response<PersonEntity> response) {
                if (response.isSuccessful()) {
                    openSession(responseEntity, response.body());
                } else {
                    view.setLoadingIndicator(false);
                    view.setMessageError(context.getString(R.string.there_was_an_error_try_it_later));
                }
            }

            @Override
            public void onFailure(Call<PersonEntity> call, Throwable t) {

            }


        });
    }


    private void openSession(ResponseEntity responseEntity, PersonEntity personEntity) {
        /*sessionManager.openSession(responseEntity.getPerson_id()<a);
        registerMyDevice();*/
        view.registerSuccessfully();
        view.setLoadingIndicator(false);
    }
}
