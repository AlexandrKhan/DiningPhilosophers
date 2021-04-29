package main.java;

import java.util.concurrent.Semaphore;

public class Fork {
    private final int id;

    public Fork(int id) {
        this.id = id;
    }

    public Semaphore semaphore = new Semaphore(1);

    public void take() throws InterruptedException {
        semaphore.acquire();
    }

    public void putdown() throws InterruptedException {
        semaphore.release();
    }

    public int getId() {
        return id;
    }
}
