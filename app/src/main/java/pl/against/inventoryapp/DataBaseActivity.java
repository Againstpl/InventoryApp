package pl.against.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import pl.against.inventoryapp.data.InventoryContract.InventoryEntry;
import pl.against.inventoryapp.data.InventoryDbHelper;

/**
 * Displays list of items that were entered and stored in the app.
 */
public class DataBaseActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataBaseActivity.this, NewRecordsActivity.class);
                startActivity(intent);
            }
        });
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new InventoryDbHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();

        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {


        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String [] projection = {

                InventoryEntry.ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRODUCT_PRICE,
                InventoryEntry.COLUMN_PRODUCT_SIZE,
                InventoryEntry.COLUMN_PRODUCT_QUANTITY
        };

        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        TextView displayView = findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The items table contains <number of rows in Cursor> items.
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The inventory table contains " + cursor.getCount() + " items.\n\n");
            displayView.append(
                    InventoryEntry.ID + " - " +
                    InventoryEntry.COLUMN_PRODUCT_NAME + "\n"+
                    InventoryEntry.COLUMN_PRODUCT_PRICE + "\n"+
                    InventoryEntry.COLUMN_PRODUCT_SIZE + "\n"+
                    InventoryEntry.COLUMN_PRODUCT_QUANTITY + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InventoryEntry.ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
            int sizeColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SIZE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentSize = cursor.getInt(sizeColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" +
                        currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentSize + " - " +
                        currentQuantity));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_database, menu);
        return true;
    }
    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertProduct() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, "Headphones");
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, "Wonder");
        values.put(InventoryEntry.COLUMN_PRODUCT_SIZE, InventoryEntry.SIZE_MEDIUM);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, 12);

        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.insert_data:
                insertProduct();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.delete_data:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}