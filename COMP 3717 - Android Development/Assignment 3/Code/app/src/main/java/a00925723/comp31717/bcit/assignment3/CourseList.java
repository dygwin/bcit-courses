package a00925723.comp31717.bcit.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView             lv;
        ListCourseAdapter adapter;
        Intent               i                  = getIntent();
        ArrayList<String>    courseIds          = i.getStringArrayListExtra("courseIds");
        ArrayList<String>    courseDescriptions = i.getStringArrayListExtra("courseDescriptions");

        ArrayList<Course> courses = new ArrayList<Course>();

        for (int j = 0; j < courseIds.size(); j++) {
            Course c = new Course();

            c.setId(courseIds.get(j));
            c.setDescription(courseDescriptions.get(j));

            courses.add(c);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        //adapter = new ArrayAdapter<> (this,
        //                              android.R.layout.simple_list_item_1,
        //                              courseIds);

        adapter = new ListCourseAdapter(this, courses);

        lv = (ListView) findViewById(R.id.course_list);
        lv.setAdapter(adapter);
    }
}
