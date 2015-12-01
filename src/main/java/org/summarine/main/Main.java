package org.summarine.main;

import org.summarine.bean.Worker;
import org.summarine.core.container.DefaultContainer;

public class Main {
    public static void main(String[] args) {
        Worker worker = (Worker) new DefaultContainer().getBean("worker");
        System.out.println(worker.weed());
    }
}
