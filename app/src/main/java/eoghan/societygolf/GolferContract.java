package eoghan.societygolf;

import android.provider.BaseColumns;

/**
 * API Contract for the SocietyGolf app.
 */
public final class GolferContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private GolferContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class GolferEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "golfers";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the Golfer.
         *
         * Type: TEXT
         */
        public final static String COLUMN_GOLFER_NAME ="name";

        /**
         * Golfers playing handicap
         *
         * Type: TEXT
         */
        public final static String COLUMN_HANDICAP = "handicap";

        /**
         * Gender of the golfer.
         *
         * The only possible values are {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
         * or {@link #GENDER_FEMALE}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_GENDER = "gender";

        /**
         * Contact number for the golfer.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PHONENO = "phoneno";

        /**
         * Possible values for the gender of the golfer.
         */
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }

}

