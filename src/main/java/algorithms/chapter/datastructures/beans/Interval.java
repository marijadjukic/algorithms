package algorithms.chapter.datastructures.beans;

import java.math.BigDecimal;

public class Interval {

    private Number low;

    private Number high;

    public Interval(Number low, Number high) {
        this.low = low;
        this.high = high;
    }

    public BigDecimal getLow() {
        if(this.low != null) {
            return new BigDecimal(low.doubleValue());
        }
        return null;
    }

    public BigDecimal getHigh() {
        if(this.high != null) {
            return new BigDecimal(high.doubleValue());
        }
        return null;
    }
}
