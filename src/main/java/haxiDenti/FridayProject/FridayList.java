package haxiDenti.FridayProject;

import spark.Request;
import spark.Response;

import java.util.HashMap;

public class FridayList {
    private HashMap<String, FridayHandler> hashMap = new HashMap<String, FridayHandler>(32);
    private Object mutex = new Object();

    private static String generateString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            int r = (int) ((Math.random() * 900) + 100);
            buffer.append(r);
        }
        return buffer.toString();
    }

    public String generateStep(FridayHandler handler) {
        synchronized (mutex) {
            String generatedString = generateString();
            hashMap.put(generatedString, handler);
            return generatedString;
        }
    }

    public Object executeStep(Request request, Response response, String stepName) {
        synchronized (mutex) {
            FridayHandler handler = hashMap.remove(stepName);
            if (handler != null) {
                return handler.handle(request, response);
            }
            return null;
        }
    }

}
