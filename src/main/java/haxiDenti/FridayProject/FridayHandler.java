package haxiDenti.FridayProject;

import spark.Request;
import spark.Response;

public interface FridayHandler {
    Object handle(Request request, Response response);
}
