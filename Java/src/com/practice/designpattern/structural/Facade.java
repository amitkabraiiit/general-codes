package com.practice.designpattern.structural;

class CPU {
    public void processData() { }
}
 
class Memory {
    public void load() { }
}
 
class HardDrive {
    public void readdata() { }
}
 
/* Facade */
class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
 
    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
 
    public void run() {
        cpu.processData();
        memory.load();
        hardDrive.readdata();
    }
}
 
 
class Facade {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.run();
    }
}