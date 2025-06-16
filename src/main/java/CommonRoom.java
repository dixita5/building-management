public class CommonRoom extends Room {
    private CommonRoomType type;

    public CommonRoom(String id, CommonRoomType type) {
        super(id);
        this.type = type;
    }

    public CommonRoomType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("CommonRoom %s (Type: %s), Temp=%.2f, Heating=%b, Cooling=%b",
                id, type, temperature, heatingEnabled, coolingEnabled);
    }
}