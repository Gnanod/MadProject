package Database;

import android.provider.BaseColumns;

public final class Fee {
    private Fee(){}

    protected static class FeeDetail implements BaseColumns{

        public static final String TABLE_NAME = "fees";
        public static final String COLUMN_NAME_STUDENTID = "studentid";
        public static final String COLUMN_NAME_STUDENTNAME = "studentname";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_TYPE = "type";

    }
}
