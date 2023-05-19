package org.penicillium;

public interface IProxyServer {
    public boolean SaveUser(int id);

    public Object GetUser(int id) throws Exception;
}