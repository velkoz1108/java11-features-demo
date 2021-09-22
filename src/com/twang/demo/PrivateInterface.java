package com.twang.demo;

public interface PrivateInterface {
    public void sayHello();

    /**
     * Private interface methods are supported. This support allows nonabstract methods of an interface to share code between them.
     *
     * @param name_a
     * @return
     */
    private String sayHello(String name_a) {
        return "null";
    }
}
