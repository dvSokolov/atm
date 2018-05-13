package bank;

import util.Constant;

/**
 * Банковская карта Visa
 * Denis
 * 10.02.2018
 */
public class Visa extends Card {
    public Visa() {
        name = "Visa";
        paymentSystem = "Visa";
        typeProtection = "MagneticStripe";
        cvv = randomNumber(Constant.CVV_START, Constant.CVV_END);
        pin = randomNumber(Constant.PIN_CODE_START, Constant.PIN_CODE_END);
        setCardExpiryDate(2, 0);
    }

    @Override
    public String toString() {
        return name + ", cvv " + cvv + ", pin " + pin + ", срок действия до " + cardExpiryMonth + "/" + cardExpiryYear;
    }

}
