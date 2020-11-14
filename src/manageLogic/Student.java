package manageLogic;

public class Student {
    private String id;
    private String name;
    private String surname;
    private String fathersName;
    private String birthday;
    private String group;

    public Student(String id, String name, String surname, String fathersName, String birthday ,String group){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.birthday = birthday;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
