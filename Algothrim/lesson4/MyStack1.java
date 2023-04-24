package lesson4;

import java.util.ArrayList;

public class MyStack1 {
   private int[] data;
   private int length=0;

   public MyStack1(){
       data=new int[5];
   }
   private void expand(){
       if(length>= data.length/2){
           int[] temp=data;
           data=new int[data.length*2];
           for(int i=0;i<length;i++){
               data[i]=temp[i];
           }
       }
   }
   public void insert(int x){
      expand();
      data[length++]=x;
   }
   public int pop(){
      if(length<1){
          throw new ArrayIndexOutOfBoundsException();
      }
      return data[--length];
   }
   public int size(){
       return length;
   }
}
