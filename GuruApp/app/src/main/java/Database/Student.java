package Database;

import android.provider.BaseColumns;

public class Student {

    public Student() {

    }

    public  static class StudentAtt implements BaseColumns {

        protected static final String Table_Name = "StudentTable";

        protected static final String Student_Id="StudentId";
        protected static final String Student_Name="StudentName";
        protected static final String Student_Marks="StudentMarks";
        protected static final String address="StudentAddress";


    }

}
