package skodavox.peas.unitbrno.cz.skodavoxapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnClick;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.changeText)
    TextView changeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.change)
    void clicked() {
        changeText.setText(changeText.getText() + "!");
    }
}
