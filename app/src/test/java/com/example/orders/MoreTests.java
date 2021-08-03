package com.example.orders;

import android.content.Context;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


import com.google.firebase.FirebaseApp;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MoreTests extends TestCase {
    private ActivityController<NewOrder> activityController;

    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(NewOrder.class);
    }

    @Test
    public void when_changes_text_is_visible() {
        // setup

        NewOrder activityUnderTest = activityController.create().visible().get();
        TextView name = activityUnderTest.findViewById(R.id.name);
        name.setText("shmulik");

        // verify
        assertEquals("shmulik", name.getText().toString());

    }

    @Test
    public void when_hummus_is_clicked_3_times_it_is_chosen() {
        // setup
        NewOrder activityUnderTest = activityController.create().visible().get();
        CheckBox hummusButton = activityUnderTest.findViewById(R.id.humus);
        hummusButton.performClick();
        hummusButton.performClick();
        hummusButton.performClick();


        // verify
        assertTrue(hummusButton.isChecked());
    }

    @Test
    public void when_tahini_is_clicked_3_times_it_is_chosen() {
        // setup
        NewOrder activityUnderTest = activityController.create().visible().get();
        CheckBox tahiniButton = activityUnderTest.findViewById(R.id.humus);
        tahiniButton.performClick();
        tahiniButton.performClick();
        tahiniButton.performClick();


        // verify
        assertTrue(tahiniButton.isChecked());
    }

}
