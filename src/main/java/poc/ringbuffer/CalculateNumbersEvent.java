package poc.ringbuffer;

import com.lmax.disruptor.EventFactory;

import java.util.Date;

public final class CalculateNumbersEvent {
    private Date fired;
    private volatile long value;
    private CalculatedNumber fibonacciValue;
    private CalculatedNumber factorialValue;

    public Date getFired() {
        return fired;
    }

    public void setFired(Date fired) {
        this.fired = fired;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public CalculatedNumber getFibonacciValue() {
        return fibonacciValue;
    }

    public void setFibonacciValue(CalculatedNumber fibonacciValue) {
        this.fibonacciValue = fibonacciValue;
    }

    public CalculatedNumber getFactorialValue() {
        return factorialValue;
    }

    public void setFactorialValue(CalculatedNumber factorialValue) {
        this.factorialValue = factorialValue;
    }

    public final static EventFactory<CalculateNumbersEvent> EVENT_FACTORY = new EventFactory<CalculateNumbersEvent>() {
        public CalculateNumbersEvent newInstance() {
            return new CalculateNumbersEvent();
        }
    };

    @Override
    public String toString() {
        return "CalculateNumbersEvent{" +
                "fired=" + fired +
                ", value=" + value +
                ", fibonacciValue=" + fibonacciValue +
                ", factorialValue=" + factorialValue +
                '}';
    }
}