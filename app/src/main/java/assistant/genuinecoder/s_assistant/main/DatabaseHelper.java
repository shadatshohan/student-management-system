package assistant.genuinecoder.s_assistant.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "log.db";
    private static final String TABLE_NAME = "userdetails";
    private static final int version = 3;
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String USERNAME = "Username";
    private static final String CONTACT = "Contact";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+CONTACT+" INTEGER, " + NAME + " VARCHAR(255) NOT NULL," + EMAIL + " TEXT NOT NULL," + USERNAME + " VARCHAR(255) NOT NULL," + PASSWORD + " TEXT NOT NULL);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "Oncreate is called", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "error" + e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context, "OnUpgrade is called", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Toast.makeText(context, "error on upgrade" + e, Toast.LENGTH_SHORT).show();
        }

    }

    public long insert(UserDetails Userdetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, Userdetails.getName());
        contentValues.put(USERNAME, Userdetails.getUsername());
        contentValues.put(PASSWORD, Userdetails.getPassword());
        contentValues.put(EMAIL, Userdetails.getEmail());

        long value = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return value;
    }

    public Boolean findmatching(String user, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean result = false;
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "no data is found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String username = cursor.getString(3);
                String password = cursor.getString(4);

                if (username.equals(user) && password.equals(pass)) {
                    result = true;
                    break;
                }

            }
        }
        return result;
    }
}

