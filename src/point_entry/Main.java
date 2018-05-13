package point_entry;

import atm.Atm;
import atm.InternetShop;
import bank.*;
import exceptions.CardException;
import payments.IPayment;
import payments.MagneticStriperPayment;
import payments.MicrochipPayment;
import payments.PayPassPayment;
import readers.FileReader;
import writers.FileWriter;

import java.math.BigDecimal;
import java.util.*;

/**
 * Точка входа
 * Denis
 * 10.02.2018
 */
public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank("Сбербанк");

        // возможность сохранять/загружать в файл пользователя, карточку с помощью потоков ввода/вывода
        String clientsFile = "clients.bin";


        // Читаем клиентов из файла
        FileReader<Client> fileReader = new FileReader<Client>();
        ArrayList<Client> clients = fileReader.read(clientsFile);

        // В файле клиентов нету, читаем из "БД" и записываем в файл
        if (clients.size() == 0) {

            // Читаем клиентов из "БД" и записываем в файл
            Client client1 = new Client(
                    "Иванов Иван Иванович",
                    1234,
                    567890 // Разные паспортные данные
            );

            Client client2 = new Client(
                    "Иванов Иван Иванович",
                    1234,
                    567891 // Разные паспортные данные
            );

            clients = new ArrayList<>(Arrays.asList(client1, client2));

            // Запись клиентов в файл
            FileWriter<Client> fileWriter = new FileWriter<Client>();
            fileWriter.write(new ArrayList<Client>(clients), clientsFile);

            System.out.println("Чтение клиентов из \"БД\" и запись в файл \"" + clientsFile + "\"");
        } else {
            System.out.println("Чтение клиентов из файла \"" + clientsFile + "\"");
        }

        Client client1 = clients.get(0);
        Client client2 = clients.get(1);

        bank.openAccount(
                client1,
                new BigDecimal("10000"),
                new Card[]{
                        new Visa(),
                        new MasterCard()
                }
        );
        bank.openAccount(
                client2,
                new BigDecimal("20000"),
                new Card[]{
                        new Visa()
                }
        );
        bank.openAccount(
                client1,
                new BigDecimal("15000"),
                new Card[]{
                        new MasterCard()
                }
        );

        // Информация о всех клиентах
//        bank.showClients();

        // Достанем все банковские карты клиента
        List<Card> cards = bank.getCards(client1);

        // Все карты клиента №1
        //System.out.println(cards.toString());
        Card card1 = cards.get(0); // MasterCard с возможностью бесконтактной оплаты PayPass
        Card card2 = cards.get(1); // Visa с магнитной полосой
        Card card3 = cards.get(2); // MasterCard с возможностью бесконтактной оплаты PayPass

        // Бесконтактная оплата (PayPass)
        IPayment payPassPayment = new PayPassPayment(card1, BigDecimal.valueOf(800));
        payPassPayment.pay();

        // Оплата чипом карты
        IPayment microchipPayment = new MicrochipPayment(card3, BigDecimal.valueOf(1350));
        microchipPayment.pay();

        // Оплата магнитной полосой карты
        IPayment magneticStriperPayment = new MagneticStriperPayment(card2, BigDecimal.valueOf(100000));
        magneticStriperPayment.pay();


        // Проверим баланс в Банкомате
        Atm atm = new Atm();
        ArrayList<String> supportedPaymentSystems = new ArrayList<>(Arrays.asList("MasterCard", "Visa", "Mir"));
        atm.addSupportedPaymentSystems(supportedPaymentSystems);
        try {
            atm.insertCard(card2);
            atm.showBalance();
            // Снимем денег и выведем остаток на карточном счёте
            atm.withdraw(BigDecimal.valueOf(2000));
            atm.showBalance();

        } catch (CardException e) {
            System.out.println(e.getMessage());
        } finally {
            // Извлекаем карту
            atm.eject();
        }

        // Расплатимся картой в интенет магазине
        System.out.println(card3);

        InternetShop internetShop = new InternetShop(card3);
        try {
            internetShop.withdraw(BigDecimal.valueOf(3500));
            System.out.println("Оплата успешно произведена!");
        } catch (CardException e) {
            System.out.println(e.getMessage());
            System.out.println("Оплата не произведена");
        }

    }
}
