package me.tesis.ihappy.presentacion.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.Calendar;
import java.util.List;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.tesis.ihappy.R;
import me.tesis.ihappy.presentacion.activities.MainActivity;
import me.tesis.ihappy.presentacion.contract.RegisterUserContract;
import me.tesis.ihappy.presentacion.presenter.RegisterPresenter;
import me.tesis.ihappy.presentacion.util.ProgressDialogCustom;

public class RegisterFragment extends Fragment implements RegisterUserContract.View, Validator.ValidationListener, DatePickerDialog.OnDateSetListener {
    @NotEmpty(message = "Este campo no puede ser vacío")
    @BindView(R.id.username_edittext) EditText et_username;
    @Password(message = "La contraseña debe tener mínimo 6 campos")
    @BindView(R.id.password_edittext) EditText et_password;
    @NotEmpty(message = "Este campo no puede ser vacío")
    @BindView(R.id.email_edittext) EditText et_email;
    @NotEmpty(message = "Este campo no puede ser vacío")
    @BindView(R.id.name_edittext) EditText et_name;
    @NotEmpty(message = "Este campo no puede ser vacío")
    @BindView(R.id.lastname_edittext) EditText et_lastname;
    @Email(message = "Este campo no tiene el formato email")
    @BindView(R.id.genre_edittext) EditText et_genre;
    @NotEmpty(message = "Este campo no puede ser vacío")
    @BindView(R.id.born_date_edittext) EditText et_birth_date;

    private final int DATE_DIALOG_COM = 1;
    private RegisterUserContract.Presenter presenter;
    private ProgressDialogCustom mProgressDialogCustom;
    private Validator validator;
    public DatePickerDialog dpd;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View root = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        validator = new Validator(this);
        validator.setValidationListener(this);
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Registrando...");
        Calendar now = Calendar.getInstance();
        dpd = DatePickerDialog.newInstance(this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        presenter = new RegisterPresenter(this, getContext());

    }

    // funcion llamada desde la activity para validar
    public void register() {
        validator.validate();
    }

    @Override
    public void registerSuccessfully() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
    public void onValidationSucceeded() {
        presenter.register(et_name.getText().toString(), et_lastname.getText().toString(), et_email.getText().toString(),
                et_username.getText().toString(), et_password.getText().toString(), et_genre.getText().toString(),et_birth_date.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
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

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        et_birth_date.setText(year + "-" + String.format("%02d", monthOfYear + 1) + "-" + String.format("%02d", dayOfMonth));
    }

    @OnClick(R.id.born_date_edittext)
    public void onViewClicked(View view) {

        if (view.getId() == R.id.born_date_edittext) {
            dpd.show(getActivity().getFragmentManager(), "DatePickerDialog");
        }

    }
    public void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void setMessageError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
