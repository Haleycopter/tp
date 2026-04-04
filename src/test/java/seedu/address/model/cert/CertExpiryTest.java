package seedu.address.model.cert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class CertExpiryTest {

    @Test
    public void constructor_null_success() {
        CertExpiry forever = new CertExpiry(null);
        assertFalse(forever.hasExpiry());
    }

    @Test
    public void isBefore_comparisonWithNull() {
        CertExpiry specificDate = new CertExpiry(LocalDate.parse("2026-01-01"));
        CertExpiry forever = new CertExpiry(null);

        // A specific date is before forever
        assertTrue(specificDate.isBefore(forever));

        // forever is not before a specific date
        assertFalse(forever.isBefore(specificDate));

        // forever is not before forever
        assertFalse(forever.isBefore(new CertExpiry(null)));
    }

    @Test
    public void getDisplayDateString_withDate_returnsFormattedDate() {
        CertExpiry expiry = new CertExpiry(LocalDate.parse("2029-01-01"));
        assertEquals("2029-01-01", expiry.getDisplayDateString());
    }

    @Test
    public void getDisplayDateString_nullDate_returnsNoExpiry() {
        CertExpiry noExpiry = new CertExpiry(null);
        assertEquals("No Expiry", noExpiry.getDisplayDateString());
    }

    @Test
    public void toString_nullDate_returnsNoExpiry() {
        CertExpiry noExpiry = new CertExpiry(null);
        assertEquals("No Expiry", noExpiry.toString());
    }

    @Test
    public void isBefore_variousCases() {
        CertExpiry date1 = new CertExpiry(LocalDate.parse("2026-01-01"));
        CertExpiry date2 = new CertExpiry(LocalDate.parse("2027-01-01"));
        CertExpiry noExpiry = new CertExpiry(null);

        // no expiry date will never come before another date
        assertFalse(noExpiry.isBefore(date1));

        // a specified date will come before no expiry date
        assertTrue(date1.isBefore(noExpiry));

        assertTrue(date1.isBefore(date2));
    }

    @Test
    public void equals() {
        CertExpiry expiry = new CertExpiry(LocalDate.parse("2026-03-17"));
        assertTrue(expiry.equals(new CertExpiry(LocalDate.parse("2026-03-17"))));

        assertTrue(expiry.equals(expiry));

        assertFalse(expiry.equals(5));

        assertFalse(expiry.equals("2026-03-08"));
    }
}
