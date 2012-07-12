package poc.ringbuffer;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author stanislav bashkyrtsev
 */
public class Launcher {

    private static final int RING_SIZE = 16;

    public static void main(String[] args) {
        Disruptor<CalculateNumbersEvent> disruptor = new Disruptor<CalculateNumbersEvent>(CalculateNumbersEvent.EVENT_FACTORY, createExecutor(),
                new SingleThreadedClaimStrategy(RING_SIZE),
                new SleepingWaitStrategy());
        disruptor.handleEventsWith(new FactorialCalculator()).and(new FibonacciCalculator())
                .then(new HexRepresentationCalculator()).and(new BinaryRepresentationCalculator())
                .then(new EventOutputHandler());
        RingBuffer<CalculateNumbersEvent> ringBuffer = disruptor.start();


        // Publishers claim events in sequence
        long sequence = ringBuffer.next();
        CalculateNumbersEvent event = ringBuffer.get(sequence);

        event.setValue(20);

        // make the event available to EventProcessors
        ringBuffer.publish(sequence);

        disruptor.publishEvent(new EventTranslator<CalculateNumbersEvent>() {
            @Override
            public void translateTo(CalculateNumbersEvent event, long sequence) {
                event.setFired(new Date());
            }
        });
    }

    private static Executor createExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
