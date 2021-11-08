package JDBC.connnectionPool.curd;

import java.util.Date;

public class Customer {
    int id;
    String name;
    String email;
    Date birth;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Customer() {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public Customer(int id) {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
