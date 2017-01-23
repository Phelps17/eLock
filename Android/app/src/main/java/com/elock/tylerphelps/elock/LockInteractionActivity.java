package com.elock.tylerphelps.elock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.models.consumer.*;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.PubNub;

public class LockInteractionActivity extends AppCompatActivity {

    private Lock thisLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_interaction);
    }

    /*
    PNConfiguration pnConfiguration = new PNConfiguration();
    pnConfiguration.setSubscribeKey("pub-c-a8732a53-6069-4292-8981-a1a9a230172f");
    pnConfiguration.setPublishKey("sub-c-a27f6252-e02e-11e6-989b-02ee2ddab7fe");

    pubnub = new PubNub(pnConfiguration);

         pubnub.publish()
                        .message("Unlock from Android")
                        .channel("eLockServer")
                        .shouldStore(true)
                        .usePOST(true)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                if (status.isError()) {
                                    // something bad happened.
                                    System.out.println("error happened while publishing: " + status.toString());
                                } else {
                                    System.out.println("publish worked! timetoken: " + result.getTimetoken());
                                }
                            }
                        });
     */
}
