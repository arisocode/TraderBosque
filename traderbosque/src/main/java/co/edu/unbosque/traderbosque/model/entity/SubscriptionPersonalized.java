package co.edu.unbosque.traderbosque.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscription_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPersonalized {

    @Id
    @Column(name = "subId")
    private String subId;
    @Column(name = "stripe_customer_id")
    private String stripeCustomerId;
    @Column(name = "start_date")
    private long subDateStart;
    @Column(name = "end_date")
    private long subDateEnd;
    @Column(name = "status")
    private String status;
    @OneToOne(mappedBy = "subscriptionPersonalized")
    @JsonBackReference
    private User user;
}
