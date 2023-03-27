// Демонстрация паттерна Facade

public class Main {
    public static void main(String[] args) {
        int bankAccount = 123417564;
        String address = "Raccoon Hole Number Zero";

        Hacker.BuyACupOfCofee(bankAccount, address);
    }
}

// Фасад для библиотеки Hacker
class Hacker {
    static public void MakeMoney(int bankAccount) {
        String[] CIAAccount;
        Object CIAData;

        do {
            CIAAccount = MyLibrary.Hacker.HackСIAAccount();
        } while (CIAAccount == null);

        try {
            CIAData = MyLibrary.Hacker.DownloadDataFromCIADatabase(CIAAccount[0], CIAAccount[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("C индексацией проблемка");
            return;
        }

        MyLibrary.Hacker.SellDatOnDarknet(CIAData, bankAccount);
        System.out.println("Your bank account has been replenished");
    }

    static public boolean BuyACupOfCofee(int bankAccount, String address) {
        while (MyLibrary.Hacker.BuyCupOfCofee(bankAccount, address) == false) {
            System.out.println("Not enough money");
            MakeMoney(bankAccount);
        }

        System.out.println("Сoffee will be delivered to " + address + " soon");
        return true;
    }
}

