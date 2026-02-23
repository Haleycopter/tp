---
  layout: default.md
  title: "Haley's Project Portfolio Page"
---

### Project: Big Brother

Big Brother is a desktop address book application used by startup bosses in maintaining employee details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit existing employee details.
    * What it does: allows the user to edit specific employee details(e.g. EMAIL).
    * Justification: This feature improves user experience significantly because a user can efficiently update new details of an employee without having to delete an entire entry and then add a new entry with the desired updated details.
    * Highlights: This enhancement required some tweaks to the existing `edit` command and does not affect other existing or future commands. However, it required analysis of editing the TAG parameter, as the default AB3 required user to retype existing tags if they wanted to add new tags.
    * Credits: *the updated `edit` command reuses some code from the default AB3*

* **Code contributed**: [AY2526S2-CS2103T-T09-1/tp](https://github.com/AY2526S2-CS2103T-T09-1/tp/blob/master/src/main/java/seedu/address/logic/commands/EditCommand.java)

* **Project management**:
    * Managed releases `v1.1` on GitHub

* **Enhancements to existing features**:
    * Updated the `edit` command to edit more parameters like `SALARY` and `CERTIFICATE` (Pull requests [TBC]())
    * Wrote additional tests for existing features to increase coverage from TBC to TBC (Pull requests [TBC]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `edit` and `add` [TBC]()
    * Developer Guide:
        * Added implementation details of the `edit` and `add` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [TBC]()
    * Contributed to forum discussions (examples: [194](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/194), [133](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/133#issuecomment-3844649113), [174](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/174#issuecomment-3866187671), [227](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/227#issuecomment-3912631704), [262](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/262#issuecomment-3921069745))
    * Reported bugs and suggestions for other teams in the class (examples: [TBC]())

* **Tools**:
    * TBC
