public class Sheeple implements IObserver{
    private int id;

    public Sheeple(int id) {
        this.id = id;
    }

    @Override
    public void Update(Object info) {
        try {
            String isWinner = ((String[]) info)[0];
            String betId = ((String[]) info)[1];
            System.out.println("Player id: " + id + " " + isWinner + " bet " + betId);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Ошибка индексирования");
        } catch (ClassCastException exception) {
            System.out.println("Ошибка преобразования типов");
        }
    }
}
