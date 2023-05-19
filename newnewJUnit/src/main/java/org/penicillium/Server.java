package org.penicillium;

import java.util.Random;

public class Server {
    Object user;
    public boolean SaveUser(int id) {
        return true;
    }

    public Server (String connectionString) {
    }

    public Object GetUser(int id) {
        return new Object();
    }

    private void UselessMethod() {
        int abc = 2 * 2 + 2;
    }

    public boolean Disconnect() {
        return true;
    }

    public boolean CheckUser(int id){
        return (new Random()).nextBoolean();
    }
}