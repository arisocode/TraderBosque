package co.edu.unbosque.traderbosque.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asset {
    private String id;
    private String symbol;
    private String status;
    private String exchange;
    private String assetClass;
}
