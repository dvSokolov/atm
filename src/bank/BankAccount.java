package bank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Счет в Банке
 * Denis
 * 09.03.2018
 */
public class BankAccount {
    private BigInteger cardAccountFl; // Картсчёт ФЛ
    private BigDecimal balance; // Баланс счета
    private List<Card> cards; // Привязанные к счету карты

    public BankAccount(BigInteger cardAccountFl, BigDecimal balance) {
        this.cardAccountFl = cardAccountFl;
        this.balance = balance;
        cards = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigInteger getCardAccountFl() {
        return cardAccountFl;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Card> getCards(){
        return cards;
    }

    public void showCards() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        return cardAccountFl != null ? cardAccountFl.equals(that.cardAccountFl) : that.cardAccountFl == null;
    }

    @Override
    public int hashCode() {
        return cardAccountFl != null ? cardAccountFl.hashCode() : 0;
    }

    public void addCards(Card[] cards) {
        this.cards.addAll(Arrays.asList(cards));
    }
}
