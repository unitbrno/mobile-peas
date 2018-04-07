package skodavox.peas.unitbrno.cz.skodavoxapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.adapter.ViewDataAdapter;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.exception.ConversionException;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.utils.MyTextWatcherUtils;
import skodavox.peas.unitbrno.cz.skodavoxapp.utils.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private List<TextInputLayout> inputLayoutList;

    @NotEmpty(messageResId = R.string.error_required_field)
    @Email(messageResId = R.string.error_invalid_email)
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;

    @NotEmpty(messageResId = R.string.error_required_field)
    @BindView(R.id.input_layout_password) TextInputLayout inputLayoutPassword;

    @BindView(R.id.btn_login)
    Button loginButton;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setValidation();

        firebaseAuth = FirebaseAuth.getInstance();


    }

    @OnClick(R.id.btn_login)
    public void login() {
        firebaseAuthWithEmail(inputLayoutEmail.getEditText().toString(), inputLayoutPassword.getEditText().toString());
        startActivity(new Intent(LoginActivity.this, CalendarPickerActivity.class));
        SharedPreferencesUtil.saveEmailOfCurrentUser(this, inputLayoutEmail.getEditText().toString());
    }

    @Override
    public void onValidationSucceeded() {
        loginButton.setAlpha(1);
        loginButton.setEnabled(true);
        MyTextWatcherUtils.clearAllInputs(inputLayoutList);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        MyTextWatcherUtils.clearAllInputs(inputLayoutList);
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof TextInputLayout) {
                ((TextInputLayout) view).setError(message);
            }
        }
    }

    private void setValidation() {
        inputLayoutList = Arrays.asList(inputLayoutEmail,inputLayoutPassword);
        Validator validator = new Validator(this);
        validator.setValidationListener(this);

        validator.registerAdapter(TextInputLayout.class,
                new ViewDataAdapter<TextInputLayout, String>() {
                    @Override
                    public String getData(TextInputLayout flet) throws ConversionException {
                        return flet.getEditText().getText().toString();
                    }
                }
        );

        MyTextWatcherUtils.setTextWatcherListeners(inputLayoutList, validator);
    }

    private void firebaseAuthWithEmail(String email, String password) {
        Log.i(TAG, "firebaseAuthWithEmail: " + email);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e(TAG, "Sign with email failed", task.getException());
                        } else if (checkUserLogin()) {
                            String userId = task.getResult().getUser().getUid();
                            Log.i(TAG, "User: " + userId + " has been logged in");
                            //startMainActivity();
                        }
                    }
                });
    }

    private boolean checkUserLogin() {
        return firebaseAuth.getCurrentUser() != null;
    }
}
