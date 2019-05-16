package me.tesis.ihappy.presentacion.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.tesis.ihappy.R;
import me.tesis.ihappy.presentacion.contract.LoginContract;
import me.tesis.ihappy.presentacion.presenter.LoginPresenter;
import me.tesis.ihappy.presentacion.util.ProgressDialogCustom;


public class LoginActivity extends AppCompatActivity implements LoginContract.View, Validator.ValidationListener {

    @NotEmpty(message = "Username no válido")
    @BindView(R.id.text_username) EditText etUsuario;
    @NotEmpty(message = "Este campo no puede ser vacío")
    @BindView(R.id.text_password) EditText etPassword;
    @BindView(R.id.linear_layout)
    LinearLayout container;
    private Validator validator;
    private LoginContract.Presenter presenter;
    private ProgressDialogCustom mProgressDialogCustom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        presenter = new LoginPresenter(this, getApplicationContext());
        mProgressDialogCustom = new ProgressDialogCustom(this, "Iniciando Sesión...");
    }
    @OnClick({R.id.button_signin, R.id.btn_register})
    void onClickView(View view) {
        if (view.getId() == R.id.button_signin) {
            closeKeyboard();
            validator.validate();
        } else {
            if(view.getId() == R.id.btn_register){
                closeKeyboard();
                openActivity(RegisterActivity.class);
            }else{
                finish();
            }
        }
    }
    @Override
    public void successLoginUser() {
        openActivity(MainActivity.class);
        finish();

    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (mProgressDialogCustom != null) {
            if (active) {
                mProgressDialogCustom.show();
            } else {
                mProgressDialogCustom.dismiss();
            }
        }

    }

    @Override
    public void setMessageError(String error) {
        showMessage(error);
    }

    @Override
    public void setDialogMessage(String message) {
        showMessage(message);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public boolean isActive() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onValidationSucceeded() {
        presenter.loginUser(etUsuario.getText().toString().trim(), etPassword.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else if (view instanceof TextView) {
                ((TextView) view).setError(message);
            } else {
                setMessageError(message);
            }
        }

    }
    public void showMessage(String message) {
        this.showMessageSnack(container, message, R.color.rojo);
    }

    public void showMessageSnack(View container, String message, int colorResource) {
        if (container != null) {
            Snackbar snackbar = Snackbar
                    .make(container, message, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(this, colorResource));
            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } else {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
    public void showMessageError(String message) {
        LinearLayout container = findViewById(R.id.linear_layout);
        this.showMessageSnack(container, message, R.color.rojo);
    }

    public void openActivity(Class<?> activity) {
        Intent i = new Intent(LoginActivity.this, activity);
        startActivity(i);
    }

    private void closeKeyboard() {
        View view = LoginActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
