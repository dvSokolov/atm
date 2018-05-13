package exceptions;

/**
 * Исключение по картам
 * Denis
 * 24.03.2018
 */
public class CardException extends Exception {
    public CardException(){
        super();
    }
    public CardException(String string) {
        super(string);
    }
}
