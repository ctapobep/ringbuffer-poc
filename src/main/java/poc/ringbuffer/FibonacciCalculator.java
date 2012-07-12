package poc.ringbuffer;

import com.lmax.disruptor.EventHandler;

/**
 * @author stanislav bashkyrtsev
 */
public class FibonacciCalculator implements EventHandler<CalculateNumbersEvent> {

    @Override
    public void onEvent(CalculateNumbersEvent event, long sequence, boolean endOfBatch) throws Exception {
        long value = event.getValue();
        long result = fibonacci(value);
        event.setFibonacciValue(new CalculatedNumber(result));
    }

    private long fibonacci(long n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
