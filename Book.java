public class Book {

    private String name;
    private Integer year;
    private String author;
    private User owner;
    private static Integer stId = 0;
    private Integer id;

    public Book(String name, Integer year, String author) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.id = ++stId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return author + " - "+ author + " " + name + " (" + year + ") | Owner " + (owner != null ? owner.getName() : "Нет владельца") +" | ID - " + id;
    }

}
