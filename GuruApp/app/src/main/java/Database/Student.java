package Database;

import android.provider.BaseColumns;

import android.provider.BaseColumns;

public final class Student {

    private Student(){

    }

    public static class student implements BaseColumns{
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_STUDENT_ID = "studentId";
        public static final String COLUMN_STUDENT_Name = "studentName";
        public static final String COLUMN_STUDENT_NIC= "studentNIC";
        public static final String COLUMN_STUDENT_PHONE = "studentPhone";
        public static final String COLUMN_STUDENT_EMAIL = "studentEmail";
    }
}

