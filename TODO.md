# Developing Notes and TODOs

## Developing Notes

> 📄 Document all the methods an classes you make

## TODOs

- [ ] Implement TESTs for the following classes:
  - [X] Abilities
    - [X] SpecialAbility (Abstract class)
    - [X] Discipline    // Diego
    - [X] Don           // Daniel
    - [X] Talent        // Diego
  - [X] Challenges
    - [X] Challenge     // Diego
    - [X] Fight         //Luis
  - [ ] Characters
    - [X] Character (Abstract class)
    - [X] CharacterSelection (Enum) // Diego
    - [X] Hunter        // Diego
    - [X] Lycanthrope   // Diego
    - [X] Vampire       // Diego
  - [X] Equipments
    - [X] Equipment (Abstract class)
    - [X] Armor         // Daniel
    - [X] Weapon        // Luis
  - [X] Minions
    - [x] Minion (Abstract class)
    - [X] LoyaltyEnum (Enum)
    - [X] Devil         // Daniel
    - [X] Ghoul         // Daniel
    - [X] Human         // Daniel
  - [ ] Users
    - [X] User (Abstract class)
    - [X] Player
    - [ ] Admin
  - [X] Modifiers
    - [X] Modifier (Abstract class)  // Tested LoadFromArray
    - [X] Strength      // Daniel
    - [X] Weakness      // Daniel
  - [ ] Game
    - [ ] Game

- [X] Search a way to offer a default weapons set.
- [X] Search a way to offer a default armor set.
- [X] Make a player to select a character selection when registering.
- [X] Implement battle system.
  - [X] Challenge part 1 (created, unverified)
  - [X] Challenge part 2 (verified, waiting for acceptance)
  - [X] Challenge part 3 (accepted or not, finished)
  - [X] Implement the notification system.
  - [X] Check if the user has active challenges on login.
  - [X] Implement Minions functionality.
  - [X] Implement Special Abilities functionality.
  - [X] Modifiers of the characters must be managed by the admin.
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
  - [X] Manage challenges.
    - [X] Implement modify characters.
  - [X] Check ranking.
  - [X] Manage account settings.
  - [X] Sign off.
- [X] Attack and deffense values of the equipment must be between 1-3
- [X] Introduce minions array in the const DEVILS
- [X] Replace the {@Code Some code} comments to <code>Some code</code>
- [X] Explain the attributes of the classes in class JavaDoc comment.
  
## Refactor for review

> 📆 Review the following TODOs in the code `// TODO` and decide what to do

## Syntax and format

- Don't write onliner statements without curly brackets.
- Comment all the methods and classes.