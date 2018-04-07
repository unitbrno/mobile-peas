package skodavox.peas.unitbrno.cz.skodavoxapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skodavox.peas.unitbrno.cz.skodavoxapp.R;
import skodavox.peas.unitbrno.cz.skodavoxapp.adapters.DetailRecordAdapter;
import skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests.DevRunner;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.DetailRecord;
import skodavox.peas.unitbrno.cz.skodavoxapp.utils.RecyclerItemTouchHelper;

public class DetailRecordActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    DetailRecordAdapter adapter;
//    ArrayList<String> sentences = (ArrayList<String>) SharedState.create().get("sentences");
    List<DetailRecord> recordList = new ArrayList<DetailRecord>() {{
        add(new DetailRecord("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac elit scelerisque, tempor justo sit amet, consequat metus. Phasellus ut dui a felis condimentum eleifend condimentum non dui. Phasellus nisl mi, elementum vitae lobortis sed, porta vel libero. Aenean sed mi rhoncus, eleifend libero eget, laoreet odio. Integer sit amet est enim. In eget gravida mi. Proin pharetra sollicitudin congue. Vivamus non consequat orci, vitae ullamcorper dui. Nam in iaculis enim, eget egestas ex. In nec sem posuere, gravida magna vel, aliquam eros. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed lobortis ligula quis purus vestibulum viverra. Vestibulum maximus nibh turpis, in sagittis purus consectetur et. Maecenas condimentum luctus libero id volutpat. " +
                "Nulla vitae magna condimentum, egestas leo quis, feugiat nisl. "));

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_record);

        ButterKnife.bind(this);

        adapter = new DetailRecordAdapter(recordList);
        setupRecyclerView();

        DevRunner.runSometing();

    }

    @OnClick(R.id.btn_back)
    public void goBackToSummary() {
        startActivity(new Intent(DetailRecordActivity.this, MeetingsActivity.class));
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        // backup of removed item for undo purpose
        final DetailRecord deletedItem = recordList.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        // remove the item from recycler view
        adapter.removeItem(viewHolder.getAdapterPosition());

        // showing snack bar with Undo option
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, getString(R.string.detail_record_removed), Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // undo is selected, restore the deleted item
                adapter.restoreItem(deletedItem, deletedIndex);
            }
        });
        snackbar.setActionTextColor(getColor(R.color.colorPrimary));
        snackbar.show();
    }
}
