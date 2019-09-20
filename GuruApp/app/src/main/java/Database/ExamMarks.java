package Database;

import android.provider.BaseColumns;

public class ExamMarks {

    public ExamMarks() {

    }

    public static class  Marks implements BaseColumns {

        protected static final String TABLE_Name="Marks";
        protected static final String MARK_ID="markId";
        protected static final String Exam_ID="examId";
        protected static final String Student_Id="studentId";
        protected static final String Student_Marks="studentMarks";
        protected static final String Student_Center="studentCenter";


    }

}
