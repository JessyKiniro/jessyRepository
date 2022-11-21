# JSON

## 概念

JSON(JavaScript Object Notation)是一个轻量级的数据交换格式

JSON 用于前后端数据交互

## 类库选择

JAVA 中没有内置 JSON 解析，因此使用 JSON 需要借助第三方类库
常用类库：Gson、FastJson、Jackson

## 语法

1. 基本规则
   数据是由键值对构成的，多个键值对由逗号分开
   > {}表示对象
   > []表示数组
   > ""表示属性或者值
   > :表示后者是前者的值

## 分类

1. 基本类型：JSONObject

```JAVA
{
    "name": "张三",
    "age": 18,
    "sex":"男"
}//和一个JAVA对象对等 字符串需要“”
```

2. 数组类型：JSONArray

```JAVA
[
        {
            "name": "张三",
            "age": 18,
            "sex": "男"
        },
        {
            "name": "李四",
            "age": 19,
            "sex":"男"
        }
]
```

3. 对象嵌套

```JAVA

{
    "name": "teacher",
    "computer": {
        "CPU": "intel7",
        "disk": "512G"
    },
    "students": [
        {
            "name": "张三",
            "age": 18,
            "sex": "男"
        },
        {
            "name": "李四",
            "age": 19,
            "sex": "男"
        }//可以看成一个list
    ]
}
```

## JSON 和 Java 对象之间的转换

```JAVA
package cn.entity;

public class Computer {

    public String cpu;
    public String disk;


    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", disk='" + disk + '\'' +
                '}';
    }
}


package cn.entity;

import java.util.ArrayList;

public class School {

    public String name;
    public Computer computer;
    public ArrayList<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", computer=" + computer +
                ", students=" + students +
                '}';
    }
}



package cn.entity;

public class Student {

    public String name;
    public int age;
    public String sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}


```

**!!!JAVA 属性名与 JSON 中属性名一定要一致!!!**

1. JDK 方式

```JAVA
package cn.entity;


import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JDK_JSON {
    public static String jsonString = "{\n" +
            "    \"name\": \"teacher\",\n" +
            "    \"computer\": {\n" +
            "        \"CPU\": \"intel7\",\n" +
            "        \"disk\": \"512G\"\n" +
            "    },\n" +
            "    \"students\": [\n" +
            "        {\n" +
            "            \"name\": \"张三\",\n" +
            "            \"age\": 18,\n" +
            "            \"sex\": \"男\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"李四\",\n" +
            "            \"age\": 19,\n" +
            "            \"sex\": \"男\"\n" +
            "        }\n" +
            "    ]\n" +
            "}\n";

    public static void main(String[] args) {

        //JSON字符串转JAVA 对象

        School school = new School();

        JSONObject jsonObject = new JSONObject(jsonString);

        String name = (String) jsonObject.get("name");
        school.setName(name);

        JSONObject computer = jsonObject.getJSONObject("computer");
        Computer computer1 = new Computer();
        String cpu = (String) computer.get("CPU");
        String disk = (String) computer.get("disk");

        computer1.setCpu(cpu);
        computer1.setDisk(disk);

        school.setComputer(computer1);

        JSONArray students = jsonObject.getJSONArray("students");

        ArrayList<Student> studentList = new ArrayList<>();
        for (int i = 0; i < students.length(); i++) {
            JSONObject jsonObject1 = students.getJSONObject(i);
            Student student = new Student();
            String name1 = (String) jsonObject1.get("name");
            String sex = (String) jsonObject1.get("sex");
            int age = (int) jsonObject1.get("age");
            student.setName(name1);
            student.setAge(age);
            student.setSex(sex);
            studentList.add(student);
        }
        school.setStudents(studentList);
        System.out.println(school);

        //java对象转JSON

        String s=jsonObject.toString();
        System.out.println(s);


    }

}
```

2. Gson 方式

```JAVA

package cn.entity;

import com.google.gson.Gson;

public class Gson_JSON {

    public static String jsonString = "{\n" +
            "    \"name\": \"teacher\",\n" +
            "    \"computer\": {\n" +
            "        \"CPU\": \"intel7\",\n" +
            "        \"disk\": \"512G\"\n" +
            "    },\n" +
            "    \"students\": [\n" +
            "        {\n" +
            "            \"name\": \"张三\",\n" +
            "            \"age\": 18,\n" +
            "            \"sex\": \"男\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"李四\",\n" +
            "            \"age\": 19,\n" +
            "            \"sex\": \"男\"\n" +
            "        }\n" +
            "    ]\n" +
            "}\n";

    public static void main(String[] args) {
        //json字符串转Java对象
        Gson gson=new Gson();
        School school = gson.fromJson(jsonString, School.class);
        System.out.println(school);

        //java对象转json字符串
        String s=gson.toJson(school);
        System.out.println(s);


    }

}


```

3. fastjson 方式

```JAVA

package cn.entity;

import com.alibaba.fastjson.JSON;

import java.sql.SQLSyntaxErrorException;

public class FastJson_JSON {
    public static String jsonString = "{\n" +
            "    \"name\": \"teacher\",\n" +
            "    \"computer\": {\n" +
            "        \"cpu\": \"intel7\",\n" +
            "        \"disk\": \"512G\"\n" +
            "    },\n" +
            "    \"students\": [\n" +
            "        {\n" +
            "            \"name\": \"张三\",\n" +
            "            \"age\": 18,\n" +
            "            \"sex\": \"男\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"李四\",\n" +
            "            \"age\": 19,\n" +
            "            \"sex\": \"男\"\n" +
            "        }\n" +
            "    ]\n" +
            "}\n";

    public static void main(String[] args) {

        //JSON字符串转JAVA对象
        School school = JSON.parseObject(jsonString, School.class);
        System.out.println(school);

        //JAVA对象转JSON 字符串
        String s = JSON.toJSONString(school);
        System.out.println(s);


    }
}

```

$\color{red}{fastjson要求JSON串中属性名一定要是小写}$
