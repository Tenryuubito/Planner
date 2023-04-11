package com.example.planner.interfaceCollection;

public interface IMessage {
    static void Console(String message) {
        System.out.println("==============================");
        System.out.println("Message:\n" + message);
        System.out.println("==============================");
    }
}
