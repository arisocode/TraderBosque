package co.edu.unbosque.traderbosque.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "alpacaAccountId")
    private String alpacaAccountId;

    @Column(name = "alpacaStatus")
    private String alpacaStatus;

    @Column(name = "isVerified")
    private boolean isVerified;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_id", referencedColumnName = "subId")
    @JsonManagedReference
    private SubscriptionPersonalized subscriptionPersonalized;
}

