package payments;

import bank.Card;
import exceptions.CardException;
import util.ConsoleInput;
import util.Constant;

import java.math.BigDecimal;

/**
 * Оплата чипом карты
 * Denis
 * 25.03.2018
 */
public class MicrochipPayment implements IPayment {

    private final Card card;
    private final BigDecimal amount;
    private ConsoleInput consoleInput;

    public MicrochipPayment(Card card, BigDecimal amount) {
        this.card = card;
        this.amount = amount;
        consoleInput = new ConsoleInput();
    }

    @Override
    public boolean pay() {
        System.out.println("Оплата покупки в размере " + amount + " руб. чипом " + card);

        if (!authentication()) { // Всегда запрашиваем ПИН-код
            System.out.println("Ошибка аутентификации");
            return false;
        }

        try {
            card.withdraw(amount);
            System.out.println("Платеж в размере " + amount + " руб. прошел успешно!");
            return true;
        } catch (CardException e) {
            System.out.println("Ошибка оплаты: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean authentication() {
        int attempt = 0;
        while (attempt < 3) {
            attempt++;
            System.out.println("Введите пин-код [" + attempt + "]:");
            if (consoleInput.enterNumber(Constant.PIN_CODE_START, Constant.PIN_CODE_END) == card.getPin()) {
                return true;
            }
        }

        card.setLocked(); // Блокируем карту после 3ех неудачных попыток ввода пин-кода
        return false;
    }
}
