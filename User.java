public class User {
    
    private static Integer stId = 0;
    private Integer id;
    private String name;
    private Integer years;
    private Boolean isAdmin;

    
    public User(String name, Integer years) {
        this.id = ++stId;
        this.name = name;
        this.years = years;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYears() {
        return years;
    }
    public void setYears(Integer years) {
        this.years = years;
    }
    
    
}
