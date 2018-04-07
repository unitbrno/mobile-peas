package skodavox.peas.unitbrno.cz.skodavoxapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

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

public class FinishedRecordSessionActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_attenders)
    RecyclerView recyclerView;

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
        setContentView(R.layout.activity_finished_record_session);
        ButterKnife.bind(this);
        adapter = new AttenderAdapter(this, attenders);
        setData();
        setupRecyclerView();
    }

    @OnClick(R.id.btn_verdict)
    public void showDetailRecord() {
        startActivity(new Intent(FinishedRecordSessionActivity.this, DetailRecordActivity.class));
    }

    @OnClick(R.id.btn_send)
    public void sendEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"kraldavi@seznam.cz"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Zápis ze schůzky se zástupci ČAFF");
        i.putExtra(Intent.EXTRA_TEXT   , "Zápis ze schůzky se zástupci ČAFF\n10. 1. 2018\n\nZávěr:\n" +
                "SÚKL nemá informace o tom, že by nějaká léková agentura poskytovala takovýto seznam literatury. SÚKL nemá žádný seznam doporučené lokální odborné literatury k dispozici. Jednoznačné doporučení v této oblasti dle názoru SÚKL nelze vydat, neboť jednotliví držitelé rozhodnutí o registraci mají různé portfolio registrovaných přípravků a potřebují sledovat jinou oblast literatury. ");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }


    private void setData() {
        if (getIntent().hasExtra(INTENT_MEETING_NAME)) {
            titleMeetingName.setText(getIntent().getStringExtra(INTENT_MEETING_NAME));
        }
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
