package info.tayvs.testtask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tayvs on 30.11.2016.
 *
 * All SQL methods and logic are here
 */

public class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    private final String TABLE_NAME = "users";

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        db.execSQL("CREATE TABLE " + TABLE_NAME +" ("
                + "id INTEGER primary key autoincrement,"
                + "first_name TEXT,"
                + "last_name TEXT" + ");");

        fillDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    void fillDatabase(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        for (int i = 0; i < 1000; i++) {
            cv.put("first_name", "first_name" + i);
            cv.put("last_name", "last_name" + i);

            long rowID = db.insertOrThrow(TABLE_NAME, null, cv);
            if (rowID != -1) Log.d(LOG_TAG, "row inserted, ID = " + rowID);
        }

    }

    int countOFDatabaseRaws(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{"count()"}, null, null, null, null, null);

        return c.moveToFirst() ? c.getInt(c.getColumnIndex("count()")) : 0;
    }

    User selectByID(int id) {
        User user = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, null, "id=" + id, null, null, null, null);

        //Start iterate all users (from begin)
        if (c.moveToFirst()) {
            //Getting Index of column
            int firstNameColIndex = c.getColumnIndex("first_name");
            int lastNameColIndex = c.getColumnIndex("last_name");

            //getting user
            user = new User(c.getString(firstNameColIndex), c.getColumnName(lastNameColIndex));
        }


        return user;
    }
}
