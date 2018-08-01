package pl.against.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pl.against.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Database helper for Inventory app. Manages database creation and version management.
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "inventory.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link InventoryDbHelper}.
     *
     * @param context of the app
     */
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_PRODUCTS_TABLE = " CREATE TABLE " + InventoryEntry.TABLE_NAME + " (" +
                InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                InventoryEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, " +
                InventoryEntry.COLUMN_PRODUCT_SIZE + " INTEGER NOT NULL, " +
                InventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, " +
                InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, " +
                InventoryEntry.COLUMN_SUPPLIER_PHONE + " INTEGER NOT NULL);";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + InventoryEntry.TABLE_NAME);
        onCreate(db);
    }
}