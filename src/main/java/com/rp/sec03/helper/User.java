package com.rp.sec03.helper;

import com.rp.courseutil.Util;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class User {
    private int userId;
    private String name;

    public User(int userId){
        this.userId = userId;
        this.name = Util.faker().name().fullName();
    }
}
