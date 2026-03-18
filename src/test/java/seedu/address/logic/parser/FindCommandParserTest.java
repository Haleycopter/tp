package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
            new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTags_returnsFindCommand() {
        // Single tag
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Set.of(new Tag("friends"))));
        assertParseSuccess(parser, " t/friends", expectedFindCommand);

        // Multiple tags - testing that it captures all keywords under the prefix
        // expectedFindCommand = new FindCommand(new TagContainsKeywordsPredicate(
        //         Set.of(new Tag("friends"), new Tag("owesMoney"))));

        // Test case for multiple tags provided separately
        // assertParseSuccess(parser, " t/friends t/owesMoney", expectedFindCommand);
    }

    @Test
    public void parse_tagsWithWhitespaces_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Set.of(new Tag("friends"))));
        // Leading and trailing whitespaces within the tag value
        assertParseSuccess(parser, " t/  friends  ", expectedFindCommand);
    }

    @Test
    public void parse_invalidTagFormat_throwsParseException() {
        // Empty tag value
        assertParseFailure(parser, " t/ ", Tag.MESSAGE_CONSTRAINTS);

        // Tag with invalid characters
        assertParseFailure(parser, " t/friend\\find ", Tag.MESSAGE_CONSTRAINTS);
    }

}
