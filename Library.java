import java.util.ArrayList;

public class Library {


    private ArrayList<Book> books = new ArrayList<>();  // ← добавь = new ArrayList<>()
    private ArrayList<User> users = new ArrayList<>();  // ← добавь = new ArrayList<>()
    





    

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}
