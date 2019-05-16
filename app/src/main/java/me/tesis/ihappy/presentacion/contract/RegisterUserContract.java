package me.tesis.ihappy.presentacion.contract;

/**
 * Created by jairbarzola on 1/10/17.
 */

public interface RegisterUserContract {
    interface View {
        void registerSuccessfully();

        void setLoadingIndicator(boolean active);

        void setMessageError(String error);
    }

    interface Presenter {
        void register(String first_name, String last_name, String email,
                      String username,  String password, String genre, String born_of_date);
    }

}
