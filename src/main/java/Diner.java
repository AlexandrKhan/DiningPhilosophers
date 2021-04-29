package main.java;

import main.java.Fork;
import main.java.Philosopher;

import java.util.ArrayList;
import java.util.List;

public class Diner {
    public static void main(String[] args) {
        List<Philosopher> philosophers = new ArrayList<>();
        philosophers.add(new Philosopher(0, "Platon"));
        philosophers.add(new Philosopher(1, "Socrates"));
        philosophers.add(new Philosopher(2, "Aristotel"));
        philosophers.add(new Philosopher(3, "Machiavelli"));
        philosophers.add(new Philosopher(4, "Confucius"));

        Fork[] forks = new Fork[philosophers.size()];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

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
                right = forks[(i + 1) % forks.length];
            }

            /*
            Deadlock code
             */

//            left = forks[i];
//            right = forks[(i + 1) % forks.length];

            philosophers.get(i).setLeftFork(left);
            philosophers.get(i).setRightFork(right);
            philosophers.get(i).start();
        }
    }
}
