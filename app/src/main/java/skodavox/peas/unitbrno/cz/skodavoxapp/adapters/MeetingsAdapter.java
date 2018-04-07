package skodavox.peas.unitbrno.cz.skodavoxapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.InvitationMeeting;

import static android.widget.Toast.LENGTH_SHORT;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.MyViewHolder> {

    private List<InvitationMeeting> meetingsList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView meetingName, startTimeBefore, meetingTime;

        public MyViewHolder(View view) {
            super(view);
            meetingName = (TextView) view.findViewById(R.id.meeting_name);
            meetingTime = (TextView) view.findViewById(R.id.meeting_time);
            startTimeBefore = (TextView) view.findViewById(R.id.start_time_before);
        }


        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Not implemented for now", LENGTH_SHORT).show();
        }
    }

    public MeetingsAdapter(List<InvitationMeeting> meetingsList) {
        this.meetingsList = meetingsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_meetings_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InvitationMeeting invitationMeeting = meetingsList.get(position);
        holder.meetingName.setText(invitationMeeting.getName());
        holder.meetingTime.setText(invitationMeeting.getDesctiption());
        holder.startTimeBefore.setText("10 min");
    }

    @Override
    public int getItemCount() {
        return meetingsList.size();
    }



}