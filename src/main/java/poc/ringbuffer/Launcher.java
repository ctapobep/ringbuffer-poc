package poc.ringbuffer;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author stanislav bashkyrtsev
 */
public class Launcher {

    private static final int RING_SIZE = 10;

    public static void main(String[] args) {
        EventHandler<CalculateNumbersEvent> handler = new EventHandler<CalculateNumbersEvent>() {
            public void onEvent(CalculateNumbersEvent event, long sequence, boolean endOfBatch) throws Exception {
                event.setValue(new Random().nextInt(50));
            }
        };
        Disruptor<CalculateNumbersEvent> disruptor = new Disruptor<CalculateNumbersEvent>(CalculateNumbersEvent.EVENT_FACTORY, createExecutor(),
                new SingleThreadedClaimStrategy(RING_SIZE),
                new SleepingWaitStrategy());
        disruptor.handleEventsWith(handler);
        RingBuffer<CalculateNumbersEvent> ringBuffer = disruptor.start();


        // Publishers claim events in sequence
        long sequence = ringBuffer.next();
        CalculateNumbersEvent event = ringBuffer.get(sequence);

        event.setValue(1234);

        // make the event available to EventProcessors
        ringBuffer.publish(sequence);

        disruptor.publishEvent(new EventTranslator<CalculateNumbersEvent>() {
            @Override
            public void translateTo(CalculateNumbersEvent event, long sequence) {
                long originalValue = event.getValue();
                event.setValue(originalValue * 2);
                event.setFired(new Date());
            }
        });
    }

    private static Executor createExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
