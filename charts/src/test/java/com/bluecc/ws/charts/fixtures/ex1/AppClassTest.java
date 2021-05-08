package com.bluecc.ws.charts.fixtures.ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppClassTest {

    @Test
    void checkString() {
//        String arg="hello world";
        String arg="00011";

        AppClass appobject = new AppClass();
        if (!appobject.CheckString(arg)) {
            System.out.println("not acceptable.");
        } else {
            System.out.println("acceptable.");
        }
    }
}