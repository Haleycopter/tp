---
  layout: default.md
    title: "Haley's Project Portfolio Page"
---

### Project: Big Brother

Big Brother is a desktop address book application used for managing employee details by HR Staff of a startup. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability `undo` the address book to the immediate previous state once, provided the previous state was the result of a data-modifying command.
  * What it does: allows the user to restore the address book to the immediate previous state.
  * Justification: This feature improves user to recover from scary mistakes like running `clear` when they intended to run `delete` instead.
  * Highlights: This required the address book to store the previous state for data-modifying commands (`delete`, `clear`, `sort`, `add`, `edit`, `cert-add`, `cert-edit`, `cert-del`, `tag`).
  * Credits: the `undo` command has some references to the proposed `undo` command in default AB3.*

* **Code contributed**: [RepoSense link](https://github.com/AY2526S2-CS2103T-T09-1/tp/blob/master/src/main/java/seedu/address/logic/commands/UndoCommand.java)

* **Project management**:
  * Managed releases `v1.4` on GitHub

* **Enhancements to existing features**:
  * Updated the `add` and `edit` command with more parameters like `SALARY` (Pull requests [#54](https://github.com/AY2526S2-CS2103T-T09-1/tp/pull/54))
  * Updated input validation rules for parameters used in `add` and `edit` commands (Pull requests [#56](https://github.com/AY2526S2-CS2103T-T09-1/tp/pull/56))

* **Documentation**:
  * User Guide:
    * edit
    * add
    * undo
  * Developer Guide:
    * Undo sequence diagram
    * Planned Enhancements
    * Instructions for Manual Testing
    * User stories

* **Community**:
  * PRs reviewed (with non-trivial review comments): [18](https://nus-cs2103-ay2526-s2.github.io/dashboards/contents/tp-comments.html)
  * Contributed to forum discussions (examples: [194](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/194), [133](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/133#issuecomment-3844649113), [174](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/174#issuecomment-3866187671), [227](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/227#issuecomment-3912631704), [262](https://github.com/NUS-CS2103-AY2526-S2/forum/issues/262#issuecomment-3921069745))
