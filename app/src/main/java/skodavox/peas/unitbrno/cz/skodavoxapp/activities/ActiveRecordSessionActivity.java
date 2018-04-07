package skodavox.peas.unitbrno.cz.skodavoxapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.adapters.AttenderAdapter;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.Attender;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.User;

import static skodavox.peas.unitbrno.cz.skodavoxapp.constants.GeneralConstants.INTENT_MEETING_NAME;

public class ActiveRecordSessionActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_attenders)
    RecyclerView recyclerView;

    @BindView(R.id.status_bar)
    TextView statusBar;

    @BindView(R.id.chronometer)
    Chronometer chronometer;

    AttenderAdapter adapter;

    List<Attender> attenders = new ArrayList<Attender>() {{
        add(new Attender(new User("Petr", "Java developer"), Attender.AttenderStatus.SPEAKING));
        add(new Attender(new User("Katka", "UI desginer"), Attender.AttenderStatus.ACTIVE));
        add(new Attender(new User("Tomas", "Project manager"), Attender.AttenderStatus.INVITED));
    }};
    @BindView(R.id.title_meeting_name)
    TextView titleMeetingName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_record_session);
        ButterKnife.bind(this);
        adapter = new AttenderAdapter(this, attenders);
        setData();
        setupRecyclerView();
    }

    @OnClick(R.id.btn_back)
    public void gotoMeetings() {
        startActivity(new Intent(ActiveRecordSessionActivity.this, MeetingsActivity.class));
    }


    @OnClick(R.id.btn_end)
    public void stopRecording() {
        startActivity(new Intent(ActiveRecordSessionActivity.this, FinishedRecordSessionActivity.class));
    }

    private void setData() {
        if (getIntent().hasExtra(INTENT_MEETING_NAME)) {
            titleMeetingName.setText(getIntent().getStringExtra(INTENT_MEETING_NAME));
        }
        statusBar.setText(getString(R.string.state_decision));
        chronometer.start();
        //todo set status bar text according to keywords
    }

    private void setupRecyclerView() {
        adapter = new AttenderAdapter(this, attenders);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
    }
}
