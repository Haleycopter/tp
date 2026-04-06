package seedu.address.model.person;


import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.cert.CertExpiry;

/**
 * Tests that a {@code Person}'s list of {@code Certificate}s matches the date given.
 */
public class CertContainsDatePredicate implements Predicate<Person> {
    private final CertExpiry expiry;

    public CertContainsDatePredicate(CertExpiry expiry) {
        this.expiry = expiry;
    }

    @Override
    public boolean test(Person person) {
        return person.getCertificates().stream()
                .anyMatch(personCert -> {
                    //if cert has no expiry, it's forever valid and should not show up in find e/
                    if (!personCert.getExpiry().hasExpiry()) {
                        return false;
                    }
                    return personCert.isExpiredBefore(expiry);
                });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CertContainsDatePredicate)) {
            return false;
        }

        CertContainsDatePredicate otherCertContainsDatePredicate = (CertContainsDatePredicate) other;
        return expiry.equals(otherCertContainsDatePredicate.expiry);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("expiry date", expiry).toString();
    }
}
