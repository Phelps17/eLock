package com.elock.tylerphelps.elock;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.models.consumer.*;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.PubNub;
import android.view.View;
import android.widget.Button;
import android.os.Handler.Callback;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.pubnub.api.PubNubError;

public class LockInteractionActivity extends AppCompatActivity {

    private Lock lock;
    private PubNub pubnub;
    private DatabaseController dc;
    private final String USER = "Android_Device";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_interaction);

        //TODO get lock from intent here
        this.dc = new DatabaseController(getApplicationContext());
        int position = getIntent().getIntExtra("eLockDbPosition", 0);
        this.lock = dc.getLocks().get(position);

        try {
            PNConfiguration pnConfiguration = new PNConfiguration();
            pnConfiguration.setSubscribeKey(this.lock.getSubscribeKey());
            pnConfiguration.setPublishKey(this.lock.getPublishKey());

            this.pubnub = new PubNub(pnConfiguration);

            EditText nicknameBox = (EditText) findViewById(R.id.editText_name);
            nicknameBox.setText(this.lock.getNickname());
            TextView identifier = (TextView) findViewById(R.id.textView_indentifier);
            identifier.setText("Identifier: " + this.lock.getIdentifier());
        }
        catch (Exception e) {
            Toast.makeText(this.getApplicationContext(),"Error Loading eLock Data",
                    Toast.LENGTH_SHORT).show();
            finish();
        }


        Button unlock = (Button) findViewById(R.id.btn_unlock);
        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTestMessage();
            }
        });

        Button save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Nickname Saved",
                        Toast.LENGTH_SHORT).show();
                //TODO name change
            }
        });

        Button delete = (Button) findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Deleting eLock",
                        Toast.LENGTH_SHORT).show();
                //TODO delete the lock
            }
        });
    }

    public void sendTestMessage() {
        //get password connected
        String password = ((EditText) findViewById(R.id.editText_password)).getText().toString();
        String message = USER + " " + this.lock.getIdentifier() + " " + password;

        this.pubnub.publish()
                .message(message)
                .channel(this.lock.getChannel())
                .shouldStore(true)
                .usePOST(true)
                .async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        if (status.isError()) {
                            // something bad happened.
                            Toast.makeText(getBaseContext(),"Connection Error",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getBaseContext(),"Accessing eLock...",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        this.pubnub.disconnect();
        finish();
    }
}
