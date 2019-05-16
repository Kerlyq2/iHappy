package me.tesis.ihappy.presentacion.contract;


public interface LoginContract {

    interface View {
        void successLoginUser();

        void setLoadingIndicator(boolean active);

        void setMessageError(String error);

        void setDialogMessage(String message);

        boolean isActive();
    }

    interface Presenter  {
        void loginUser(String username, String password);
    }
}
