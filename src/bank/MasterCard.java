package bank;


import util.Constant;

/**
 * Банковская карта MasterCard
 * Denis
 * 10.02.2018
 */
public class MasterCard extends Card {
    public MasterCard() {
        name = "MasterCard";
        paymentSystem = "MasterCard";
        typeProtection = "Microchip";
        cvv = randomNumber(Constant.CVV_START, Constant.CVV_END);
        pin = randomNumber(Constant.PIN_CODE_START, Constant.PIN_CODE_END);
        setCardExpiryDate(3, 0);
    }

    @Override
    public String toString() {
        return name + ", cvv " + cvv + ", pin " + pin + ", срок действия до " + cardExpiryMonth + "/" + cardExpiryYear;
    }

}
