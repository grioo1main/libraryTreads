
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Service service = new Service();
        Scanner sc = new Scanner(System.in);
        User user;
        while (true) {
            System.out.println("Введите ваше ID:");
            Integer id = sc.nextInt();
            Optional<User> userOptional = service.getLibrary().getUsers().stream()
                    .filter(u -> u.getId() == id) // или u.getId().equals(id), если Integer
                    .findFirst();
            user = userOptional.orElse(null);
            if (user == null) {
                System.out.println("Введите корректное ID");
                continue;
            }
            break;
        } Boolean flag = true;
            while (flag) {
                System.out.println(""" 

                        1. Посмотреть свои книги
                        2. Взять книгу
                        3. Вернуть книгу
                        4. Посмотреть свободные книги
                        5. Выход
                        """);
                switch (sc.nextInt()) {
                    case 1:
                        service.readMyBook(user);
                        break;
                    case 2:
                        System.out.println("Введите id вашей книги");
                        Integer bookId = sc.nextInt();
                        service.takeBook(user, bookId);
                        break;
                    case 3:
                        System.out.println("Введите id вашей книги");
                        Integer bookId2 = sc.nextInt();
                        service.returnBook(user, bookId2);
                        break;
                    case 4:
                        service.readAllNOOwnerBook();
                        break;
                    case 5:
                        flag = false;
                        break;
            
                    default:
                        continue;
                }

            }

        }

    }

