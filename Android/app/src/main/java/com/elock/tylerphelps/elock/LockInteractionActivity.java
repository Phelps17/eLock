package com.elock.tylerphelps.elock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LockInteractionActivity extends AppCompatActivity {

    private Lock thisLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_interaction);
    }
}
