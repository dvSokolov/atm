package bank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Банк
 * Denis
 * 09.03.2018
 */
public class Bank {
    private String name;
    private static BigInteger cardAccountFlStart = new BigInteger("40800000000000000000");
    private HashMap<Client, Set<BankAccount>> clients; // Коллекция с клиентами Банка

    public Bank(String name) {
        this.name = name;
        clients = new HashMap<>();
    }

    // Открыть счёт клиенту и выпустить карты
    public void openAccount(Client client, BigDecimal balance, Card[] cards) {
        cardAccountFlStart = cardAccountFlStart.add(BigInteger.valueOf(1));

        Set<BankAccount> clientAccounts; // Счета клиента
        if (clients.containsKey(client)) {
            clientAccounts = clients.get(client); // Уже имеется клиент в Банке
        } else {
            clientAccounts = new HashSet<>();
        }

        BankAccount bankAccount = new BankAccount(cardAccountFlStart, balance); // Открыли счёт
        for (Card card : cards) {
            card.setBankAccount(bankAccount); // Привязали к картам счёт (для получения информации в банкомате)
        }
        bankAccount.addCards(cards); // Привязали к счёту карты
        clientAccounts.add(bankAccount); // Добавили клиенту новый счёт
        clients.put(client, clientAccounts); // Обновили коллекцию счётов клиента в Банке
    }


    @Override
    public String toString() {
        return name;
    }

    public HashMap<Client, Set<BankAccount>> getClients() {
        return clients;
    }

    // Получим все карты клиента со всех его счетов
    public List<Card> getCards(Client client) {
        List<Card> clientCards = new ArrayList<>();
        for (BankAccount bankAccount : clients.get(client)) {
            clientCards.addAll(bankAccount.getCards());
        }
        return clientCards;
    }

    // Информация о всех клиентах (пин-коды отображены только для банкоматов)
    public void showClients() {

        for (Map.Entry<Client, Set<BankAccount>> item : clients.entrySet()) {
            System.out.println("Клиент " + item.getKey());
            for (BankAccount bankAccount : item.getValue()) {
                System.out.println("Счёт № " + bankAccount.getCardAccountFl());
                System.out.println("Баланс " + bankAccount.getBalance());
                System.out.println("Привязанные карты: ");
                for (Card card : bankAccount.getCards()) {
                    System.out.println(card);
                }
            }
            System.out.println();
        }

    }
}
