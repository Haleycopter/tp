package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s set of {@code Tags} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final Set<Tag> tagsToFind;

    public TagContainsKeywordsPredicate(Set<Tag> tagsToFind) {
        this.tagsToFind = tagsToFind;
    }

    @Override
    public boolean test(Person person) {
        return tagsToFind.stream()
                .allMatch(keywordTag -> hasMatchingTag(person, keywordTag));
    }

    private boolean hasMatchingTag(Person person, Tag keywordTag) {
        return person.getTags().stream()
            .anyMatch(personTag -> personTag.containsTagNameIgnoreCase(keywordTag));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordsPredicate)) {
            return false;
        }

        TagContainsKeywordsPredicate otherTagContainsKeywordsPredicate = (TagContainsKeywordsPredicate) other;
        return tagsToFind.equals(otherTagContainsKeywordsPredicate.tagsToFind);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", tagsToFind).toString();
    }
}
