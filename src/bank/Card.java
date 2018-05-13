package bank;

import exceptions.CardException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Random;

/**
 * Карта
 * Denis
 * 10.02.2018
 */
public abstract class Card {
    protected BankAccount bankAccount; // Счет в Банке
    //protected int number; // пока не используем
    protected int pin;
    protected int cvv;
    protected int cardExpiryMonth;
    protected int cardExpiryYear;
    protected String name;
    protected boolean locked; // блокировка карты
    protected String paymentSystem; // Платежная система
    protected String typeProtection; // Тип защиты карты: чипованные, с магнитной полосой

    public boolean isLocked() {
        return locked;
    }

    // чтобы можно было узнать остаток на карте,
    public BigDecimal getBalance() {
        return bankAccount.getBalance();
    }

    // Геттер платежной системы карты
    public String getPaymentSystem() {
        return paymentSystem;
    }

    // Привяжем банковский счет к карте
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // чтобы можно было списать часть средств с карты для выдачи наличными.
    public void withdraw(BigDecimal amount) throws CardException {
        BigDecimal balance = bankAccount.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new CardException("На карте недостаточно средств");
        }
        bankAccount.setBalance(balance.subtract(amount));
    }

    // Генерация случайного числа в диапазоне от min до max
    protected int randomNumber(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    // Установим срок действия карты
    protected void setCardExpiryDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        cardExpiryYear = calendar.get(Calendar.YEAR) + year;
        cardExpiryMonth = calendar.get(Calendar.MONTH) + month;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public void setLocked() {
        locked = true;
    }

    public int getCardExpiryMonth() {
        return cardExpiryMonth;
    }

    public int getCardExpiryYear() {
        return cardExpiryYear;
    }

    public int getCvv() {
        return cvv;
    }

    public String getTypeProtection() {
        return typeProtection;
    }

}
