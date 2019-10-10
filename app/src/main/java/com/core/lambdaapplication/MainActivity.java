package com.core.lambdaapplication;

import android.content.ComponentCallbacks2;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.SimpleArrayMap;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ComponentCallbacks2 {

    private SimpleArrayMap A, B, C;
    private int capacityA, capacityB, capacityC;

    private void fillArray(SimpleArrayMap toFill, int capacity) {
        for (int i=0; i<capacity; i++) toFill.put(i, (int) Math.random() * 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        capacityA = 1000000;
        capacityB = 1000000;
        capacityC = 1000000;
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        A = new SimpleArrayMap(capacityA);
        fillArray(A, capacityA);
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        B = new SimpleArrayMap(capacityB);
        fillArray(B, capacityB);
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        C = new SimpleArrayMap(capacityC);
        fillArray(C, capacityC);
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
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

    /**
     * Release memory when the UI becomes hidden or when system resources become low.
     * @param level the memory-related event that was raised.
     */
    public void onTrimMemory(int level) {
        System.out.println("TOT : Test OnTrim level : " + level);
        // Determine which lifecycle or system event was raised.
        switch (level) {

            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:
                System.out.println("TOT : UI hidden");
                /*
                   Release any UI objects that currently hold memory.

                   The user interface has moved to the background.
                */
                System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
                A = null;
                B = null;
                C = null;
                break;

            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
                System.out.println("TOT : Running Moderate");
                break;
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
                System.out.println("TOT : Running Low");
                break;
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:
                System.out.println("TOT : Running Critical");
                /*
                   Release any memory that your app doesn't need to run.

                   The device is running low on memory while the app is running.
                   The event raised indicates the severity of the memory-related event.
                   If the event is TRIM_MEMORY_RUNNING_CRITICAL, then the system will
                   begin killing background processes.
                */

                break;

            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
                System.out.println("TOT : Background");
                break;
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
                System.out.println("TOT : Moderate");
                break;
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:
                System.out.println("TOT : Complete");
                /*
                   Release as much memory as the process can.

                   The app is on the LRU list and the system is running low on memory.
                   The event raised indicates where the app sits within the LRU list.
                   If the event is TRIM_MEMORY_COMPLETE, the process will be one of
                   the first to be terminated.
                */

                break;

            default:
                System.out.println("TOT : Unknown");
                /*
                  Release any non-critical data structures.

                  The app received an unrecognized memory level value
                  from the system. Treat this as a generic low-memory message.
                */
                break;
        }
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }
}
