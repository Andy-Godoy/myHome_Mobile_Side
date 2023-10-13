package com.example.myhome;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
        public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        android.content.Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myhome", appContext.getPackageName());
    }
}