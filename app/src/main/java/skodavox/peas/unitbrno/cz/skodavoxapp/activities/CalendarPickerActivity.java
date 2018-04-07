package skodavox.peas.unitbrno.cz.skodavoxapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.retrofit.ApiClient;

public class CalendarPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_picker);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_google_calendar)
    public void getMeetingsFromGoogle() {
        startActivity(new Intent(CalendarPickerActivity.this, MeetingsActivity.class));
    }

    @OnClick(R.id.btn_outlook_calendar)
    public void getMeetingsFromOutlook() {
        Toast.makeText(this, "Not implemented for now", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_skip_calendar)
    public void skipPickingCalendar() {
        ApiClient.getUpcomingEvents(this);
        startActivity(new Intent(CalendarPickerActivity.this, MeetingsActivity.class));
    }


}
