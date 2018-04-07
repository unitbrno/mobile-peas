package skodavox.peas.unitbrno.cz.skodavoxapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.DetailRecord;
import skodavox.peas.unitbrno.cz.skodavoxapp.viewholders.DetailRecordViewHolder;

public class DetailRecordAdapter extends RecyclerView.Adapter<DetailRecordViewHolder>{

    private List<DetailRecord> recordList;

    public DetailRecordAdapter(List<DetailRecord> records) {
        this.recordList = records;
    }

    @NonNull
    @Override
    public DetailRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_record_row, parent, false);

        return new DetailRecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailRecordViewHolder holder, int position) {
        holder.onBind(recordList.get(position));
    }


    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void removeItem(int position) {
        recordList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(DetailRecord item, int position) {
        recordList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
