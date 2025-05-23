package co.edu.unbosque.traderbosque.model.entity;

import co.edu.unbosque.traderbosque.model.enums.OrderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "client_order_id")
    private String clientOrderId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "qty")
    private String qty;

    @Column(name = "side")
    private String side;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OrderType type;

    @Column(name = "status")
    private String status;

    @Column(name = "filled_qty")
    private String filledQty;

    @Column(name = "filled_avg_price")
    private String filledAvgPrice;

    @Column(name = "submitted_at")
    private String submittedAt;

    @Column(name = "filled_at")
    private String filledAt;

    @Column(name = "stopPrice")
    private double stopPrice;

    @Column(name = "timeInForce")
    private String timeInForce;

    @Column(name = "limitPrice")
    private double limitPrice;

    @Column(name = "raw_response", columnDefinition = "TEXT")
    private String rawResponse;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
