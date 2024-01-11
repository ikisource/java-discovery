package fr.ikisource.oma.java19.recordpattern;

import org.junit.jupiter.api.Test;

public class TestRecordPatterns {

    Object object = new Location("Home", new GPSPoint(1.0, 2.0));

    @Test
    void givenObject_whenTestInstanceOfAndCastIdiom_shouldMatchNewInstanceOf() {
        // old Code
        if (object instanceof Location) {
            Location l = (Location) object;
            assertThat(l).isInstanceOf(Location.class);
        }
        // new code
        if (object instanceof Location l) {
            assertThat(l).isInstanceOf(Location.class);
        }
    }
}
