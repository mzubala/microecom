package pl.com.bottega.microecom.commons.api;

import org.hibernate.validator.constraints.NotBlank;
import pl.com.bottega.microecom.commons.model.Money;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

public class MoneyDto {

    @NotNull
    private BigDecimal value;

    @NotBlank
    private String currency = Money.DEFAULT_CURRENCY.getCurrencyCode();

    public MoneyDto() {
    }

    public MoneyDto(Money money) {
        this.value = money.value();
        this.currency = money.currencyCode();
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Money toMoney() {
        return new Money(value, Currency.getInstance(currency));
    }
}
