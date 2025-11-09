package rs.digit.bpts.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "id")
    @GeneratedValue
    private String id;

    @Column(nullable = false)
    private BigDecimal funds;

    public void debit(BigDecimal amount) {
        this.funds = this.funds.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        this.funds = this.funds.add(amount);
    }

}
