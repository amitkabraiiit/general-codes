package com.practice.hackerrank;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModifiedFibonacci {
    static BigInteger a , b ;
    static Map<BigInteger,BigInteger> map = new HashMap<>();
    static BigInteger modifiedFibonacci(BigInteger p){
        if(map.containsKey(p))return map.get(p);
        BigInteger x = modifiedFibonacci(p.subtract(new BigInteger(1+"")));
        BigInteger y = modifiedFibonacci(p.subtract(new BigInteger(2+"")));
        BigInteger value = x.multiply(x).add(y);
        map.put(p,value);
        return value;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        a = new BigInteger(Integer.parseInt(str.split(" ")[0])+"");
        b = new BigInteger(Integer.parseInt(str.split(" ")[1])+"");
        map.put(BigInteger.valueOf(1),a);
        map.put(new BigInteger(2+""),b);
        BigInteger n = new BigInteger(Integer.parseInt(str.split(" ")[2])+"");
        System.out.println(modifiedFibonacci(n));
    }

}

