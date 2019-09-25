package test.jarTest;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import utils.MD5Util;

public class Md5Test {
    public static void main(String[] args) {
        String key = "AE095621D287D03219F57B765741F05C";
        Map sendMap = new HashMap<>();
        sendMap.put("hotelCode", "FUK1608102368");
        sendMap.put("partnerCode", "MC21608264504");

        String sign = getSendStr(sendMap, key);
        System.out.println(sign);
    }

    public static String getSendStr(Map<String, String> map, String key) {
        TreeMap<String, String> m = new TreeMap();
        for (String entry : map.keySet()) {
            m.put(entry, map.get(entry));
        }
        String aa = "";
        for (String entry : m.keySet()) {
            String value = m.get(entry);
            if (value != null && !value.equals("")) {
                aa += entry + "=" + m.get(entry) + "&";
            }
        }
        if (aa.length() > 0) {
            aa = aa.substring(0, aa.length() - 1);
        }
        return MD5Util.MD5Encode(aa + "&key=" + key);
    }
}
