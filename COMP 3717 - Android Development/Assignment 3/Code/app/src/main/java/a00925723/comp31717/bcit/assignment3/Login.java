package a00925723.comp31717.bcit.assignment3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentId = (EditText) findViewById(R.id.student_id);

    }

    public void login(View v) {
        String id = studentId.getText().toString().toUpperCase();

        // First try to pull the data from the SQLite database
        try {
            final SQLiteOpenHelper helper;
            final SQLiteDatabase database;
            final Cursor cursor;

            helper = new DBHelper(this);
            database = helper.getReadableDatabase();
            cursor = database.query(DBHelper.COURSE_TABLE,
                    new String[] { DBHelper.STUDENT_ID,
                            DBHelper.COURSE_ID,
                            DBHelper.COURSE_DESCRIPTION
                    },
                    DBHelper.STUDENT_ID + " like ?",
                    new String[] { id
                    },
                    null,
                    null,
                    null);

            // If the student record exists in the SQLite database
            if(cursor.moveToFirst()) {
                Toast.makeText(this, "Pulled from SQLite", Toast.LENGTH_SHORT).show();

                Intent classList                     = new Intent(this, CourseList.class);
                ArrayList<String> courseIds          = new ArrayList<>();
                ArrayList<String> courseDescriptions = new ArrayList<>();

                courseIds.add(cursor.getString(1));
                courseDescriptions.add(cursor.getString(2));

                while (cursor.moveToNext()) {
                    courseIds.add(cursor.getString(1));
                    courseDescriptions.add(cursor.getString(2));
                }

                classList.putExtra("courseIds", courseIds);
                classList.putExtra("courseDescriptions", courseDescriptions);

                startActivity(classList);

                Toast.makeText(this, "Logged in as " + id, Toast.LENGTH_SHORT).show();
            } else {
                // If the student doesn't exist in SQLite database, try to pull it from MongoDB
                new MongoAccess().execute(this, studentId.getText().toString().toUpperCase());
            }

            cursor.close();
            database.close();
        } catch(final SQLiteException ex) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG).show();
            Log.e("CourseActivity", "Database error", ex);
        }
    }
}
