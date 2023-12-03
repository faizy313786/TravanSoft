package in.faizal.travansoft.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.faizal.travansoft.R;
import in.faizal.travansoft.activity.RatingActivity;
import in.faizal.travansoft.model.ItemModel;
import in.faizal.travansoft.utils.Constants;
import in.faizal.travansoft.utils.ItemRatingDatabaseHelper;
import in.faizal.travansoft.utils.Preference;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<ItemModel> itemList;
    private Context context;
    private ItemRatingDatabaseHelper dbHelper;

    public ItemAdapter(List<ItemModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
        dbHelper = new ItemRatingDatabaseHelper(context);
    }

    public ItemAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = itemList.get(position);

        holder.imageView.setImageResource(item.getImageResource());
        holder.itemNAme.setText(item.getText());
        holder.itemRating.setText(item.getItemRating());
        holder.ratedUserName.setText(Preference.getUserName(context));
        holder.userFeedback.setText(item.getUserFeedback());

        // Fetch the item rating from the database
        float rating = dbHelper.getRating(item.getItemId());
        holder.itemRating.setText(String.valueOf(rating));

        holder.itemView.setOnClickListener(v -> {
            // Check if the user has already rated the item
            if (!dbHelper.hasUserRated(item.getItemId())) {
                Intent intent = new Intent(context, RatingActivity.class);
                intent.putExtra("itemId", item.getItemId());
                intent.putExtra("itemName", item.getText());
                intent.putExtra("itemRating", rating);
                intent.putExtra("itemImage", item.getImageResource());
                intent.putExtra("userFeedback", item.getUserFeedback());
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "You have already rated this item.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public void updateItemRating(int itemId, float newRating,String userName,String feedback) {
        // Iterate through the items in the dataset
        for (int i = 0; i < itemList.size(); i++) {
            ItemModel item = itemList.get(i);

            // Find the item with matching ID
            if (item.getItemId() == itemId) {
                // Update the rating of the matched item
                item.setRating(String.valueOf(newRating));
                item.setUserName(String.valueOf(userName));
                Log.e("TAG", "updateItemRating: "+feedback );
                item.setUserFeedback(String.valueOf(feedback));

                // Notify the adapter that data has changed at the specific position
                notifyItemChanged(i);
                break; // Break the loop after finding the item
            }
        }
    }
    public List<ItemModel> getItemList() {
        return itemList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView itemNAme, itemRating,ratedUserName,userFeedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivItem);
            itemNAme = itemView.findViewById(R.id.tvItemName);
            itemRating = itemView.findViewById(R.id.tvRating);
            ratedUserName = itemView.findViewById(R.id.tvUserName);
            userFeedback = itemView.findViewById(R.id.tvItemFeedback);
        }

    }
}
