public class Book {

    private String name;
    private Integer year;
    private String author;
    private Integer ownerId;
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }



    private static Integer stId = 0;
    private Integer id;

    public Book(String name, Integer year, String author , Integer ownerId) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.id = ++stId;
        this.ownerId = ownerId;
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

    

    @Override
    public String toString() {
        return author + " - "+ author + " " + name + " (" + year + ") | Owner " + (ownerId != null ? "ID владельца " + ownerId : "Нет владельца") +" | ID - " + id;
    }

}
