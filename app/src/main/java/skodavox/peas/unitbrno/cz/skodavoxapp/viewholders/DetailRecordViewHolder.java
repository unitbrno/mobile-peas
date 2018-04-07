package skodavox.peas.unitbrno.cz.skodavoxapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.DetailRecord;

public class DetailRecordViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.record_text)
    TextView recordText;
    @BindView(R.id.user_photo)
    ImageView userPhoto;

    @BindView(R.id.view_background)
    public RelativeLayout viewBackground;

    @BindView(R.id.view_foreground)
    public RelativeLayout viewForeground ;

    public DetailRecordViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(DetailRecord detailRecord) {
        recordText.setText(detailRecord.getText());
        userPhoto.setImageResource(R.drawable.woman_user);
    }
}
