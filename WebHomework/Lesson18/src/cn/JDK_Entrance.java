package cn;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JDK_Entrance {

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
        //JDK json
        Location location=new Location();
        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray addressesJSArray = jsonObject.getJSONArray("addresses");

        ArrayList<String> addressesList=new ArrayList<>();
        for(int i=0;i<addressesJSArray.length();i++){
            String address=addressesJSArray.getString(i);
            addressesList.add(address);
        }
        location.setAddresses(addressesList);

        JSONArray pointsJSArray = jsonObject.getJSONArray("points");
        ArrayList<Points> pointsList=new ArrayList<>();
        for(int i=0;i<pointsJSArray.length();i++){
            JSONObject jsonObject1 = pointsJSArray.getJSONObject(i);
            Points points=new Points();
            double x=jsonObject1.getDouble("x");
            double y=jsonObject1.getDouble("y");
            points.setX(x);
            points.setY(y);
            pointsList.add(points);

        }
        location.setPoints(pointsList);

        JSONArray palacesJSArray=jsonObject.getJSONArray("places");
        ArrayList<ArrayList<String>> palacesListList=new ArrayList<>();
        for(int i=0;i<palacesJSArray.length();i++){
            ArrayList<String> strings = new ArrayList<>();
            JSONArray jsonArray = palacesJSArray.getJSONArray(i);
            for(int j=0;j<jsonArray.length();j++){
                String string = jsonArray.getString(j);
                strings.add(string);
            }
            palacesListList.add(strings);
        }
        location.setPlaces(palacesListList);

        System.out.println(location);


    }
}
