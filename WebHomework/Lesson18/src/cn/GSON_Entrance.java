package cn;

import com.google.gson.Gson;

public class GSON_Entrance {

    private static String jsonString="{\n" +
            "    \"addresses\": [\n" +
            "        \"广东省中山市火炬开发区在伟盛路附近距离顷九327米\", \n" +
            "        \"广东省中山市火炬开发区在伟盛路附近距离顷九327米\"\n" +
            "    ], \n" +
            "    \"points\": [\n" +
            "        {\n" +
            "            \"x\": 113.456021761023, \n" +
            "            \"y\": 22.5737174302826\n" +
            "        }, \n" +
            "        {\n" +
            "            \"x\": 113.456021761023, \n" +
            "            \"y\": 22.5737174302826\n" +
            "        }\n" +
            "    ], \n" +
            "    \"places\": [\n" +
            "        [\n" +
            "            \"测试区域\", \n" +
            "            \"su\"\n" +
            "        ], \n" +
            "        [\n" +
            "            \"测试区域\", \n" +
            "            \"su\"\n" +
            "        ]\n" +
            "    ]\n" +
            "}\n";

    public static void main(String[] args) {

        //JSON转java对象
        Gson gson=new Gson();
        Location location=gson.fromJson(jsonString,Location.class);
        System.out.println(location);
        //java对象转json字符串
        String s=gson.toJson(location);
        System.out.println(s);
    }

}
