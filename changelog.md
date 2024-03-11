
# Changes From Design Document
- Removed the `gold` attribute from the `Fight` class.
- Added `getScore` abstract method to the `User` class.
- Removed `checkRanking()` method from the `User` class.

## Game Class Changes
- Rename `registerUser()` method to `register()` method.
- Rename `loginUser()` method to `login()` method.
- Rename`deleteUser()` method to `deleteAccount()` method.
- Added submenus to the `menu()` method.
- Added private methods:
    - `notLoggedMenu()`
    - `loggedPlayerMenu()`
    - `loggedAdminMenu()`
    - `signOff()`
    - `challenge()`
    - `modifyActiveEquipment()`
    - `changeCharacter()`
    - `checkBattleHistory()`
    - `checkRanking()`
- Added some private methods for modularization

## User Class Changes
- Added `challenges` attribute to the `Player` class.
- Added `hasChallenges()` method to the `Player` class.