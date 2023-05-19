package org.penicillium;

public class Main {
    public static void main(String[] args) {
        String  connectionString = "";
        ProxyServer serverProxy = new ProxyServer(new Server(connectionString));

        serverProxy.SaveUser(1);
        serverProxy.SaveUser(2);
        serverProxy.SaveUser(3);

//        serverProxy.GetUser(1);
//        serverProxy.GetUser(4);

        System.out.println(serverProxy.GetLogs());
    }
}