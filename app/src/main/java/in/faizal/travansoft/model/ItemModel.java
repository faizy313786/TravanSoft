package in.faizal.travansoft.model;

// ItemModel.java
public class ItemModel {
    private int imageResource;
    private String itemName;
    private String itemRating;
    private String userName;
    private String userFeedback;
    private int itemId;

    public ItemModel(int itemId, int imageResource, String itemName, String itemRating, String userName, String userFeedback) {
        this.itemId = itemId;
        this.imageResource = imageResource;
        this.itemName = itemName;
        this.itemRating = itemRating;
        this.userName = userName;
        this.userFeedback = userFeedback;
    }

    public int getItemId() {
        return itemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return itemName;
    }

    public String getItemRating() {
        return itemRating;
    }

    public void setRating(String valueOf) {
    }

}
