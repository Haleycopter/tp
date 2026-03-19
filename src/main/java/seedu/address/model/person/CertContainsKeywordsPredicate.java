package seedu.address.model.person;


import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.cert.Certificate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class CertContainsKeywordsPredicate implements Predicate<Person> {
    private final ArrayList<Certificate> certKeywords;

    public CertContainsKeywordsPredicate(ArrayList<Certificate> certKeywords) {
        this.certKeywords = certKeywords;
    }

    @Override
    public boolean test(Person person) {
        return certKeywords.stream()
                .anyMatch(keyword -> person.getCertificates().stream()
                .anyMatch(personCert -> personCert.containsCertNameIgnoreCase(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CertContainsKeywordsPredicate)) {
            return false;
        }

        CertContainsKeywordsPredicate otherCertContainsKeywordsPredicate = (CertContainsKeywordsPredicate) other;
        return certKeywords.equals(otherCertContainsKeywordsPredicate.certKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", certKeywords).toString();
    }
}
