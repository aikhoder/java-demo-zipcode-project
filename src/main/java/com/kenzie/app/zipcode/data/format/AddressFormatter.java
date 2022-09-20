package com.kenzie.app.zipcode.data.format;

import java.util.HashMap;

public class AddressFormatter {
    //declare properties

    //lookup table - Hashmap
    private static HashMap<String,String> abbreviationTable;
    // initialize map -- hardCode
    public static void initializeAddressMap(){
        abbreviationTable= new HashMap<>();
        abbreviationTable.put("ST","STREET");
        abbreviationTable.put("ST.","STREET");
        abbreviationTable.put("st","STREET");
        abbreviationTable.put("St.","STREET");

        abbreviationTable.put("AVE","AVENUE");
        abbreviationTable.put("AVE.","AVENUE");
        abbreviationTable.put("Ave","AVENUE");
        abbreviationTable.put("Ave.","AVENUE");

        abbreviationTable.put("RD","ROAD");
        abbreviationTable.put("rd","ROAD");
        abbreviationTable.put("RD.","ROAD");
        abbreviationTable.put("rd.","ROAD");


    }

    // replace address string - use the map
    // Example : 123 Main St.
    // Output : 123 Main STREET
    public static String replaceAbbreviation(String input){
//        for (String currentKey: abbreviationTable.keySet()) {
//            resultStr = resultStr.replace(currentKey,abbreviationTable.get())
//        }
        String resultStr="";
        int ind = input.indexOf("st");
        input = input.substring(ind);
        System.out.println(input);
        resultStr =abbreviationTable.get(input);

        return resultStr;

    }

    public static String replaceAbbreviation(String stringTarget, String input){
        String resultStr="";
        int ind = input.indexOf(stringTarget);
        input = input.substring(ind);
        System.out.println(input);
        resultStr =abbreviationTable.get(input);

        return resultStr;

    }
    public static void main(String[] args) {
        AddressFormatter.initializeAddressMap();
        String adr = "123 Main rd";
        String newST =AddressFormatter.replaceAbbreviation(adr);
        String adr1 = adr.replace("rd",newST);
        System.out.println(adr1);
    }

}
