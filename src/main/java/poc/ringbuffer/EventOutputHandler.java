package poc.ringbuffer;

import com.lmax.disruptor.EventHandler;

/**
 * @author stanislav bashkyrtsev
 */
public class EventOutputHandler implements EventHandler<CalculateNumbersEvent> {
    @Override
    public void onEvent(CalculateNumbersEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.toString());
    }
}
