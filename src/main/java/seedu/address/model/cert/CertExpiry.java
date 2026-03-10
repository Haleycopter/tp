package seedu.address.model.cert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class CertExpiry {
    private LocalDate expiryDate;
    
    public static final String MESSAGE_CONSTRAINTS =
            "Certificate expiry dates should be in the format of yyyy-mm-dd.";

    public CertExpiry(LocalDate expiry) {
        expiryDate = expiry;
    }

    /**
     * Returns true if a given string is a valid certificate name.
     */
    public static boolean isValidCertExpiry(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
