package in.faizal.travansoft.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemRatingDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "item_ratings.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ITEM_RATINGS = "item_ratings";
    private static final String COLUMN_ITEM_ID = "item_id";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_USER_RATED = "user_rated";
    private static final String COLUMN_FEEDBACK = "feedback"; // Add feedback column

    public ItemRatingDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the item_ratings table
        String createTableQuery = "CREATE TABLE " + TABLE_ITEM_RATINGS + " ("
                + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_RATING + " REAL,"
                + COLUMN_USER_RATED + " INTEGER,"
                + COLUMN_FEEDBACK + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    @SuppressLint("Range")
    public float getRating(int itemId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEM_RATINGS, new String[]{COLUMN_RATING},
                COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)}, null, null, null, null);

        float rating = 0.0f;
        if (cursor != null && cursor.moveToFirst()) {
            rating = cursor.getFloat(cursor.getColumnIndex(COLUMN_RATING));
            cursor.close();
        }

        return rating;
    }

    @SuppressLint("Range")
    public String getFeedback(int itemId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEM_RATINGS, new String[]{COLUMN_FEEDBACK},
                COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)}, null, null, null, null);

        String feedback = "";
        if (cursor != null && cursor.moveToFirst()) {
            feedback = cursor.getString(cursor.getColumnIndex(COLUMN_FEEDBACK));
            cursor.close();
        }

        return feedback;
    }

    public boolean hasUserRated(int itemId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEM_RATINGS, new String[]{COLUMN_USER_RATED},
                COLUMN_ITEM_ID + "=? AND " + COLUMN_USER_RATED + "=?",
                new String[]{String.valueOf(itemId), String.valueOf(1)}, null, null, null, null);

        boolean hasRated = cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }

        return hasRated;
    }

    public void saveRating(int itemId, float rating, String feedback) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_ID, itemId);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_USER_RATED, 1); // Mark user as rated
        values.put(COLUMN_FEEDBACK, feedback);

        db.insert(TABLE_ITEM_RATINGS, null, values);
        db.close();
    }
}