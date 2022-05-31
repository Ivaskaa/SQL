package Hibernate.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_and_time")
    @Temporal(TemporalType.TIMESTAMP)
    //private Timestamp dateAndTime;
    private Date dateAndTime;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> products;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<User> users;

    public Order(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //public Timestamp getDateAndTime() {
    //    return dateAndTime;
    //}
    public Date getDateAndTime() {
        return dateAndTime;
    }

//    public void setDateAndTime(Timestamp time) {
//        this.dateAndTime = time;
//    }
    public void setDateAndTime(Date time) {
        this.dateAndTime = time;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(dateAndTime, order.dateAndTime) && Objects.equals(products, order.products) && Objects.equals(users, order.users);
    }

}
