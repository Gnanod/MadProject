package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.guruapp.marks_add;

import java.util.ArrayList;
import java.util.List;

import Model.ExamMarkDTO;
import Model.StudentDTO;

public class DBHelper extends SQLiteOpenHelper {


    private  static final String DATABASE_NAME = "GuruApp2.db";


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CreateExamMarksTableSQL = "CREATE TABLE "+ExamMarks.Marks.TABLE_Name+" ("+

                ExamMarks.Marks.MARK_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT,"+
                ExamMarks.Marks.Exam_ID+" Text,"+
                ExamMarks.Marks.Student_Id +" Text,"+
                ExamMarks.Marks.Student_Center+" Text,"+
                ExamMarks.Marks.Student_Marks+" Real)";

        sqLiteDatabase.execSQL(CreateExamMarksTableSQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF exists "+ExamMarks.Marks.TABLE_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean SaveMarkDetails(ExamMarkDTO examMarksDto){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExamMarks.Marks.Exam_ID,examMarksDto.getExam_ID());
        values.put(ExamMarks.Marks.Student_Id,examMarksDto.getStudent_Id());
        values.put(ExamMarks.Marks.Student_Center,examMarksDto.getStudent_Center());
        values.put(ExamMarks.Marks.Student_Marks,examMarksDto.getStudent_Marks());

        long result= db.insert(ExamMarks.Marks.TABLE_Name,null,values);

        if(result>0){
            return true;

        }else{
            return false;
        }

    }

    public List<ExamMarkDTO> searchMarksDetails(String examId,String studentId){

        SQLiteDatabase db  = getReadableDatabase();

        String [] projection ={
                ExamMarks.Marks.MARK_ID,
                ExamMarks.Marks.Student_Id,
                ExamMarks.Marks.Exam_ID,
                ExamMarks.Marks.Student_Marks,
                ExamMarks.Marks.Student_Center
        };

        String selection = ExamMarks.Marks.Exam_ID + " = ? and "+ ExamMarks.Marks.Student_Id + " = ?";

        String []selectionArgs = {examId,studentId};

        Cursor cursor = db.query(ExamMarks.Marks.TABLE_Name,projection ,selection,selectionArgs,null,null,null);


        List<ExamMarkDTO> examMarksList = new ArrayList<>();


        while(cursor.moveToNext()) {

            ExamMarkDTO d = new ExamMarkDTO();

            d.setStudent_Id(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Exam_ID)));
            d.setStudent_Center(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Student_Center)));
            double marks = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Student_Marks)));
            d.setStudent_Marks(marks);
            d.setExam_ID(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Exam_ID)));
            int autoId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.MARK_ID)));
            d.setMarkId(autoId);

            examMarksList.add(d);

        }
        cursor.close();


        return examMarksList;

    }

    public List<ExamMarkDTO> performance(String examId,String center){

        SQLiteDatabase db  = getReadableDatabase();

        String [] projection ={

                ExamMarks.Marks.MARK_ID,
                ExamMarks.Marks.Student_Id,
                ExamMarks.Marks.Exam_ID,
                ExamMarks.Marks.Student_Marks,
                ExamMarks.Marks.Student_Center,
//              RANK () OVER ( ORDER BY "+ExamMarks.Marks.Student_Marks+" DESC) Ranks"
        };



        String selection =ExamMarks.Marks.Exam_ID + " = ? and "+ ExamMarks.Marks.Student_Center + " = ? ";

        String []selectionArgs = {examId,center};


        String sortOrder =ExamMarks.Marks.Student_Marks + " DESC ";

        //  Cursor cursor = db.query(ExamMarks.Marks.TABLE_Name,projection ,selection,selectionArgs,null,null,sortOrder);
        //Cursor cu = db.query("SELECT markId, studentId, examId,studentCenter,RANK() over( order by studentMarks desc) Ranks FROM Marks WHERE examId = ? and studentCenter = ?");
        //  db.execSQL("SELECT markId, studentId, examId,studentCenter,RANK() over( order by studentMarks desc) Ranks FROM Marks WHERE examId = ? and studentCenter = ?");
        //db.execSQL("SELECT markId, studentId, examId,studentCenter,RANK() over( order by studentMarks desc) Ranks FROM Marks WHERE examId = ? and studentCenter = ?");
        // SELECT markId, studentId, examId,studentCenter,RANK() over( order by studentMarks desc) Ranks FROM Marks WHERE examId = ? and studentCenter = ?

        Cursor cursor  = db.query(ExamMarks.Marks.TABLE_Name,projection ,selection,selectionArgs,null,null,sortOrder);

        List<ExamMarkDTO> examMarksList = new ArrayList<>();

        while(cursor.moveToNext()) {


            ExamMarkDTO d = new ExamMarkDTO();

            d.setStudent_Id(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Student_Id)));
            d.setStudent_Center(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Student_Center)));
            double marks = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Student_Marks)));
            d.setStudent_Marks(marks);
            d.setExam_ID(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.Exam_ID)));
            int autoId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ExamMarks.Marks.MARK_ID)));
            d.setMarkId(autoId);

//            int rank = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Rank")));
            // d.setRank(rank);

            examMarksList.add(d);

        }
        cursor.close();


        return examMarksList;

    }


    public int deleteMarks(String deleteId) {

        SQLiteDatabase db = getReadableDatabase();

        String selection = ExamMarks.Marks.MARK_ID + " LIKE ?";

        System.out.println("DeleteID"+deleteId);

        String [] selectionArgs = { deleteId.trim() };

        int deletedRows = db.delete(ExamMarks.Marks.TABLE_Name, selection, selectionArgs);

        if(deletedRows > 0){
            return 1;
        }else{
            return -1;
        }
    }


    public boolean updateMarks(ExamMarkDTO marks){


        SQLiteDatabase db = getReadableDatabase();

        ContentValues  values = new ContentValues();

        values.put(ExamMarks.Marks.Student_Marks,marks.getStudent_Marks());
        values.put(ExamMarks.Marks.Student_Center,marks.getStudent_Center());
        values.put(ExamMarks.Marks.Student_Id,marks.getStudent_Id());
        values.put(ExamMarks.Marks.Exam_ID,marks.getExam_ID());
        values.put(ExamMarks.Marks.MARK_ID,marks.getMarkId());

        String selection = ExamMarks.Marks.Exam_ID + " LIKE ?";
        String[] selectionArgs = { marks.getExam_ID() };

        int count = db.update(
                ExamMarks.Marks.TABLE_Name,
                values,
                selection,
                selectionArgs);

        if(count>0){

            return true;

        }else{
            return false;
        }

    }


    public String LoginFunction(String studentId, String nic) {


        SQLiteDatabase db  = getReadableDatabase();

        String [] projection ={
                Student.student.COLUMN_STUDENT_NIC
        };

        String selection = Student.student.COLUMN_STUDENT_ID + " = ? and "+ Student.student.COLUMN_STUDENT_NIC + " = ?";

        String []selectionArgs = {studentId,nic};

        Cursor cursor = db.query(Student.student.TABLE_NAME,projection ,selection,selectionArgs,null,null,null);


        StudentDTO s1 = new StudentDTO();

        String s2 = null;

        while(cursor.moveToNext()) {

            s2=cursor.getString(cursor.getColumnIndexOrThrow(Student.student.COLUMN_STUDENT_NIC));

        }
        cursor.close();


        return s2;

    }
}
