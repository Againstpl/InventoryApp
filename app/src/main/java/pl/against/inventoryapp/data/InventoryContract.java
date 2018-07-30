package pl.against.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Pets app.
 */
public class InventoryContract  {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class InventoryEntry implements BaseColumns {

        /** Name of database table for products */
        public final static String TABLE_NAME = "PRODUCTS";

        /**
         * Unique ID number for the product (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String ID = BaseColumns._ID;

        /**
         * Name of the product.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME ="NAME";

        /**
         * Price of the product.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_PRICE = "PRICE";

        /**
         * Size of the product .
         *
         * The only possible values are {@link #SIZE_SMALL}, {@link #SIZE_MEDIUM},
         * or {@link #SIZE_BIG}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_SIZE = "SIZE";

        /**
         * Quantity of products.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_QUANTITY = "QUANTITY";

        /**
         * Possible values for the sizes of the product.
         */
        public static final int SIZE_SMALL = 0;
        public static final int SIZE_MEDIUM = 1;
        public static final int SIZE_BIG = 2;
    }

}
