package in.faizal.travansoft.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.faizal.travansoft.R;
import in.faizal.travansoft.adapter.ItemAdapter;
import in.faizal.travansoft.model.ItemModel;
import in.faizal.travansoft.utils.ItemRatingDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadRatings(); // Load ratings from the local database
        RatingActivity.setMainItemAdapter(itemAdapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);

        // Sample data
        List<ItemModel> itemList = new ArrayList<>();
        itemList.add(new ItemModel(1, R.drawable.chair_item, "Patio Chair", "0.0","",""));
        itemList.add(new ItemModel(2, R.drawable.high_angle_bed, "High Angle Bed", "0.0","",""));

        // Initialize the adapter
        itemAdapter = new ItemAdapter(itemList, this);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(itemAdapter);
    }

    private void loadRatings() {
        // Fetch ratings from the local database and update the item models
        ItemRatingDatabaseHelper dbHelper = new ItemRatingDatabaseHelper(this);

        for (ItemModel item : itemAdapter.getItemList()) {
            float rating = dbHelper.getRating(item.getItemId()); // Use item name, not item rating
            item.setRating(String.valueOf(rating));
        }

        // Notify the adapter that data has changed
        itemAdapter.notifyDataSetChanged();
    }
}
