package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.StudentDTO;

public class DBHelper1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "guruapp";

    public DBHelper1(Context context){

        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlCreateTable = "CREATE TABLE "+Student.student.TABLE_NAME+" ("+
                Student.student.COLUMN_STUDENT_ID+ " TEXT,"+
                Student.student.COLUMN_STUDENT_Name+ " TEXT,"+
                Student.student.COLUMN_STUDENT_NIC+ " TEXT," +
                Student.student.COLUMN_STUDENT_PHONE+ " TEXT," +
                Student.student.COLUMN_STUDENT_EMAIL+" TEXT)";

        sqLiteDatabase.execSQL(sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public boolean addStudents(StudentDTO dto){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Student.student.COLUMN_STUDENT_ID,dto.getStudentId());
        values.put(Student.student.COLUMN_STUDENT_Name,dto.getStudentName());
        values.put(Student.student.COLUMN_STUDENT_NIC,dto.getStudentNic());
        values.put(Student.student.COLUMN_STUDENT_PHONE,dto.getStudentPhone());
        values.put(Student.student.COLUMN_STUDENT_EMAIL,dto.getStudentEmail());

        long newStu = db.insert(Student.student.TABLE_NAME,null,values);

        if(newStu>0){
            return true;

        }else{
            return false;
        }
    }

    public List<StudentDTO> searchStudent(String studentId){
        SQLiteDatabase db = getReadableDatabase();

        String [] projection ={
                Student.student.COLUMN_STUDENT_Name,
                Student.student.COLUMN_STUDENT_NIC,
                Student.student.COLUMN_STUDENT_PHONE,
                Student.student.COLUMN_STUDENT_EMAIL
        };

        String selection = Student.student.COLUMN_STUDENT_ID +"= ?";

        String []selectionArgs = {studentId};

        Cursor cursor = db.query(Student.student.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

        List<StudentDTO> studentList = new ArrayList<>();

        while (cursor.moveToNext()){

            StudentDTO ud = new StudentDTO();

            ud.setStudentName(cursor.getString(cursor.getColumnIndexOrThrow(Student.student.COLUMN_STUDENT_Name)));
            ud.setStudentNic(cursor.getString(cursor.getColumnIndexOrThrow(Student.student.COLUMN_STUDENT_NIC)));
            ud.setStudentPhone(cursor.getString(cursor.getColumnIndexOrThrow(Student.student.COLUMN_STUDENT_PHONE)));
            ud.setStudentEmail(cursor.getString(cursor.getColumnIndexOrThrow(Student.student.COLUMN_STUDENT_EMAIL)));

            studentList.add(ud);
        }

        cursor.close();
        return studentList;
    }

    public boolean updateStudent(StudentDTO newdto){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Student.student.COLUMN_STUDENT_ID,newdto.getStudentId());
        values.put(Student.student.COLUMN_STUDENT_Name,newdto.getStudentName());
        values.put(Student.student.COLUMN_STUDENT_NIC,newdto.getStudentNic());
        values.put(Student.student.COLUMN_STUDENT_PHONE,newdto.getStudentPhone());
        values.put(Student.student.COLUMN_STUDENT_EMAIL,newdto.getStudentEmail());

        String selection = Student.student.COLUMN_STUDENT_ID + " LIKE ?";
        String []selectionArgs = {newdto.getStudentId()};

        int upROw = db.update(Student.student.TABLE_NAME,values,selection,selectionArgs);

        if(upROw>0){
            return  true;
        }else{

            return false;
        }

    }

    public int deleteStudent(String deleteStudentId) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = Student.student.COLUMN_STUDENT_ID + " LIKE ?";

        System.out.println("DeleteID"+deleteStudentId);

        String [] selectionArgs = {deleteStudentId.trim() };

        int deleteRows = db.delete(Student.student.TABLE_NAME,selection,selectionArgs);

        if(deleteRows > 0){
            return 1;
        }else{
            return -1;
        }
    }
}
