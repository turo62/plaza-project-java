package com.codecool.plaza.cmd;

public class Main {

    public static void main(String[] args) {
        try {
            new CmdProgram(args).run();
        } catch (Exception ex) {
            ex.getMessage();
        }
        
    
    }
}
