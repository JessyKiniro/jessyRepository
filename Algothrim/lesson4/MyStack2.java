package lesson4;

import java.util.Stack;

public class MyStack2 {
    Unit firstUnit;
    int size=0;

    public void insert(Unit unit){
        if (size != 0) {
            unit.last = firstUnit;
        }
        firstUnit = unit;
        size++;

    }
    public Unit pop(){
        if(size<1){
            throw new RuntimeException();
        }else{
            Unit unit=firstUnit;
            firstUnit=unit.last;
            size--;
            return unit;
        }

    }

}
