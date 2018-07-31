package pl.against.inventoryapp.data;

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
     * Inner class that defines constant values for the products database table.
     * Each entry in the table represents a single product.
     */
    public static final class InventoryEntry implements BaseColumns {

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
        public final static String COLUMN_SUPPLIER_NAME = "SUPPLIER NAME";

        /**
         * Supplier phone number.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_SUPPLIER_PHONE = "PHONE NUMBER";

        /**
         * Possible values for the sizes of the product.
         */
        public static final int SIZE_SMALL = 0;
        public static final int SIZE_MEDIUM = 1;
        public static final int SIZE_BIG = 2;
    }

}
