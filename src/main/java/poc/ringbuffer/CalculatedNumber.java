package poc.ringbuffer;

/**
 * @author stanislav bashkyrtsev
 */
public class CalculatedNumber {
    private final long value;
    private String binaryRepresentation;
    private String hexRepresentation;


    public CalculatedNumber(long value) {
        this.value = value;
    }

    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    public void setBinaryRepresentation(String binaryRepresentation) {
        this.binaryRepresentation = binaryRepresentation;
    }

    public String getHexRepresentation() {
        return hexRepresentation;
    }

    public void setHexRepresentation(String hexRepresentation) {
        this.hexRepresentation = hexRepresentation;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CalculatedNumber{" +
                "binaryRepresentation='" + binaryRepresentation + '\'' +
                ", hexRepresentation='" + hexRepresentation + '\'' +
                '}';
    }
}
