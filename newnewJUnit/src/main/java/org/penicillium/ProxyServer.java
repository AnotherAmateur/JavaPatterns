package org.penicillium;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProxyServer implements IProxyServer {
    static private enum Responses {Success, Error}
    public Server server;

    public ProxyServer(Server server) {
        this.server = server;
    }

    private class Logs {
        private Date date;
        private String info;
        private Responses response;

        public Logs(Date date, String info, Responses response) {
            this.date = date;
            this.info = info;
            this.response = response;
        }

        @Override
        public String toString() {
            return "date: " + date + ", info: " + info + ", response: " + response;
        }
    }

    private List<Logs> logsList = new ArrayList<>();

    private void WriteDown(Logs logs){
        logsList.add(logs);

        try(FileWriter writer = new FileWriter("logs.csv", true))
        {
            writer.write(logs.toString() + '\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public String GetLogs() {
        return logsList.stream().map(Logs::toString)
                .collect(Collectors.joining("\n"));
    }

    //@Override
    public boolean SaveUser(int id) {
        boolean result = server.SaveUser(id);
        Responses response = (result == true) ? Responses.Success : Responses.Error;
        WriteDown(new Logs(new Date(), "Called SaveUser method", response));

        return result;
    }

    //@Override
    public Object GetUser(int id) throws Exception {
        Object result = server.GetUser(id);
        Responses response = (result != null) ? Responses.Success : Responses.Error;
        WriteDown(new Logs(new Date(), "Called GetUser method", response));

        if (result == null){
            throw new Exception("Can`t get such a user");
        }
        return result;
    }

    public boolean IfUserExists(int id){
        return server.CheckUser(id);
    }
}
