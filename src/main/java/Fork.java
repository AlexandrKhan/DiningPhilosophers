package main.java;

import java.util.concurrent.Semaphore;

public class Fork {
    private int id;

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

    public boolean isFree() {
        return semaphore.availablePermits() > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
