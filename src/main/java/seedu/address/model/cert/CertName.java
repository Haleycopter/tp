package seedu.address.model.cert;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class CertName {

    /**
     * Represents a Certificate's name in the address book.
     * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
     */

    public static final String MESSAGE_CONSTRAINTS =
            "Certificate names should only contain alphanumeric characters and spaces, "
            + "and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String certificateName;

    /**
     * Constructs a {@code CertName}.
     *
     * @param name A valid certificate name.
     */
    public CertName(String name) {
        requireNonNull(name);
        checkArgument(isValidCertName(name), MESSAGE_CONSTRAINTS);
        certificateName = name;
    }

    /**
     * Returns true if a given string is a valid certificate name.
     */
    public static boolean isValidCertName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return certificateName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CertName)) {
            return false;
        }

        CertName otherName = (CertName) other;
        return certificateName.equals(otherName.certificateName);
    }

    @Override
    public int hashCode() {
        return certificateName.hashCode();
    }
}
