package pl.against.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Inventory app.
 */
public class InventoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "pl.against.inventoryapp";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_PRODUCTS = "PRODUCTS";

    /**
     * Inner class that defines constant values for the products database table.
     * Each entry in the table represents a single product.
     */
    public static final class InventoryEntry implements BaseColumns {

        /**
         * The content URI to access the product data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /**
         * Name of database table for products
         */
        public final static String TABLE_NAME = "PRODUCTS";

        /**
         * Unique _ID number for the product (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the product.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME = "NAME";

        /**
         * Price of the product.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_PRICE = "PRICE";

        /**
         * Size of the product .
         * <p>
         * The only possible values are {@link #SIZE_SMALL}, {@link #SIZE_MEDIUM},
         * or {@link #SIZE_BIG}.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_SIZE = "SIZE";

        /**
         * Quantity of products.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_QUANTITY = "QUANTITY";

        /**
         * Name of the supplier.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_SUPPLIER_NAME = "SUPPLIER_NAME";

        /**
         * Supplier phone number.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_SUPPLIER_PHONE = "PHONE_NUMBER";

        /**
         * Possible values for the sizes of the product.
         */
        public static final int SIZE_SMALL = 0;
        public static final int SIZE_MEDIUM = 1;
        public static final int SIZE_BIG = 2;

        /**
         * Returns whether or not the given size is {@link #SIZE_SMALL}, {@link #SIZE_MEDIUM},
         * or {@link #SIZE_BIG}.
         */
        public static boolean isValidSize(int size) {
            return size == SIZE_SMALL || size == SIZE_MEDIUM || size == SIZE_BIG;
        }
    }

}
