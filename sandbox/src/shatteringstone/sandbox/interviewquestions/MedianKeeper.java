package shatteringstone.sandbox.interviewquestions;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianKeeper {

    private final PriorityQueue<Integer> smallValues = new PriorityQueue<Integer>(Collections.reverseOrder());
    private final PriorityQueue<Integer> bigValues = new PriorityQueue<Integer>();

    public int getMedian() {
        if (this.bigValues.size() == this.smallValues.size()) {
            return this.smallValues.peek();
        } else if (this.smallValues.size() > this.bigValues.size()) {
            return this.smallValues.peek();
        } else {
            return this.bigValues.peek();
        }
    }

    public void add(final int i) {

        // Add to appropriate heap
        if ((this.smallValues.size() == 0) || (i < this.smallValues.peek())) {
            this.smallValues.add(i);
        } else {
            this.bigValues.add(i);
        }

        // Re-balance
        if ((this.smallValues.size() - this.bigValues.size()) > 1) {
            this.bigValues.add(this.smallValues.remove());
        } else if ((this.bigValues.size() - this.smallValues.size()) > 1) {
            this.smallValues.add(this.bigValues.remove());
        }
    }

}
