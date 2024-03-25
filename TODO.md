# Developing Notes and TODOs

## Developing Notes

> 📄 Document all the methods an classes you make

## TODOs

- [X] Search a way to offer a default weapons set.
- [X] Search a way to offer a default armor set.
- [X] Make a player to select a character selection when registering.
- [ ] Implement battle system.
  - [X] Challenge part 1 (created, unverified)
  - [X] Challenge part 2 (verified, waiting for acceptance)
  - [X] Challenge part 3 (accepted or not, finished)
  - [X] Implement the notification system.
  - [X] Check if the user has active challenges on login.
  - [X] Implement Minions functionality.
  - [ ] Implement Special Abilities functionality.
- [X] Check the way the score is calculated.
- [X] Implement a way to manage account settings.
  - [X] Change password.
  - [X] Change name.
  - [X] Change nickname.
- [x] Implement Player menu.
  - [X] Challenge a player.
  - [X] Modify active equipment.
  - [X] Change character.
  - [X] Check battle history.
  - [X] Check ranking.
  - [X] Manage account settings.
  - [X] Sign off.
- [x] Implement Admin menu.
  - [X] Manage players.
  - [X] Manage equipment.
  - [ ] Manage challenges.
    - [ ] Implement modify characters.
  - [X] Check ranking.
  - [X] Manage account settings.
  - [X] Sign off.
- [ ] attack and deffense values of the equipment must be between 1-3
- [X] intoduce minions array in the const DEVILS
  
## Refactor for review

> 📆 Review the following TODOs in the code `// TODO` and decide what to do

- [ ] In `Game` class, implement `menu` method using the **State** pattern.
- [ ] In the `Game` class, the `manageChallenge` and similar methods such as `accept` or `approve` should be moved to the `Admin` class, or managed using the **State** pattern.
- [ ] Use the **Prototype** pattern to create a default set of weapons, armor, characters and minions.

## Syntax and format

- Don't write onliner statements without curly brackets.
- Comment all the methods and classes.