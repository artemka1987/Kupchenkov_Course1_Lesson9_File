import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserStorageList implements UserStorage {

    public static List<User> users = new ArrayList<>();


    public UserStorageList() {
        users.add(new User(1, "Ivan", "Ivanov", "ivanov@gmail.com", Calendar.getInstance().getTime(), "Ivanov", "123"));
        users.add(new User(2, "Petr", "Petrov", "petrov@gmail.com", Calendar.getInstance().getTime(), "Petrov", "456"));
        users.add(new User(3, "Oleg", "Sidiriv", "sidiriv@gmail.com", Calendar.getInstance().getTime(), "Sidorov", "789"));
    }

    @Override
    public User authentication(User user) {
        for (User otherUser : users){
            if (otherUser.equals(user)) return otherUser;
        }
        return null;
    }
}
