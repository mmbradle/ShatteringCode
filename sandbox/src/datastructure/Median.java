/****************************************************************************
 *
 * FPX, LLC Confidential and Proprietary
 *
 * This unpublished work contains valuable confidential and proprietary
 * information. Disclosure, use or reproduction without the written
 * authorization of FPX, LLC is prohibited.
 * This unpublished work by FPX, LLC is protected by the laws of the
 * United States and other countries. If publication of the work should
 * occur the following notice shall apply:
 *
 * Copyright (c) 1983-2014 by FPX, LLC.
 * All rights reserved.
 *
 ***************************************************************************/
package datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Median {
    private final RandomNumbers randomNumbers;

    private final PriorityQueue<Integer> littleNumbersMaxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    private final PriorityQueue<Integer> bigNumbersMinHeap = new PriorityQueue<Integer>();

    public Median(final RandomNumbers randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public int getMedian() {
        if (this.bigNumbersMinHeap.size() == this.littleNumbersMaxHeap.size()) {
            return this.littleNumbersMaxHeap.peek();
        } else if (this.littleNumbersMaxHeap.size() > this.bigNumbersMinHeap.size()) {
            return this.littleNumbersMaxHeap.peek();
        } else {
            return this.bigNumbersMinHeap.peek();
        }
    }

    public void add(final int i) {
        this.randomNumbers.add(i);

        if ((this.littleNumbersMaxHeap.size() == 0) || (i < this.littleNumbersMaxHeap.peek())) {
            // add to small
            this.littleNumbersMaxHeap.add(i);
        } else {
            // add to big
            this.bigNumbersMinHeap.add(i);
        }
        rebalanceHeaps();
    }

    private void rebalanceHeaps() {
        int littleSize = this.littleNumbersMaxHeap.size();
        int bigSize = this.bigNumbersMinHeap.size();
        assert (Math.abs(bigSize - littleSize) <= 2) : "Failed precondition";
        if ((littleSize - bigSize) > 1) {
            this.bigNumbersMinHeap.add(this.littleNumbersMaxHeap.remove());
        } else if ((bigSize - littleSize) > 1) {
            this.littleNumbersMaxHeap.add(this.bigNumbersMinHeap.remove());
        }
        assert (Math.abs(bigSize - littleSize) <= 1) : "Failed postcondition";
    }

}

class RandomNumbers {
    private final List<Integer> numbers = new ArrayList<Integer>();
    private final Random r = new Random();
    private final int Low = 10;
    private final int High = 100;

    public void add(final int i) {
        this.numbers.add(Integer.valueOf(i));
    }

    public void add() {
        int i = this.r.nextInt(this.High - this.Low) + this.Low;
        this.numbers.add(Integer.valueOf(i));
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }
}
