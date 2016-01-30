package a00925723.comp31717.bcit.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper
        extends SQLiteOpenHelper
{
    public static final String DB_NAME = "courselist";
    public static final int DB_VERSION = 1;
    public static final String COURSE_TABLE = "STUDENT_COURSES";
    public static final String STUDENT_ID = "STUDENT_ID";
    public static final String COURSE_ID = "COURSE_ID";
    public static final String COURSE_DESCRIPTION = "COURSE_DESCRIPTION";

    public DBHelper(final Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + COURSE_TABLE + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENT_ID + " TEXT, " +
                COURSE_ID + " TEXT, " +
                COURSE_DESCRIPTION + " TEXT)");
    }

    public void insertCourse(final SQLiteDatabase database,
                             final String studentId,
                             final String courseId,
                             final String courseDescription) {
        final ContentValues courseValues;

        courseValues = new ContentValues();
        courseValues.put(STUDENT_ID,studentId);
        courseValues.put(COURSE_ID,courseId);
        courseValues.put(COURSE_DESCRIPTION, courseDescription);
        database.insert(COURSE_TABLE, null, courseValues);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase database,
                          final int            oldVersion,
                          final int            newVersion) {
    }
}