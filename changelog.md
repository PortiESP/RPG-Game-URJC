
# Changes From Design Document
- Removed the `gold` attribute from the `Fight` class.
- Added `getScore` abstract method to the `User` class.
- Removed `checkRanking()` method from the `User` class.

## Game Class Changes
- Rename `registerUser()` method to `register()` method.
- Rename `loginUser()` method to `login()` method.
- Rename`deleteUser()` method to `deleteAccount()` method.
- Rename `manageUsers()` method to `managePlayers()` method.
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
- Added `ban()` method to the `Player` class.
- Added `unban()` method to the `Player` class.
- Added `showInfo()` abstract method to the `User` class.
- Added `changeArmor()` method to the `Player` class.
- Added `changeWeapon()` method to the `Player` class.
- Added `manageNotifications()` method to the `Player` class.

## Weapon & Armor Class Changes
- Added `loadFromArray()` method to the `Weapon` & `Armor` classes in order to load default weapons from Constants class.
- Overrided `toString()` method.

## Constants Class Changes
- Added `WEAPONS` constant to allow players to choose weapons even if the admin has not added them.
- Added `ARMORS` constant to allow players to choose armors even if the admin has not added them.