package com.billion_dollor_company.npciServer.common.util;

public class MessagePrinter {

    public static void printMessage(String serverName, String methodType, Object reqObject) {
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------------" + serverName + "----------------------------------------------------");
        System.out.println("API called : " + methodType);
        System.out.println("The object is : " + reqObject.toString());
    }

}
