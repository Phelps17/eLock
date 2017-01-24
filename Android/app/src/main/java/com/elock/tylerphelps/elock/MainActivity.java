package com.elock.tylerphelps.elock;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.elock.tylerphelps.elock.barcode.BarcodeCaptureActivity;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final int BARCODE_READER_REQUEST_CODE = 1;
    private DatabaseController dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.dc = new DatabaseController(getApplicationContext());
        populateListView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        ListView listView = (ListView) findViewById(R.id.scrolling_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("LOCK SELECTED", ""+ position + "ID: " + id);

                    Intent intent = new Intent(getApplicationContext(), LockInteractionActivity.class);
                    intent.putExtra("eLockDbPosition",position);
                    startActivity(intent);
                }
            }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //TODO handle barcode response codes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Point[] p = barcode.cornerPoints;

                    String barcodeData = barcode.displayValue;
                    Log.d("QR CODE SCANNER", barcodeData);

                    try {
                        JSONObject json = new JSONObject(barcodeData);
                        long newId = 0;
                        try {
                            Lock newLock = new Lock(newId, json.getString("door_name"),
                                    json.getString("channel"), json.getString("pub_key"),
                                    json.getString("sub_key"), "New eLock");

                            try {
                                newLock.setId(this.dc.getNextLockId());
                                this.dc.addLock(newLock);
                                Toast.makeText(getBaseContext(),"New eLock Added!",
                                        Toast.LENGTH_SHORT).show();

                                try {
                                    populateListView();
                                }
                                catch (Exception e) {
                                    Toast.makeText(getBaseContext(),"Error Reloading ListView",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e) {
                                //database errors
                                Toast.makeText(getBaseContext(),"Error: Could Not Add New Lock",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e) {
                            //invalid format
                            Toast.makeText(getBaseContext(),"Error: Could Not eLock Data",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e) {
                        //couldnt parse json data
                        Toast.makeText(getBaseContext(),"Error: Invalid QR Code Data",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Log.e("QR CODE SCANNER", "Nothing Captured");
                }
            }
            else {
                Log.e("QR CODE SCANNER", "ERROR");
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void populateListView() {
        this.dc = new DatabaseController(getApplicationContext());
        List<Lock> savedLocks = dc.getLocks();

        ListView listView = (ListView) findViewById(R.id.scrolling_list);

        // Create and populate a List of planet names.
        ArrayList<String> lockList = new ArrayList<String>();
        for (Lock lock : savedLocks) {
            lockList.add(lock.getNickname());
            System.out.println(lock.getId() + " " + lock.getIdentifier());
        }

        // Create ArrayAdapter using the planet list.
        ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.list_row, lockList);

        // Set the ArrayAdapter as the ListView's adapter.
        listView.setAdapter( listAdapter );
    }
}
