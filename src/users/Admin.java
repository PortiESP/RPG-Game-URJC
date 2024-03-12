package src.users;

public class Admin extends User {

    // ============================================================================================[ Constructor ]>>>

    // Zero Constructor
    public Admin() {
    }

    // Constructor with parameters
    public Admin(String name, String nick, String password) {
        super(name, nick, password);
    }

    // ============================================================================================[ Public Methods ]>>>

    // Method to get the score
    public int getScore() {
        return 0;
    }
}
