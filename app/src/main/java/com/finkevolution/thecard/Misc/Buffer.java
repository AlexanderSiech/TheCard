package com.finkevolution.thecard.Misc;

/**
 * Created by alexander on 2017-06-28.
 */
import java.util.LinkedList;

/**
 * Created by Girondins on 30/01/17.
 */
public class Buffer<T> {
    private LinkedList<T> buffer = new LinkedList<T>();

    public synchronized void put(T element) {
        buffer.addLast(element);
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while(buffer.isEmpty()) {
            wait();
        }
        return buffer.removeFirst();
    }
}
