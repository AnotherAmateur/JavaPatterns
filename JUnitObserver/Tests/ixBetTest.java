import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ixBetTest {
    private ixBet bet;
    private Sheeple observerMock = mock(Sheeple.class);
    private Sheeple observer;

    @BeforeEach
    public void setUp() {
        bet = new ixBet(1);
        observer = new Sheeple(2);
        bet.RegisterObserver(observer);
    }

    @Test
    public void testRegisterObserver() {
        Sheeple newObserver = new Sheeple(132);
        bet.RegisterObserver(newObserver);

        List<IObserver> expectedObservers = new ArrayList<>();
        expectedObservers.add(observer);
        expectedObservers.add(newObserver);

        assertTrue(expectedObservers.containsAll(bet.getObservers())
                && bet.getObservers().containsAll(expectedObservers));
    }

    @Test
    public void testRemoveObserver() {
        bet.RemoveObserver(observer);
        assertEquals(new ArrayList<IObserver>(), bet.getObservers());
    }

    @Test
    public void testNotifyObservers() {
        bet.RegisterObserver(observerMock);
        bet.PlayBet();
        verify(observerMock, times(1)).Update(Mockito.any());
    }

    @Test
    public void testThrowsException() {
        Sheeple newObserver = null;
        bet.RegisterObserver(newObserver);

        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            bet.PlayBet();
        });

        System.out.println(thrown.getMessage());
        assertNotNull(thrown.getMessage());
    }
}