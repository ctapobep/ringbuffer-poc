package poc.ringbuffer;

import com.lmax.disruptor.EventHandler;

/**
 * @author stanislav bashkyrtsev
 */
public class HexRepresentationCalculator implements EventHandler<CalculateNumbersEvent> {
    @Override
    public void onEvent(CalculateNumbersEvent event, long sequence, boolean endOfBatch) throws Exception {
        CalculatedNumber fibonacciValue = event.getFibonacciValue();
        CalculatedNumber factorialValue = event.getFactorialValue();
        fibonacciValue.setHexRepresentation(Long.toHexString(fibonacciValue.getValue()));
        factorialValue.setHexRepresentation(Long.toHexString(factorialValue.getValue()));
    }
}
