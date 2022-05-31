package JDBC.entity;

import java.util.Objects;

public class DetailsUser {
    private int id;
    private String name;
    private String surname;
    private int age;
    private int user_id;

    public DetailsUser() {}


    public DetailsUser(int id, String name, String surname, int age, int user_id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "DetailsUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", user_id=" + user_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsUser that = (DetailsUser) o;
        return id == that.id && age == that.age && user_id == that.user_id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

}
