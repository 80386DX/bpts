package rs.digit.bpts.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;


@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@Data
public class Account {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "id")
    @GeneratedValue
    private String id;

    @Column(nullable = false)
    private BigDecimal funds;

    //Here for reason not to make JPA sad
    public Account() {}

    public Account(String id, BigDecimal funds) {
        this.id = id;
        this.funds = funds;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public void debit(BigDecimal amount) {
        this.funds = this.funds.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        this.funds = this.funds.add(amount);
    }

}
