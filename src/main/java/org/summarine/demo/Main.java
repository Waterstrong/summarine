package org.summarine.demo;

import org.summarine.core.container.DefaultContainer;

public class Main {

    public static void main(String[] args) {
        DefaultContainer container = new DefaultContainer();
        Worker worker = (Worker) container.getBean("worker");
        System.out.println(worker.weed());

        System.out.println("\n=========Split Line=========\n");

        AOPTester tester = (AOPTester) container.getBean("aopTester");
        tester.action();

    }
}
