package a00925723.comp31717.bcit.assignment3;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class MongoAccess extends AsyncTask<Object, Void, Student> {
    Login activity;
    String   studentId;

    @Override
    protected Student doInBackground(Object... objects) {
        OkHttpClient client = new OkHttpClient();
        activity            = (Login) objects[0];
        studentId           = (String)   objects[1];

        String url          = "https://api.mongolab.com/api/1/databases/a00925723/collections/" +
                              "assignment3?q={\"id\":\"" + studentId + "\"}" +
                              "&fo=true&apiKey=###"; // Redacted API Key, needs to be put back in.

        // Make the url safe for okhttp
        url = url.replace("\'", "%91")
                 .replace("{", "%7B")
                 .replace("}", "%7D");

        try {
            Request request = new Request.Builder()
                                         .url(HttpUrl.parse(url))
                                         .build();

            Response response = client.newCall(request).execute();
            String   json     = response.body().string();

            // Return null if there is no JSON data returned from the HTTP request
            if (json.equals("null")){
                return null;
            }

            Student student = new Gson().fromJson(json, Student.class);

            return student;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Student student) {
        if (student == null) {
            Toast t = Toast.makeText(activity, "Invalid ID", Toast.LENGTH_SHORT);

            t.show();
        } else {
            Intent classList                     = new Intent(activity, CourseList.class);
            ArrayList<String> courseIds          = new ArrayList<>();
            ArrayList<String> courseDescriptions = new ArrayList<>();
            DBHelper          helper             = new DBHelper(activity);

            for (Course c : student.getCourses()) {
                // Insert records into the SQLite database
                helper.insertCourse(helper.getWritableDatabase(),
                                    studentId,
                                    c.getId(),
                                    c.getDescription());

                // Add the courses to the lists for the intent
                courseIds.add(c.getId());
                courseDescriptions.add(c.getDescription());
            }

            classList.putExtra("courseIds", courseIds);
            classList.putExtra("courseDescriptions", courseDescriptions);

            activity.startActivity(classList);

            Toast.makeText(activity, "Pulled from Mongolab", Toast.LENGTH_SHORT).show();
            Toast.makeText(activity, "Logged in as " + student.getId(), Toast.LENGTH_SHORT).show();
        }
    }
}
