package com.unitn.zaffino.lingProg.grafico;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {
	// write your code here
        get("/hello", (request, response) -> "Hello World!" + " little one");
    }
}
