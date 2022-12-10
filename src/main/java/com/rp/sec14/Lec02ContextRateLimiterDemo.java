package com.rp.sec14;

import com.rp.courseutil.Util;
import com.rp.sec14.helper.BookService;
import com.rp.sec14.helper.UserService;
import reactor.util.context.Context;

public class Lec02ContextRateLimiterDemo {
    public static void main(String[] args) {
        BookService
                .getBook()
                .repeat(2) // this repeats it three times
                .contextWrite(UserService.userCategoryContext())
                .contextWrite(Context.of("user","mike"))
                .subscribe(Util.subscriber());
    }
}
