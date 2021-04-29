package main.java;

import main.java.Fork;
import main.java.Philosopher;

import java.util.ArrayList;
import java.util.List;

public class Diner {
    private static int MAX_PHILOSOPHERS_EATING;
    public static int count = 0;

    public static void main(String[] args) {
        /*
            Create philosophers
         */
        List<Philosopher> philosophers = new ArrayList<>();
        philosophers.add(new Philosopher(0, "Platon"));
        philosophers.add(new Philosopher(1, "Socrates"));
        philosophers.add(new Philosopher(2, "Aristotel"));
        philosophers.add(new Philosopher(3, "Machiavelli"));
        philosophers.add(new Philosopher(4, "Confucius"));

        /*
            Create forks
        */
        Fork[] forks = new Fork[philosophers.size()];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

        MAX_PHILOSOPHERS_EATING = philosophers.size() / 2;


        /*
          Associate philosopher with forks
         */
        for (int i = 0; i < philosophers.size(); i++) {
            Fork left;
            Fork right;

            /*
            Following condition solves the deadlock problem
            Last philosopher takes RIGHT fork first, instead of LEFT like the others ->
            Either him or the first philosopher will always be able to take other fork and eat ->
            Then releasing his forks etc. -> no deadlock
             */

            if (i == philosophers.size() - 1) {
                right = forks[i];
                left = forks[(i+1) % forks.length];
            } else {
                left = forks[i];
                right = forks[(i+1) % forks.length];
            }
            philosophers.get(i).setLeftFork(left);
            philosophers.get(i).setRightFork(right);
            philosophers.get(i).start();
        }
    }
}
