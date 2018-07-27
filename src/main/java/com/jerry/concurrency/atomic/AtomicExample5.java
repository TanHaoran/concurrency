package com.jerry.concurrency.atomic;

import com.jerry.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/7/21
 * Time: 14:41
 * Description:
 */

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    @Getter
    public volatile int count = 100;

    // 其中更新的字段fileName必须是使用volatile、非static修饰的
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");


    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        // 只会更新一次
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1, {}", example5.getCount());
        }


        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }
    }
}
