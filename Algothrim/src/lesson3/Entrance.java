package lesson3;

public class Entrance {
    public static void main(String[] args) {
        String str="<option value=1555>7°E — Eutelsat 7C </option>";
        String value=str.substring(str.indexOf("=")+1,str.indexOf(">"));

        System.out.println(value);

    }
}
