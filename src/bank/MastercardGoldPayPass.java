package bank;

import util.Constant;

/**
 * Банковская карта Mastercard Gold PayPass
 * Denis
 * 24.03.2018
 */
public class MastercardGoldPayPass extends Card {
    public MastercardGoldPayPass() {
        name = "MastercardGoldPayPass";
        paymentSystem = "MasterCard";
        cvv = randomNumber(Constant.CVV_START, Constant.CVV_END);
        pin = randomNumber(Constant.PIN_CODE_START, Constant.PIN_CODE_START);
        setCardExpiryDate(3, 0);
    }

    @Override
    public String toString() {
        return name + ", cvv " + cvv + ", pin " + pin + ", срок действия до " + cardExpiryMonth + "/" + cardExpiryYear;
    }
}
