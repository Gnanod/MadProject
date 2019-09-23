package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.ExamMarkDTO;
import Model.FeeDTO;

public class DBHandlerFeeManagement extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "GuruApp3.db";

    public DBHandlerFeeManagement(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Fee.FeeDetail.TABLE_NAME + " ("+
                        Fee.FeeDetail.COLUMN_NAME_STUDENTID + " Text PRIMARY KEY," +
                        Fee.FeeDetail.COLUMN_NAME_STUDENTNAME + " Text," +
                        Fee.FeeDetail.COLUMN_NAME_YEAR + " INTEGER," +
                        Fee.FeeDetail.COLUMN_NAME_MONTH + " Text," +
                        Fee.FeeDetail.COLUMN_NAME_AMOUNT + " Real," +
                        Fee.FeeDetail.COLUMN_NAME_TYPE + " Text)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean SaveFeeDetails(FeeDTO FeesDto){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Fee.FeeDetail.COLUMN_NAME_STUDENTID,FeesDto.getStudentId());
        values.put(Fee.FeeDetail.COLUMN_NAME_STUDENTNAME,FeesDto.getStudentName());
        values.put(Fee.FeeDetail.COLUMN_NAME_YEAR,FeesDto.getYear());
        values.put(Fee.FeeDetail.COLUMN_NAME_MONTH,FeesDto.getMonth());
        values.put(Fee.FeeDetail.COLUMN_NAME_AMOUNT,FeesDto.getAmount());
        values.put(Fee.FeeDetail.COLUMN_NAME_TYPE,FeesDto.getType());

        long result= db.insert(Fee.FeeDetail.TABLE_NAME,null,values);

        if(result>0){
            return true;

        }else{
            return false;
        }

    }

    public List<FeeDTO> searchFeeDetails(String studentid, String month, String year) {


        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGJJJJJJJJJJJJJJJKKKKKKKKKKKKKKKKKKK");
        SQLiteDatabase db  = getReadableDatabase();

        String [] projection ={

                Fee.FeeDetail.COLUMN_NAME_STUDENTNAME,
                Fee.FeeDetail.COLUMN_NAME_YEAR,
                Fee.FeeDetail.COLUMN_NAME_MONTH,
                Fee.FeeDetail.COLUMN_NAME_AMOUNT,
                Fee.FeeDetail.COLUMN_NAME_TYPE
        };

        String selection = Fee.FeeDetail.COLUMN_NAME_STUDENTID + " = ? and "+ Fee.FeeDetail.COLUMN_NAME_MONTH + " = ? and "+ Fee.FeeDetail.COLUMN_NAME_YEAR + " = ?";

        String []selectionArgs = {studentid,month,year};

        Cursor cursor = db.query(Fee.FeeDetail.TABLE_NAME,projection ,selection,selectionArgs,null,null,null);

        List<FeeDTO> feeDetailList = new ArrayList<>();


        while(cursor.moveToNext()) {

            FeeDTO d = new FeeDTO();
            d.setStudentName(cursor.getString(cursor.getColumnIndexOrThrow(Fee.FeeDetail.COLUMN_NAME_STUDENTNAME)));
            int y = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(Fee.FeeDetail.COLUMN_NAME_YEAR)));
            d.setYear(y);
            d.setMonth(cursor.getString(cursor.getColumnIndexOrThrow(Fee.FeeDetail.COLUMN_NAME_MONTH)));
            double amt = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(Fee.FeeDetail.COLUMN_NAME_AMOUNT)));
            d.setAmount(amt);
            d.setType(cursor.getString(cursor.getColumnIndexOrThrow(Fee.FeeDetail.COLUMN_NAME_TYPE)));
            d.setStudentId(studentid);



            feeDetailList.add(d);

        }
        cursor.close();


        return feeDetailList;

    }

    public boolean updateFeeDetails(FeeDTO fees){

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(Fee.FeeDetail.COLUMN_NAME_STUDENTID,fees.getStudentId());
        values.put(Fee.FeeDetail.COLUMN_NAME_STUDENTNAME,fees.getStudentName());
        values.put(Fee.FeeDetail.COLUMN_NAME_YEAR,fees.getYear());
        values.put(Fee.FeeDetail.COLUMN_NAME_MONTH,fees.getMonth());
        values.put(Fee.FeeDetail.COLUMN_NAME_AMOUNT,fees.getAmount());
        values.put(Fee.FeeDetail.COLUMN_NAME_TYPE,fees.getType());

        String selection = Fee.FeeDetail.COLUMN_NAME_STUDENTID + " LIKE ?";
        String[] selectionArgs = { fees.getStudentId() };

        int count = db.update(
                Fee.FeeDetail.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(count>0){
            return true;

        }else{
            return false;
        }


    }

    public int deleteFeeDetails(String deleteId) {

        SQLiteDatabase db = getReadableDatabase();

        String selection = Fee.FeeDetail.COLUMN_NAME_STUDENTID + " LIKE ?";

        //System.out.println("DeleteID"+deleteId);

        String [] selectionArgs = { deleteId.trim() };

        int deletedRows = db.delete(Fee.FeeDetail.TABLE_NAME, selection, selectionArgs);

        if(deletedRows > 0){
            return 1;
        }else{
            return -1;
        }
    }

}
