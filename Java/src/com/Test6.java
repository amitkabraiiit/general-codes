package com;

interface myint{
    int returnit(Test123 t ,int y);
}
class Test123{
    int x=0;
    public Test123(int x){
        this.x=x;
    }

    public int addNumbers(int y){
        return x+y;
    }
    public int subtractNumbers(int y){
        return x-y;
    }
    public static int isPrintable(Test123 x, int y){
        return  y;
    }
}

public class Test6{
    private static void myMethod(Test123 t,myint inf,int y){
        int x=inf.returnit(t, y);
        System.out.println(x+"");
    }
    public static void main(String[] args){
    	myMethod(new Test123(4),(x,y) -> Test123.isPrintable(new Test123(4), 8),7);
    	myMethod(new Test123(4),Test123:: isPrintable,7);
        myMethod(new Test123(4),Test123::addNumbers,7);
        myMethod(new Test123(4),Test123::subtractNumbers,7);
    }
}

