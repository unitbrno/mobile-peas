package skodavox.peas.unitbrno.cz.skodavoxapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.Attender;
import skodavox.peas.unitbrno.cz.skodavoxapp.viewholders.AttenderViewHolder;

public class AttenderAdapter extends RecyclerView.Adapter<AttenderViewHolder>  {
    private List<Attender> attenderList;
    private Context context;

    public AttenderAdapter(Context context, List<Attender> attenderList) {
        this.attenderList = attenderList;
        this.context = context;
    }

    @NonNull
    @Override
    public AttenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attender_row, parent, false);

        return new AttenderViewHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttenderViewHolder holder, int position) {
        Attender attender = attenderList.get(position);
        holder.onBind(attender);
    }

    @Override
    public int getItemCount() {
        return attenderList.size();
    }
}
