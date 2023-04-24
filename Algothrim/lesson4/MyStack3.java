package lesson4;

public class MyStack3 {
    public boolean testChars(String str){
        if(str==null&&str.length()==0){
            return false;
        }
        char[] chars = str.toCharArray();
        MyStack1 stack=new MyStack1();
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='('||chars[i]=='{'||chars[i]=='[')
                stack.insert(chars[i]);
            if(chars[i]==')'||chars[i]=='}'||chars[i]==']'){
                if(stack.size()>0){
                    char val= (char) stack.pop();
                    if(chars[i]==')'&&val!='('){
                        return false;
                    }
                    if(chars[i]=='}'&&val!='{'){
                        return false;
                    }
                    if(chars[i]==']'&&val!='['){
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        if(stack.size()!=0){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MyStack3 myStack3=new MyStack3();
        boolean b = myStack3.testChars("45454(){454]");
        System.out.println(b);
    }
}
