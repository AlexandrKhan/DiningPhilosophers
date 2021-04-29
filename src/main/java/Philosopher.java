package main.java;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
    private final int philosopherId;
    private final String philosopherName;
    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(int philosopherId, String philosopherName) {
        this.philosopherId = philosopherId;
        this.philosopherName = philosopherName;
    }

    public void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    public void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        System.out.println(philosopherName + " is thinking");
        Thread.sleep(ThreadLocalRandom.current().nextInt(0, 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("                                " + philosopherName.toUpperCase() + " IS EATING");
        Thread.sleep(ThreadLocalRandom.current().nextInt(0, 1000));
    }

    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    @Override
    public void run() {
        while(true) {
            try {
                think();
                leftFork.take();
                System.out.println(philosopherName + " took left fork");
                Thread.sleep(5000);
                rightFork.take();
                System.out.println(philosopherName + " took right fork");
                eat();
                leftFork.putdown();
                System.out.println(philosopherName + " put down left fork");
                rightFork.putdown();
                System.out.println(philosopherName + " put down right fork");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Philosopher {" +
                "philosopherId=" + philosopherId +
                ", philosopherName='" + philosopherName + '\'' +
                ", leftFork=" + leftFork.getId() +
                ", rightFork=" + rightFork.getId() +
                '}';
    }
}
