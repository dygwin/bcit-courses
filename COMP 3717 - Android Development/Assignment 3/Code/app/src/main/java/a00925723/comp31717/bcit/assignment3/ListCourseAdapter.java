package a00925723.comp31717.bcit.assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListCourseAdapter extends BaseAdapter {

    Context context;

    protected List<Course> listCourses;
    LayoutInflater inflater;

    public ListCourseAdapter(Context context, List<Course> listCourses) {
        this.listCourses = listCourses;
        this.inflater    = LayoutInflater.from(context);
        this.context     = context;
    }

    public int getCount() {
        return listCourses.size();
    }

    public Course getItem(int position) {
        return listCourses.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.layout_list_item,
                    parent, false);

            holder.txtName = (TextView) convertView
                    .findViewById(R.id.txt_name);
            holder.txtDesc = (TextView) convertView
                    .findViewById(R.id.txt_desc);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Course person = listCourses.get(position);
        holder.txtName.setText(person.getId());
        holder.txtDesc.setText(""+person.getDescription());

        return convertView;
    }

    private class ViewHolder {
        TextView txtName;
        TextView txtDesc;
    }

}
