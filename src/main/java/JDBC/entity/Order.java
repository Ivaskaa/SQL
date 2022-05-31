package JDBC.entity;

import java.sql.Timestamp;

public class Order {
    private int id;
    private Timestamp dateAndTime;
    private int productId;
    private int userId;

    public Order(){}

    public Order(int id, Timestamp time, int productId, int userId) {
        this.id = id;
        this.dateAndTime = time;
        this.productId = productId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp time) {
        this.dateAndTime = time;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int product_id) {
        this.productId = product_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", time=" + dateAndTime +
                ", product_id=" + productId +
                ", user_id=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        long time1 = dateAndTime.getTime();
        long time2 = order.dateAndTime.getTime();
        if(time2 - time1 > 10000){
            return false;
        }
        return id == order.id && productId == order.productId && userId == order.userId;
    }

}
