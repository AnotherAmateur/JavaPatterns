package MyLibrary;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


public class Hacker {
    static public boolean CallPresidentsSecretaryToMakeAppointment(Date dateOfAppointment) {
        return ThreadLocalRandom.current().nextInt(0, 2) == 1;
    }

    static public String[] BuyYuanCheap() {
        String[] result = new String[2];

        if (ThreadLocalRandom.current().nextInt(0, 2) == 1) {
            result[0] = "login";
            result[1] = "123";
            return result;
        }

        return null;
    }

    static public Object СonvertСurrency(String login, String password) {
        return "data";
    }

    static public boolean SellCurrency(Object data, int bankAccount) {
        try {
            if (Integer.toString(bankAccount).length() < 9) {
                throw new ArithmeticException("!!!!!!! The bank account number is too short!!!!!!!!!!!!");
            }
        }catch(ArithmeticException e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    static public boolean BuyCupOfCofee(int bankAccount, String address) {
        return ThreadLocalRandom.current().nextInt(0, 2) == 1;
    }

    static public void Method123() {
        return;
    }

    static public void Method321() {
        return;
    }
}
