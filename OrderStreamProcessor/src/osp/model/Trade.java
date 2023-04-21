package osp.models;
import java.math.BigDecimal;


public class Trade {
    private final long timestamp;
    private BigDecimal price;
    private BigDecimal quantity;
    private final Side side;
    private String listing;

    public Trade(long timestamp, String listing, Side side, BigDecimal quantity, BigDecimal price) {
        this.timestamp = timestamp;
        this.listing = listing;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getListing() {
        return listing;
    }

    public Side getSide() {
        return side;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
