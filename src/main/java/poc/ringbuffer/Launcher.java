package poc.ringbuffer;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author stanislav bashkyrtsev
 */
public class Launcher {

    private static final int RING_SIZE = 10;

    public static void main(String[] args) {
        final EventHandler<ValueEvent> handler = new EventHandler<ValueEvent>() {
            public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception {
                // process a new event.
            }
        };
        Disruptor<ValueEvent> disruptor =
                new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, createExecutor(),
                        new SingleThreadedClaimStrategy(RING_SIZE),
                        new SleepingWaitStrategy());
        disruptor.handleEventsWith(handler);
        RingBuffer<ValueEvent> ringBuffer = disruptor.start();


        // Publishers claim events in sequence
        long sequence = ringBuffer.next();
        ValueEvent event = ringBuffer.get(sequence);

        event.setValue(1234); // this could be more complex with multiple fields

// make the event available to EventProcessors
        ringBuffer.publish(sequence);

        disruptor.publishEvent(eventTranslator);
    }

    private static Executor createExecutor(){
        return Executors.newSingleThreadExecutor();
    }
}
