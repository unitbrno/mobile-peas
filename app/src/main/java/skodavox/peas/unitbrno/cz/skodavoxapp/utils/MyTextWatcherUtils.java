package skodavox.peas.unitbrno.cz.skodavoxapp.utils;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.mobsandgeeks.saripaar.Validator;

import java.util.List;
import java.util.Objects;

public class MyTextWatcherUtils {

    public static class MyTextWatcher implements TextWatcher {
        Validator validator;
        private View view;


        public MyTextWatcher(Validator validator, View view) {
            this.validator = validator;
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            validator.validate();
        }
    }

    public static void setTextWatcherListeners(List<TextInputLayout> inputLayoutList, Validator validator) {
        for (TextInputLayout textInputLayout : inputLayoutList) {

            Objects.requireNonNull(textInputLayout.getEditText()).addTextChangedListener(new MyTextWatcher(validator, textInputLayout));

        }


    }

    public static void clearAllInputs(List<TextInputLayout> inputLayoutList) {
        for (TextInputLayout textInputLayout : inputLayoutList) {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }
    }
}
