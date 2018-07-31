package pl.against.inventoryapp;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import pl.against.inventoryapp.data.InventoryContract.InventoryEntry;
import pl.against.inventoryapp.data.InventoryDbHelper;


/**
 * Allows user to create a new product or edit an existing one.
 */
public class NewRecordsActivity extends AppCompatActivity {

    /**
     * EditText field to enter the product's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the product's price
     */
    private EditText mPriceEditText;

    /**
     * EditText field to enter the product quantity
     */
    private EditText mQuantityEditText;

    /**
     * EditText field to enter the supplier's name
     */
    private EditText mSupplierEditText;

    /**
     * EditText field to enter the supplier's phone number
     */
    private EditText mPhoneEditText;

    /**
     * EditText field to enter the size
     */
    private Spinner mSizeSpinner;

    private int mSize = InventoryEntry.SIZE_MEDIUM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecords);

        // Find all relevant views that we will need to read user input from
        mNameEditText = findViewById(R.id.edit_product_name);
        mPriceEditText = findViewById(R.id.edit_product_price);
        mQuantityEditText = findViewById(R.id.edit_product_quantity);
        mSizeSpinner = findViewById(R.id.spinner_size);
        mSupplierEditText = findViewById(R.id.edit_supplier_name);
        mPhoneEditText = findViewById(R.id.edit_supplier_phone);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the size of the product.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter sizeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_size_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mSizeSpinner.setAdapter(sizeSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.size_medium))) {
                        mSize = InventoryEntry.SIZE_MEDIUM;
                    } else if (selection.equals(getString(R.string.size_big))) {
                        mSize = InventoryEntry.SIZE_BIG;
                    } else {
                        mSize = InventoryEntry.SIZE_SMALL;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSize = InventoryEntry.SIZE_MEDIUM;
            }
        });
    }

    /**
     * Get user input from editor and save new product into database.
     */
    private void insertProduct() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        String quantityString = mQuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String supplierString = mSupplierEditText.getText().toString().trim();
        String phoneString = mPhoneEditText.getText().toString().trim();
        int phone = Integer.parseInt(phoneString);
        // Create database helper
        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a ContentValues object where column names are the keys,
        // and products attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(InventoryEntry.COLUMN_PRODUCT_SIZE, mSize);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierString);
        values.put(InventoryEntry.COLUMN_SUPPLIER_PHONE, phone);
        // Insert a new row for product in the database, returning the _ID of that new row.
        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row _ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving product", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row _ID.
            Toast.makeText(this, "Product saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_newrecords, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save_data:
                // Save pet to database
                insertProduct();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.delete_data:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}