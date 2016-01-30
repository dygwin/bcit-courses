package a00925723.comp3717.bcit.ca.banking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RelativeLayout savings_balance = (RelativeLayout) findViewById(R.id.savings_layout);
        RelativeLayout chequings_balance = (RelativeLayout) findViewById(R.id.chequing_layout);

        // Set the widths of the balance layouts to the width of the screen
        savings_balance.getLayoutParams().width = getWindowManager().getDefaultDisplay().getWidth();
        chequings_balance.getLayoutParams().width = getWindowManager().getDefaultDisplay().getWidth();
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

    public void accounts(View v) {
        Intent i = new Intent(this, Accounts.class);

        startActivity(i);
    }

    public void payBills(View v) {
        Intent i = new Intent(this, PayBills.class);

        startActivity(i);
    }

    public void transfers(View v) {
        Intent i = new Intent(this, Transfers.class);

        startActivity(i);
    }

    public void deposit(View v) {
        Toast t = Toast.makeText(this, "Opens Camera App", Toast.LENGTH_SHORT);

        t.show();
    }

    public void findUs(View v) {
        Toast t = Toast.makeText(this, "Opens Google Maps App", Toast.LENGTH_SHORT);

        t.show();
    }

    public void contactUs(View v) {
        Intent i = new Intent(this, ContactUs.class);

        startActivity(i);
    }
}
