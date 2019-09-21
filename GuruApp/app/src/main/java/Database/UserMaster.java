package Database;

import android.provider.BaseColumns;

public final class UserMaster {

    private UserMaster() {
    }

    public static class users implements BaseColumns{
            public static final String TABLE_NAME = "users";
            public static final String COLUMN_ID = "id";
            public static final String COLUMN_NAME = "userName";
            public static final String COLUMN_PHONE = "phone";
            public static final String COLUMN_MAIL = "email";
            public static final String COLUMN_SUBJECT = "subject";
            public static final String COLUMN_PASSWORD = "password";

    }
}
