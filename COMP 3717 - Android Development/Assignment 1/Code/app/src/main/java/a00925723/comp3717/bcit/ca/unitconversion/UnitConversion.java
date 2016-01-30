package a00925723.comp3717.bcit.ca.unitconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UnitConversion extends AppCompatActivity {
    TextView result;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);

        result = (TextView) findViewById(R.id.result);
        input = (EditText) findViewById(R.id.input);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unit_conversion, menu);
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

    public void convert(View v) {
        Double value;
        String tempValue;

        // Default value set to 1.0 if there is no input in the edit-text
        if ((tempValue = input.getText().toString()).length() > 0) {
            value = Double.parseDouble(tempValue);
        } else {
            value = 1.0;
        }

        switch(v.getId()) {
            case R.id.mile_to_kilometer:
                result.setText((value * 1.60934) + " Kilometers");
                break;
            case R.id.kilometer_to_mile:
                result.setText((value * 0.621371) + " Miles");
                break;
        }
    }
}
