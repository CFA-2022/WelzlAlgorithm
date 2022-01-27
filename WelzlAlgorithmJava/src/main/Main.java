package main;

import algorithms.AlgoService;

public class Main {

    public static AlgoService algoService;

    public static void main(String[] args) {
        algoService = new AlgoService();

        long t = System.currentTimeMillis();
        algoService.baseTest_AlgoWelzl();
        t = (System.currentTimeMillis() - t)/1000;
        System.out.println("Base Test for Algorithm Welzl is finished after " + t + " seconds");

        t = System.currentTimeMillis();
        algoService.baseTest_AlgoNaif();
        t = (System.currentTimeMillis() - t)/1000;
        System.out.println("Base Test for Algorithm Naif is finished after " + t + " seconds");
    }
}
