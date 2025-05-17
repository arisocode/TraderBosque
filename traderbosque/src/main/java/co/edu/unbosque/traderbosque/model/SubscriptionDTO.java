package co.edu.unbosque.traderbosque.model;

import lombok.Getter;
import lombok.Setter;

/*
* Una clase DTO modelando una suscripción a un servicio
*
* @author Mathew Garzón
* @version 1.0
* */
@Getter
@Setter
public class SubscriptionDTO {
    private String username;
    private String subId;
    private String customerId;
    private long subDateStart;
    private long subDateEnd;
    private String status;
}
