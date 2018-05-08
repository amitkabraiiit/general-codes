package com.practice.sorting;

import java.util.Arrays;

public class CountSort { 
    void count_sort(int a[],int b[],int k){ // O(n+d) , stable sort , extra space O(K)

    	// input array : [1,4,3,1,2,3,0,3]
    	
        // c[i] are all zeros.
    	// c array in count sort is count array.
        int[] c=new int[k]; 
        
       
        // c elements are put in their positions 
        // so we know how many of each are there.
       for(int j=0;j<a.length;j++){ 
           c[a[j]]=c[a[j]]+1; 
       } 
       // c[1, 2, 1, 3, 1, 0, 0, 0, 0, 0] 
       
       // count are added to their previous positions. 
       // by adding we know that how many elements should be done till this index 
       for(int i=1;i<k;i++){ 
           c[i]=c[i]+c[i-1]; 
       } 
       // c[1, 3, 4, 7, 8, 8, 8, 8, 8, 8]      

       
       // Now for each element i in a we will find out where should be its position in new array b.
       // We can easy find out by just going to the ith elements position in c.
       // There we can find out including i how many should be put till this position.
       // Ex: a[7] is 3, so in order to find where to put 3, go to its position, that is c[3] , which is 7.
       // Now including 3 there are 7 element, so position wise put it at b[6] and decrease count for 3 in c.
       for(int j=(a.length-1);j>=0;j--){ 
           b[c[a[j]]-1]=a[j]; 
           c[a[j]]=c[a[j]]-1; 
       } 

       // c[0, 1, 3, 4, 7, 8, 8, 8, 8, 8]
       // b[0, 1, 1, 2, 3, 3, 3, 4]


    } 
    public static void main(String args[]){ 
        CountSort ct=new CountSort(); 
        int []a={1,4,3,1,2,3,0,3};
        System.out.println(Arrays.toString(a));
        int []b=new int[8]; 
        int k=10; // range of input that is going to be provided, ie. numbers will be between 0-9
        try{ 
        ct.count_sort(a,b,k); 
        }catch(Exception e){}; 
        System.out.println("Sorted Array is:"); 
        System.out.println(Arrays.toString(b));
    } 
} 