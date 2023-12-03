package in.faizal.travansoft.model;

public class ItemRating {
    private int itemId;
    private float rating;

    public ItemRating(int itemId, float rating) {
        this.itemId = itemId;
        this.rating = rating;
    }

    public int getItemId() {
        return itemId;
    }

    public float getRating() {
        return rating;
    }
}
