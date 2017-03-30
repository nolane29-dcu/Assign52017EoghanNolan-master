package eoghan.societygolf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for SocietyGolf app. Manages database creation and version management.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String KEY_ID="id";

    public static final String TABLE_NAME="GolfersTable";

    public static final String KEY_Name="name";

    public static final String KEY_PhoneNumber="phone_number";

    public static final String KEY_Handicap="handicap";


    public static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "golf.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link DatabaseHelper}.
     *
     * @param context of the app
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the golfers table
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+ " ("
                + KEY_ID+" INTEGER PRIMARY KEY, "
                + KEY_Name+" NOT NULL, "
                + KEY_PhoneNumber+" INT NOT NULL, "
                + KEY_Handicap+" NOT NULL)";
        db.execSQL(CREATE_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}