package org.penicillium;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class ServerProxyTest {
    @Mock
    static Server serverMock;
    @InjectMocks
    ProxyServer proxyServer;

    @BeforeAll
    static void setUpMock() {
        serverMock = Mockito.mock(Server.class);
    }

    @BeforeEach
    void setUp() {
        proxyServer = new ProxyServer(serverMock);
    }

    @Test
    void saveUserTrue() {
        int userId = 14;
        when(serverMock.SaveUser(anyInt())).thenReturn(true);
        boolean result = proxyServer.SaveUser(userId);

        verify(serverMock).SaveUser(userId);

        assertTrue(result);
    }

    @Test
    void saveUserFalse() {
        int userId = 4;
        when(serverMock.SaveUser(userId)).thenReturn(false);
        boolean result = proxyServer.SaveUser(userId);

        verify(serverMock).SaveUser(userId);

        assertFalse(result);
    }

    @Test
    void checkUserTrue() {
        int userId = 14;
        when(serverMock.CheckUser(anyInt())).thenReturn(true);
        boolean result = proxyServer.IfUserExists(userId);

        verify(serverMock).CheckUser(userId);

        assertTrue(result);
    }

    @Test
    void checkUserFalse() {
        int userId = 23;
        when(serverMock.CheckUser(userId)).thenReturn(false);
        boolean result = proxyServer.IfUserExists(userId);

        verify(serverMock).CheckUser(userId);

        assertFalse(result);
    }

    @Test
    void getUser() {
        int userId = 34;
        Object expectedUser = "some user";
        when(serverMock.GetUser(userId)).thenReturn(expectedUser);
        Object resultUser = null;
        try {
            resultUser = proxyServer.GetUser(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        verify(serverMock).GetUser(userId);

        assertEquals((String) expectedUser, (String) resultUser);
        assertNotNull(proxyServer.GetLogs());
    }

    @Test
    void getUserError() throws Exception {
        int userId = 134;
        when(serverMock.GetUser(anyInt())).thenReturn(null);

        Throwable thrown = assertThrows(Exception.class, () -> {
            proxyServer.GetUser(userId);
        });

        assertNotNull(thrown.getMessage());
    }
}