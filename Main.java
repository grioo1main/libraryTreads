
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    // public static void main(String[] args) {
        // Service service = new Service();
        // Scanner sc = new Scanner(System.in);
        // User user;
        // while (true) {
        //     System.out.println("Введите ваше ID:");
        //     Integer id = sc.nextInt();
        //     Optional<User> userOptional = service.getLibrary().getUsers().stream()
        //             .filter(u -> u.getId() == id) // или u.getId().equals(id), если Integer
        //             .findFirst();
        //     user = userOptional.orElse(null);
        //     if (user == null) {
        //         System.out.println("Введите корректное ID");
        //         continue;
        //     }
        //     break;
        // } Boolean flag = true;
        //     while (flag) {
        //         System.out.println(""" 

        //                 1. Посмотреть свои книги
        //                 2. Взять книгу
        //                 3. Вернуть книгу
        //                 4. Посмотреть свободные книги
        //                 5. Выход
        //                 """);
        //         switch (sc.nextInt()) {
        //             case 1:
        //                 service.readMyBook(user);
        //                 break;
        //             case 2:
        //                 System.out.println("Введите id вашей книги");
        //                 Integer bookId = sc.nextInt();
        //                 service.takeBook(user, bookId);
        //                 break;
        //             case 3:
        //                 System.out.println("Введите id вашей книги");
        //                 Integer bookId2 = sc.nextInt();
        //                 service.returnBook(user, bookId2);
        //                 break;
        //             case 4:
        //                 service.readAllNOOwnerBook();
        //                 break;
        //             case 5:
        //                 flag = false;
        //                 break;
            
        //             default:
        //                 continue;
        //         }

        //     }

        // }
    public static void main(String[] args) throws Exception {
    Service service = new Service();

    ExecutorService eService = Executors.newFixedThreadPool(4);
    System.out.println("Созданы 4 потока");

    List<Future<String>> futures = new ArrayList<>();

    int TASK_COUNT = 1000;

    // общий счётчик id книг для всех потоков
    AtomicInteger correctBookId = new AtomicInteger(1);

    for (int i = 1; i <= TASK_COUNT; i++) {
        final int taskId = i;

        Future<String> future = eService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Поток [" + threadName + "] начал задачу #" + taskId);

            // аккуратно с индексами, если AllUsers() с 0
            User user = service.AllUsers().get(taskId - 1);

            for (int j = 0; j < 3; j++) {
                // 1) берём уникальный id книги и сразу его увеличиваем
                int bookId = correctBookId.getAndIncrement(); // атомарно [web:22]

                // 2) выдаём книгу
                service.takeBook(user, bookId);

                // 3) логируем
                System.out.println(
                    "User " + user + " взял книгу " + service.getBookById(bookId)
                );
            }

            return "task " + taskId + " done";
        });

        futures.add(future);
    }

    // ждём результаты (по желанию)
    for (Future<String> f : futures) {
        System.out.println(f.get());
    }

    eService.shutdown();
}


}