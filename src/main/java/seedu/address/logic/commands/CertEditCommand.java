package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CERT_EDIT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CERT_EDIT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CERT_EXPIRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CERT_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import main.java.seedu.address.model.cert.CertExpiry;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CertAddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cert.CertName;
import seedu.address.model.cert.Certificate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

/**
 * Adds a certificate to a person in the address book.
 */
public class CertEditCommand extends Command {
    public static final String COMMAND_WORD = "cert-edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a certificate in "
            + "a person's portfolio in the address book. "
            + "Parameters: "
            + "INDEX "
            + PREFIX_CERT_NAME + "NAME "
            + PREFIX_CERT_EDIT_NAME + "NEW_NAME [OPTIONAL] "
            + PREFIX_CERT_EDIT_DATE + "NEW_EXPIRY_DATE [OPTIONAL] "
            + "Example: " + COMMAND_WORD + " "
            + "2 "
            + PREFIX_CERT_NAME + "Social Media Marketing "
            + PREFIX_CERT_EDIT_DATE + "2028-03-05";

    public static final String MESSAGE_SUCCESS = "Certificate edited: %1$s";
    public static final String MESSAGE_MISSING_CERT = "This person does not have this certificate.";

    private final Index index;
    private final Certificate toEdit;
    private final Optional<String> newName;
    private final Optional<String> newDate;
    private final boolean hasNewName;
    private final boolean hasNewDate;

    /**
     * Creates a CertEditCommand to edit the specified {@code Certificate} cert.
     */
    public CertEditCommand(Index index, Certificate cert, String newName, String newDate) {
        this.index = index;
        this.toEdit = cert;
        this.newName = Optional.<String>of(newName);
        this.newDate = Optional.<String>of(newDate);
        this.hasNewName = true;
        this.hasNewDate = true;
    }

    /**
     * Overloaded constructor for CertEditCommand if only the name of the certificate is to be modified.
     * @param index Index of Person to hold the edited certificate.
     * @param cert Certificate to be edited.
     * @param newName New name of the certificate to be edited to.
     */
    public CertEditCommand(Index index, Certificate cert, String newName) {
        this.index = index;
        this.toEdit = cert;
        this.newName = Optional.<String>of(newName);
        this.newDate = Optional.<String>empty();
        this.hasNewName = true;
        this.hasNewDate = false;
    }

    /**
     * Overloaded constructor for CertEditCommand if only the expiry date of
     * the certificate is to be modified.
     * @param index Index of Person to hold the edited certificate.
     * @param cert Certificate to be edited.
     * @param newDate New expiry date of the certificate to be edited to.
     */
    public CertEditCommand(Index index, Certificate cert, String newDate) {
        this.index = index;
        this.toEdit = cert;
        this.newName = Optional.<String>empty();
        this.newDate = Optional.<String>of(newDate);
        this.hasNewName = false;
        this.hasNewDate = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (!personToEdit.hasCert(toEdit)) {
            throw new CommandException(MESSAGE_MISSING_CERT);
        }

        Certificate updatedCert = getUpdatedCert();

        Person personEdited = editCertForPerson(personToEdit, toEdit);

        model.setPerson(personToEdit, personEdited);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personEdited)));
    }

    /**
     * Generates the updated Certificate from the information provided.
     * If new values are not supplied, the old values will be reused.
     * @return Certificate with the edited information.
     */
    private Certificate getUpdatedCert() {
        CertName updatedName;
        CertExpiry updatedExpiry;

        if (hasNewName) {
            updatedName = newName.get();
        } else {
            updatedName = toEdit.getName();
        }

        if (hasNewDate) {
            updatedExpiry = newDate.get();
        } else {
            updatedExpiry = toEdit.getExpiry();
        }

        Certificate updatedCert = new Certificate(updatedName, updatedExpiry);
        return updatedCert;
    }

    private static Person editCertForPerson(Person personToEdit, Certificate toEdit, Certificate updatedCert) {
        assert personToEdit != null;
        // find the index of toEdit in the person's list of certificates
        ArrayList<Certificate> certList = new ArrayList<Certificate>(personToEdit.getCertificates());
        int index = -1;
        for (int i = 0; i < certList.size(); i++) {
            if (toEdit.isSameCert(certList.get(i))) {
                index = i;
                break;
            }
        }

        if (index < 0) {
            throw new CommandException(MESSAGE_MISSING_CERT);
        }
        // set the new cert
        certList.set(index, updatedCert);

        Name name = personToAddTo.getName();
        Phone phone = personToAddTo.getPhone();
        Email email = personToAddTo.getEmail();
        Address address = personToAddTo.getAddress();
        Set<Tag> tags = personToAddTo.getTags();
        Salary salary = personToAddTo.getSalary();
        return new Person(name, phone, email, address, tags, salary, certList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CertAddCommand)) {
            return false;
        }

        CertAddCommand otherCertAddCommand = (CertAddCommand) other;
        return toAdd.equals(otherCertAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
