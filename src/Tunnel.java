import java.io.Serializable;
import java.util.List;

public class Tunnel extends Room implements Serializable {
    private final Room from;
    private final Room to;

    public Tunnel(int x, int y, List<Integer> doors, boolean visible, Room from, Room to) {
        super(x, y, doors, visible);
        this.from = from;
        this.to = to;
    }

    public Room getFrom() {
        return from;
    }

    public Room getTo() {
        return to;
    }
}
