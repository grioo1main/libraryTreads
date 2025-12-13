import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Service {
    private Library library = new Library();
    
    public Library getLibrary() { return library; }
    

    {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("/media/mint1grio/Новый том/MyOtherProject/libraly/books.csv"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                // Проверяем, что строка корректная
                if (parts.length >= 4) {
                    String name = parts[0]; // название
                    String author = parts[1]; // автор
                    int id = Integer.parseInt(parts[2]); // ID
                    int year = Integer.parseInt(parts[3]); // год

                    // Предполагаем конструктор: Book(name, year, new User(author))
                    library.getBooks().add(new Book(name, year, author));
                }
            }
        } catch (Exception e) {
            e.setStackTrace(null);
        }
        System.out.println("Загружено " + library.getBooks().size() + " книг");
        try (BufferedReader reader2 = new BufferedReader(
                new FileReader("/media/mint1grio/Новый том/MyOtherProject/libraly/users.csv"))) {

            String line2;
            while ((line2 = reader2.readLine()) != null) {
                String[] parts = line2.split(";");

                // Проверяем, что строка корректная
                if (parts.length >= 2) {
                    String name = parts[0]; // название
                    int year = Integer.parseInt(parts[1]); // год

                    library.getUsers().add(new User(name, year));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Ошибка парсинга чисел: " + e.getMessage());
        }
    }

    public void readAllBooks() {
        library.getBooks().stream()
                .forEach(System.out::println);

    }

    public void addBook(Book book) {
        library.getBooks().add(book);
        System.out.println("Книга успешно добавленна");

    }

    public void readMyBook(User user) {
    List<Book> userBooks = library.getBooks().stream()
        .filter(b -> b.getOwner() != null && b.getOwner().getId().equals(user.getId()))
        .collect(Collectors.toList());
    
    if (userBooks.isEmpty()) {
        System.out.println("У пользователя нет книг");
    } else {
        userBooks.forEach(System.out::println);
        System.out.println("Найдено книг: " + userBooks.size());
    }
}


    public void takeBook(User user, Integer id) {
        Optional<Book> book = library.getBooks().stream()
                .filter(b -> b.getId().equals(id)) // ищем книгу по ID
                .findFirst(); // берём первую найденную

        Book book2 = book.orElse(null);

        if (book.isPresent() && (book2.getOwner() == null)) {

            // логика взятия книги пользователем
            book2.setOwner(user); // или что-то подобное
            System.out.println("Книга " + book2.getName() + " взята пользователем " + user.getName());
        } else {
            System.out.println("Книга с ID " + id + " не найдена или взята другим пользователем");
        }
    }

    public void returnBook(User user, Integer id) {
        List<Book> books = library.getBooks().stream()
                .filter(b -> b.getOwner() != null && b.getOwner().getId().equals(user.getId()))
                .collect(Collectors.toList());
        Optional<Book> bookOPT = books.stream().filter(b -> b.getId() == id).findFirst();
        if (bookOPT.isPresent()){
            Book book = bookOPT.get();
            book.setOwner(null);
            System.out.println("Книга успешно возварщена");
        } else {
            System.out.println("Ошибка , проверьте корректность ID");
        }
    }

    public void readAllNOOwnerBook(){
        library.getBooks().stream()
                .sorted(Comparator.comparing(b -> b.getOwner() == null))
                .forEach(System.out::println);
    }
}
