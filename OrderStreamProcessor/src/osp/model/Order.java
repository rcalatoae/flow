package osp.models;
import java.math.BigDecimal;

public class Order implements Comparable<Order> {

    private final long timestamp;
    private String orderID;
    private BigDecimal price;
    private BigDecimal quantity;
    private final Side side;
    private String listing;

    public Order(long timestamp, String orderID, BigDecimal price, BigDecimal quantity, Side side, String listing) {
        this.timestamp = timestamp;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.listing = listing;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getOrderID() {
        return orderID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Side getSide() {
        return side;
    }

    public String getListing() {
        return listing;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Order o) {
        if (this.timestamp < o.timestamp) {
            return -1;
        } else if (this.timestamp > o.timestamp) {
            return 1;
        } else {
           return 0;
        }
    }
}
