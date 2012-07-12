package poc.ringbuffer;

import com.lmax.disruptor.EventHandler;

/**
 * @author stanislav bashkyrtsev
 */
public class BinaryRepresentationCalculator implements EventHandler<CalculateNumbersEvent> {
    @Override
    public void onEvent(CalculateNumbersEvent event, long sequence, boolean endOfBatch) throws Exception {
        CalculatedNumber fibonacciValue = event.getFibonacciValue();
        CalculatedNumber factorialValue = event.getFactorialValue();
        fibonacciValue.setBinaryRepresentation(Long.toBinaryString(fibonacciValue.getValue()));
        factorialValue.setBinaryRepresentation(Long.toBinaryString(fibonacciValue.getValue()));
    }
}
