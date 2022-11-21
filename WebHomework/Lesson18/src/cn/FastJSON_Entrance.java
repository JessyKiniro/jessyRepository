package cn;

import com.alibaba.fastjson.JSON;

public class FastJSON_Entrance {

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
        //JSON->java
        Location location= JSON.parseObject(jsonString,Location.class);
        System.out.println(location);

        //java->json
        String s = JSON.toJSONString(location);
        System.out.println(s);

    }

}
