package co.edu.unbosque.traderbosque.model.entity;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "holding")
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
     @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "quantity")
    private double qty;

    @Column(name = "avg_price")
    private double avgPrice;

    @LastModifiedDate
    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    @Column(name = "currency")
    private String currency;

    @Column(name = "asset_type")
    private String assetType;
}
