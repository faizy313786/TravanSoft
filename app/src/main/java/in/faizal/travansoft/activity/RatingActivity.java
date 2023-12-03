package in.faizal.travansoft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import in.faizal.travansoft.R;
import in.faizal.travansoft.adapter.ItemAdapter;
import in.faizal.travansoft.utils.ItemRatingDatabaseHelper;
import in.faizal.travansoft.utils.Preference;

// RatingActivity.java
public class RatingActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    ItemAdapter adapter;


    private static ItemAdapter mainItemAdapter;
    private TextView tvItemName, tvItemRating;
    private Button btnSubmit;
    private ImageView itemImage;
    private EditText etFeedback;
    private String feedBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        initViews();


    }

    public static void setMainItemAdapter(ItemAdapter itemAdapter) {
        mainItemAdapter = itemAdapter;
    }

    private void initViews() {
        ratingBar = findViewById(R.id.ratingBar);
        tvItemName = findViewById(R.id.tvItemName);
        tvItemRating = findViewById(R.id.tvItemRating);
        btnSubmit = findViewById(R.id.btnSubmit);
        itemImage = findViewById(R.id.ivItemImage);
        etFeedback = findViewById(R.id.editTextFeedback);
        adapter = new ItemAdapter();

        // Retrieve item information from intent
        Intent intent = getIntent();


        String itemName = intent.getStringExtra("itemName");
        int itemID = intent.getIntExtra("itemId", 0);
        int itemImageResource = intent.getIntExtra("itemImage", 0);
        float previousRating = intent.getFloatExtra("itemRating", 0.0f);
        String userFeedback = intent.getStringExtra("userFeedback");
        etFeedback.setText(userFeedback);

        // Set item information in the UI
        tvItemName.setText(itemName);
        Log.e("TAG", "initViews: " + itemName);
        itemImage.setImageResource(itemImageResource);
        tvItemRating.setText(String.valueOf(previousRating));

        // Set the initial rating if user has rated before
        ratingBar.setRating(previousRating);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user's new rating
                float userRating = ratingBar.getRating();

                // Save the rating in the local database
                ItemRatingDatabaseHelper dbHelper = new ItemRatingDatabaseHelper(RatingActivity.this);
                dbHelper.saveRating(itemID, userRating,feedBack);

                // Move this line here
                feedBack = etFeedback.getText().toString().trim();

                // Update the item in the adapter's dataset
                updateAdapterItem(itemID, userRating, Preference.getUserName(RatingActivity.this), feedBack);

                // Update the UI to show the new rating
                tvItemRating.setText(String.valueOf(userRating));
                btnSubmit.setEnabled(false); // Disable the submit button after submitting
            }
        });
    }

    private void updateAdapterItem(int itemId, float newRating,String userName,String userFeedback) {
        // Access the adapter from the RatingActivity
        if (mainItemAdapter != null) {
            // Use the adapter method to update the item rating
            mainItemAdapter.updateItemRating(itemId, newRating,userName,userFeedback);

        }
    }
}