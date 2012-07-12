package poc.ringbuffer;

import com.lmax.disruptor.EventHandler;

/**
 * @author stanislav bashkyrtsev
 */
public class FactorialCalculator implements EventHandler<CalculateNumbersEvent> {
    @Override
    public void onEvent(CalculateNumbersEvent event, long sequence, boolean endOfBatch) throws Exception {
        long value = event.getValue();
        long result = factorial(value);
        event.setFactorialValue(new CalculatedNumber(result));
    }

    private long factorial(long n) {
        long result = 1;
        for(int i = 2; i < n; i++){
            result *= i;
        }
        return result;
    }
}
