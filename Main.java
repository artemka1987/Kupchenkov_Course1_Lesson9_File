
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MessageStorage messageStorage;
    private static User currentUser;
    private static UserStorage userStorage;

    public static void main(String[] args) {

        String login;
        String password;
        int userStoragetype;

        //Создаем хранилище сообщений
        messageStorage = new MessageStorage();

        System.out.println("Укажите используемое хранилище пользователей");
        System.out.println("1. List");
        System.out.println("2. File");
        userStoragetype = readConsoleMenuItem(1, 2);
        switch (userStoragetype){
            case 1: {
                userStorage = new UserStorageList();
                break;
            }
            case 2:{
                userStorage = new UserStorageFile();
                break;
            }
        }



        System.out.println("Для входа введите логин и пароль");
        System.out.print("Логин: " );
        login = scanner.next();
        System.out.print("Пароль: " );
        password = scanner.next();

        currentUser = userStorage.authentication(new User(login, password));


        if (currentUser != null){
            viewMainMenu();
        }else {
            System.out.println("Нет такого пользователя");
        }

    }



    // Метод считывания пункта меню
    private static int readConsoleMenuItem(int minValue, int maxValue){
        int item = 0;
        do{
            while (!scanner.hasNextInt()){
                System.out.println("Вы неверно ввели число , попробуйте еще раз...");
                scanner.next();
            }
            item = scanner.nextInt();
            if (item < minValue || item > maxValue){
                System.out.println("Нет такого пункта меню");
            }
        }while (item < minValue || item > maxValue);
        return item;
    }




    // Метод отображения основного меню
    private static void viewMainMenu(){
        int menuItem;
        System.out.println("Укажите номер пункта меню:");
        System.out.println("1. Показать все мои посты");
        System.out.println("2. Написать сообшение пользователю");
        System.out.println("3. Добавить пост");
        System.out.println("4. выход");
        menuItem = readConsoleMenuItem(1,4);
        switch (menuItem){
            case 1:
                viewUserPosts(currentUser);
                break;
            case 2:
                User recipientUser = selectUser(currentUser);
                if (recipientUser != null){
                    writePrivateMessage(recipientUser);
                };
                break;
            case 3:
                addPostMessage();
                break;
            case 4:
                System.exit(0);
                break;
        }
    }



    // Метод отображения постов пользователя
    private static void viewUserPosts(User author){
        int messageCount = 0;
        for(AbstractMessage message : messageStorage.getMessages()){
            if (message.author.equals(author)) {
                System.out.println("   " + message.toString());
                messageCount++;
            }
        }
        if (messageCount == 0) System.out.println("   У Вас еще нет ни одного сообщения");
        int menuItem;
        System.out.println("   1. <<Назад");
        System.out.println("   2. Выход");
        menuItem = readConsoleMenuItem(1,2);
        switch (menuItem){
            case 1:
                viewMainMenu();
            case 2:
                System.exit(0);
        }
    }




    // Метод написания сообщения пользователю
    private static void writePrivateMessage(User recipientUser){
        scanner.nextLine();
        System.out.println("   Введите текст сообщения:");
        String text = scanner.nextLine();
        PrivateMessage privateMessage = new PrivateMessage(messageStorage.getMessages().length + 1, text, currentUser, Calendar.getInstance().getTime(), recipientUser);
        privateMessage.send(messageStorage);
        System.out.println("   Сообщение добавлено");
        viewMainMenu();
    }



    // Метод добавления поста
    private static void addPostMessage(){
        scanner.nextLine();
        System.out.println("   Введите заголовок:");
        String title = scanner.nextLine();
        System.out.println("   Введите текст:");
        String text = scanner.nextLine();
        PostMessage postMessage = new PostMessage(messageStorage.getMessages().length + 1, text, currentUser, Calendar.getInstance().getTime(),
                                                new Post(messageStorage.getMessages().length + 1, title, text, Calendar.getInstance().getTime()));
        postMessage.send(messageStorage);
        System.out.println("   Пост добавлен");
        viewMainMenu();
    }



    // Метод выбора пользователя для взаимодействия
    private static User selectUser(User currentUser){
        int createMenuItem = 1;
        User[] users = new User[UserStorageList.users.size() + 1];
        System.out.println("   Укажите пользователя");
        for(User recipientUser : UserStorageList.users){
            if (currentUser.equals(recipientUser)){
                continue;}
                System.out.println("   " + createMenuItem + ". " + recipientUser.toString());
                users[createMenuItem] = recipientUser;
                createMenuItem++;
        }
        if (createMenuItem == 1){
            System.out.println("Нет пользователей");
            return null;
        }else {
            return users[readConsoleMenuItem(1, createMenuItem)];
        }
    }






}
