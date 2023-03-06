// Демонстрация паттерна Proxy

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Прокси логгирует запрос к серверу и ответ от него
        ServerProxy server = new ServerProxy();

        server.SaveUser(1);
        server.SaveUser(2);
        server.SaveUser(3);

        server.GetUser(1);
        server.GetUser(4);

        System.out.println(server.GetLogs());
    }
}

abstract class Server {
    static public boolean SaveUser(int id) {
        return true;
    }

    static public Object GetUser(int id) {
        return new Object();
    }

    private void UselessMethod() {
        int abc = 2 * 2 + 2;
    }
}

interface IServerProxy {
    public boolean SaveUser(int id);

    public Object GetUser(int id);
}

class ServerProxy implements IServerProxy {
    static private enum Responses {Success, Error}

    private class Logs {
        public Date date;
        public String info;
        public Responses response;

        Logs(Date date, String info, Responses response) {
            this.date = date;
            this.info = info;
            this.response = response;
        }

        @Override
        public String toString() {
            return "date: " + date + ", info: " + info + ", response: " + response;
        }
    }

    private List<Logs> logs = new ArrayList<>();

    public String GetLogs() {
        return logs.stream().map(Logs::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean SaveUser(int id) {
        boolean result = Server.SaveUser(id);
        Responses response = (result == true) ? Responses.Success : Responses.Error;
        logs.add(new Logs(new Date(), "Called SaveUser method", response));

        return result;
    }

    @Override
    public Object GetUser(int id) {
        Object result = Server.GetUser(id);
        Responses response = (result != null) ? Responses.Success : Responses.Error;
        logs.add(new Logs(new Date(), "Called GetUser method", response));

        return result;
    }
}