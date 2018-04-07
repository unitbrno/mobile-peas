package skodavox.peas.unitbrno.cz.skodavoxapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.RecyclerTouchListener;
import skodavox.peas.unitbrno.cz.skodavoxapp.adapters.MeetingsAdapter;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.InvitationMeeting;

import static skodavox.peas.unitbrno.cz.skodavoxapp.constants.GeneralConstants.INTENT_MEETING_NAME;

public class MeetingsActivity extends AppCompatActivity {

    private List<InvitationMeeting> meetingsList = new ArrayList<>();
    @BindView(R.id.recycler_view_meetings)
    RecyclerView recyclerView;
    private MeetingsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        ButterKnife.bind(this);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position){
                InvitationMeeting inivitatonMeeting = meetingsList.get(position);
                Intent intent = new Intent(MeetingsActivity.this, ActiveRecordSessionActivity.class);
                intent.putExtra(INTENT_MEETING_NAME, inivitatonMeeting.getName());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position){}

        }));



        mAdapter = new MeetingsAdapter(meetingsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    @OnClick(R.id.btn_back_calendar)
    public void backPickingCalendar() {
        startActivity(new Intent(MeetingsActivity.this, CalendarPickerActivity.class));
    }

    private void prepareMovieData() {
        InvitationMeeting meeting = new InvitationMeeting("Developer meeting", "Development etc");
        meetingsList.add(meeting);

        meeting = new InvitationMeeting("Financial meeting", "Budget so on");
        meetingsList.add(meeting);

        mAdapter.notifyDataSetChanged();
    }
}
