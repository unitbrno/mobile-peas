package skodavox.peas.unitbrno.cz.skodavoxapp.viewholders;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.Attender;

public class AttenderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_position)
    TextView userPosition;
    @BindView(R.id.user_photo)
    ImageView userPhoto;

    @BindView(R.id.is_speaking)
    ImageView microphone;

    @BindView(R.id.user_row)
    ConstraintLayout background;

    private Context context;

    public AttenderViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Attender attender) {
        userName.setText(attender.getUser().getName());
        userPosition.setText(attender.getUser().getPosition());
        userPhoto.setImageResource(R.drawable.woman_user);
        if (attender.getAttenderStatus().name().equals(Attender.AttenderStatus.SPEAKING.name())) {
            microphone.setVisibility(View.VISIBLE);
            background.setBackgroundColor(context.getResources().getColor(R.color.light_green));
        } else if (attender.getAttenderStatus().name().equals(Attender.AttenderStatus.INVITED.name())) {
            microphone.setVisibility(View.GONE);
            background.setEnabled(false);
            background.setElevation(0.7f);
        } else {
            microphone.setVisibility(View.GONE);
        }
    }
}
