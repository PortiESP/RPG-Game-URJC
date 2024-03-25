package src.users;

/**
 * Abstract class that represents a user.
 */
public abstract class User {

    private String name;
    private String nick;
    private String password;

    // ============================================================================================[ Constructor ]>>>

    // Zero Constructor
    public User() {}

    // Constructor with parameters
    public User(String name, String nick, String password) {
        this.name = name;
        this.nick = nick;
        this.password = password;
    }

    // ============================================================================================[ Public Methods ]>>>

    // Abstract method to get the score
    public abstract int getScore();

    // ============================================================================================[ Getters & Setters ]>>>
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
