package lesson4;

import org.junit.Test;

public class Entrance {

    @Test
    public void test1(){
        MyStack1 stack1=new MyStack1();
        stack1.insert(1);
        stack1.insert(2);
        stack1.insert(3);
        stack1.insert(4);

        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
    }

    @Test
    public void test2(){
        MyStack2 stack2=new MyStack2();
        stack2.insert(new Unit(1));
        stack2.insert(new Unit(2));
        stack2.insert(new Unit(3));
        stack2.insert(new Unit(4));
        System.out.println(stack2.pop().x);
        System.out.println(stack2.pop().x);
        System.out.println(stack2.pop().x);
        System.out.println(stack2.pop().x);
        System.out.println(stack2.pop().x);
    }
}
