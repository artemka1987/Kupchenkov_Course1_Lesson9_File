import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {

    private int userId;
    private String name;
    private String lastName;
    private String email;
    private Date registrationDate;
    private String login;
    private String password;


    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public User(int userId, String name, String lastName, String email, Date registrationDate, String login, String password) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.login = login;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return " userId = " + userId + ", name = " + name + ", lastName = " + lastName + ", email = " + email + ", registrationDate = " + dateFormat.format(registrationDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        if (this.hashCode() != obj.hashCode()) return false;
        if (this.login.equals(((User) obj).getLogin()) && this.password.equals(((User) obj).getPassword())) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = 21;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
