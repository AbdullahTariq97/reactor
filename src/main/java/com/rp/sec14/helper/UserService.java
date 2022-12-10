package com.rp.sec14.helper;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {

    private static final Map<String, String> MAP = Map.of("sam", "std",
                                                    "mike", "prime");

    public static Function<Context,Context> userCategoryContext(){
        return contextIn -> {
            String userName = contextIn.get("user");
            String category = MAP.get(userName);
            return contextIn.put("category", category);
        };

    }}
