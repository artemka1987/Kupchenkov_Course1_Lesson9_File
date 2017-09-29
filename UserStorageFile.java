import java.io.*;
import java.util.Calendar;

public class UserStorageFile implements UserStorage {

    private File fileUserStorage = new File("UserStorage.txt");

    public UserStorageFile() {
        if (!fileUserStorage.exists()){
            try {
                fileUserStorage.createNewFile();
            } catch (IOException e){
                System.err.println("Ошибка создания файла: " + e.getMessage());
            }
            if (fileUserStorage.canWrite()){
                try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(fileUserStorage))){
                    ous.writeObject(new User(1, "Ivan", "Ivanov", "ivanov@gmail.com", Calendar.getInstance().getTime(), "Ivanov", "123"));
                    ous.writeObject(new User(2, "Petr", "Petrov", "petrov@gmail.com", Calendar.getInstance().getTime(), "Petrov", "456"));
                    ous.writeObject(new User(3, "Oleg", "Sidiriv", "sidiriv@gmail.com", Calendar.getInstance().getTime(), "Sidorov", "789"));
                } catch (IOException e){
                    System.err.println("Ошибка записи в файл: " + e.getMessage());
                }
            } else {
                System.err.println("Файл защищен от записи");
            }
        }

    }


    public File getFileUserStorage() {
        return fileUserStorage;
    }

    @Override
    public User authentication(User user) {
        if (!fileUserStorage.exists()){
                System.err.println("Файла не существует");
        } else {
            if (fileUserStorage.canRead()){
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileUserStorage))){
                    User returnUser = (User)ois.readObject();
                    while (returnUser != null){
                       if (returnUser.equals(user)) return returnUser;
                       returnUser = (User)ois.readObject();
                    }
                } catch (IOException | ClassNotFoundException e){
                    if (!(e instanceof EOFException)) System.err.println("Ошибка чтения файла: " + e.getMessage());
                }
            }else {
                System.err.println("Файл защищен от чтения");
            }
        }
        return null;
    }

}
