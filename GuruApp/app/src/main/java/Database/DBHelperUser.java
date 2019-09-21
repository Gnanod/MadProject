package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.UserDTO;

public class DBHelperUser extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "guruapp.db";

    public DBHelperUser(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlCreateTable = "CREATE TABLE "+UserMaster.users.TABLE_NAME+" ("+
                UserMaster.users._ID+" TEXT PRIMARY KEY,"+
                UserMaster.users.COLUMN_NAME+ " TEXT,"+
                UserMaster.users.COLUMN_PHONE+ " TEXT," +
                UserMaster.users.COLUMN_MAIL+ " TEXT," +
                UserMaster.users.COLUMN_SUBJECT+ " TEXT," +
                UserMaster.users.COLUMN_PASSWORD+" TEXT)";



        sqLiteDatabase.execSQL(sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(UserDTO dto){
                SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        
        values.put(UserMaster.users._ID,dto.getUserID());
        values.put(UserMaster.users.COLUMN_NAME,dto.getUserName());
        values.put(UserMaster.users.COLUMN_PHONE,dto.getUserPhone());
        values.put(UserMaster.users.COLUMN_MAIL,dto.getUserMail());
        values.put(UserMaster.users.COLUMN_SUBJECT,dto.getUserSubject());
        values.put(UserMaster.users.COLUMN_PASSWORD,dto.getUserPassword());

        long newuser = db.insert(UserMaster.users.TABLE_NAME,null,values);

        if(newuser>0){
            return true;
        }else{
            return false;
        }
    }


    public List<UserDTO> searchUser(String userID){
                SQLiteDatabase db = getReadableDatabase();

                String [] projection ={
                      UserMaster.users.COLUMN_NAME,
                      UserMaster.users.COLUMN_PHONE,
                      UserMaster.users.COLUMN_MAIL,
                      UserMaster.users.COLUMN_SUBJECT,
                      UserMaster.users.COLUMN_PASSWORD
                };

                String selection = UserMaster.users._ID +"= ?";

                String []selectionArgs = {userID};

        Cursor cursor = db.query(UserMaster.users.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

        List<UserDTO> usersList = new ArrayList<>();

        while(cursor.moveToNext()){

            UserDTO ud = new UserDTO();

           // ud.setUserID(cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.users._ID)));
            ud.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.users.COLUMN_NAME)));
            ud.setUserPhone(cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.users.COLUMN_PHONE)));
            ud.setUserMail(cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.users.COLUMN_MAIL)));
            ud.setUserSubject(cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.users.COLUMN_SUBJECT)));
            ud.setUserPassword(cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.users.COLUMN_PASSWORD)));

            usersList.add(ud);
        }

        cursor.close();

        return usersList;
    }


    public boolean updateUser(UserDTO newdto){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UserMaster.users._ID,newdto.getUserID());
        values.put(UserMaster.users.COLUMN_NAME,newdto.getUserName());
        values.put(UserMaster.users.COLUMN_PHONE,newdto.getUserPhone());
        values.put(UserMaster.users.COLUMN_MAIL,newdto.getUserMail());
        values.put(UserMaster.users.COLUMN_SUBJECT,newdto.getUserSubject());
        values.put(UserMaster.users.COLUMN_PASSWORD,newdto.getUserPassword());

        String selection = UserMaster.users._ID + " LIKE ?";
        String []selectionArgs ={newdto.getUserID()};

        int upRow = db.update(UserMaster.users.TABLE_NAME,values,selection,selectionArgs);

        if (upRow>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteUser(String delID){

        SQLiteDatabase db = getWritableDatabase();

        String selection = UserMaster.users._ID + " LIKE ?";
        String []selectionArgs = {delID.trim()};

        int delRow = db.delete(UserMaster.users.TABLE_NAME,selection,selectionArgs);

        if (delRow>0){
            return true;
        }else {
            return false;
        }
    }

}
