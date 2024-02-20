package com.dangerousteam.doomee;

public class Test {
    public static void main(String[] args) {
        Doomee.load("output/", new String[]{"https://github.com/bexonpak/Doomee/tree/master"})
                .tasks(3)
                .start();
    }
}
