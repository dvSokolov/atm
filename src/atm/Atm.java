package atm;

import bank.Card;
import exceptions.CardException;
import readers.IReader;
import readers.MagneticStripeReader;
import readers.MicrochipReader;
import util.ConsoleInput;
import util.Constant;

import java.util.ArrayList;
import java.math.BigDecimal;

/**
 * Банкомат
 * Denis
 * 10.02.2018
 */
public class Atm {
    private Card currentCard;
    private boolean accessGranted;
    private ConsoleInput consoleInput;
    private ArrayList<String> supportedPaymentSystems; // Поддерживаемые платежные системы

    public Atm() {
        supportedPaymentSystems = new ArrayList<>();
        consoleInput = new ConsoleInput();
    }

    // Добавить поддерживаемые платежные системы
    public void addSupportedPaymentSystems(ArrayList arrayList) {
        supportedPaymentSystems.addAll(arrayList);
    }

    public void insertCard(Card card) throws CardException {
        //todo dz избежать повторной вставки
        if (currentCard != null) {
            throw new CardException("Карточка " + currentCard.getName() + " уже в банкомате");
        } else if (!readCard(card)) {
            throw new CardException("Карта " + card.getName() + " не может быть прочитана банкоматом!");
        }else if(!checkSupportedPaymentSystems(card)){
            throw new CardException("Платежная система " + card.getPaymentSystem() + " не поддерживается банкоматом!");
        } else if (card.isLocked()) {
            throw new CardException("Карта " + card.getName() + " заблокирована!");
        }

        accessGranted = false;
        currentCard = card;
        int attempt = 0;
        while (attempt < 3) {
            attempt++;
            System.out.println("Введите пин-код [" + attempt + "]:");
            if (consoleInput.enterNumber(Constant.PIN_CODE_START, Constant.PIN_CODE_END) == card.getPin()) {
                accessGranted = true;
                break;
            }
        }

        if (!accessGranted) {
            card.setLocked(); // Блокируем карту после 3ех неудачных попыток ввода пин-кода
            currentCard = null;
            throw new CardException("Карта " + card.getName() + " заблокирована!");
        }

    }

    // Чтение банкоматом карты (чипа или магнитной полосы)
    private boolean readCard(Card card) {

        IReader reader;
        String typeProtection = card.getTypeProtection();
        if(typeProtection.equals("Microchip")){ // Читаем микрочип
            reader = new MicrochipReader(card);
            reader.read();
            return true;
        }else if(typeProtection.equals("MagneticStripe")){ // Читаем магнитную полосу
            reader = new MagneticStripeReader(card);
            reader.read();
            return true;
        }

        return false;
    }

    // Проверим поддерживается ли платежная система банкоматом
    private boolean checkSupportedPaymentSystems(Card card){
        return supportedPaymentSystems.indexOf(card.getPaymentSystem()) >= 0;
    }

    public void eject() {
        currentCard = null;
        System.out.println("Спасибо. Не забудьте вашу карту!");
    }

    public void showBalance() throws CardException {
        if (accessGranted) {
            //todo dz проверить вставлена ли карта
            if (currentCard == null) {
                throw new CardException("Карта не вставлена");
            }
            System.out.println("Balance: " + currentCard.getBalance());
        }
    }

    public void withdraw(BigDecimal amount) throws CardException {
        if (accessGranted) {
            //todo dz проверить вставлена ли карта
            if (currentCard == null) {
                throw new CardException("Карта не вставлена");
            }
            currentCard.withdraw(amount);
        }
    }
}
