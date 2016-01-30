package a00925723.comp3717.bcit.ca.banking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Transfers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void transfer(View v) {
        Spinner to = (Spinner) findViewById(R.id.to);
        Spinner from = (Spinner) findViewById(R.id.from);
        EditText amount = (EditText) findViewById(R.id.amount);

        if (amount.getText().toString().length() == 0) {
            Toast t = Toast.makeText(this, "Invalid Amount Specified", Toast.LENGTH_SHORT);

            t.show();
        } else if (to.getSelectedItem() == from.getSelectedItem()) {
            Toast t = Toast.makeText(this, "Invalid Account Selection", Toast.LENGTH_SHORT);

            t.show();
        } else {
            Toast t = Toast.makeText(this, "Transfer Complete", Toast.LENGTH_SHORT);

            t.show();
            finish();
        }
    }

}
