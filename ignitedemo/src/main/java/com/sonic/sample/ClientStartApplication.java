package com.sonic.sample;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.stream.StreamTransformer;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ClientStartApplication
 *
 * @author Sonic
 * @since 2020/5/26
 */
public class ClientStartApplication implements Serializable {

    public static void main(String[] args) {
//        operateCache();
//        compute();
        operateStream();
    }

    private static void operateStream() {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("default-config.xml");

        CacheConfiguration cfg = new CacheConfiguration("wordCountCache");

        Ignite ignite = Ignition.start(resource);

        ignite.destroyCache(cfg.getName());

        String[] text = {"aa", "bb", "bb"};

        IgniteCache<String, Long> stmCache = ignite.getOrCreateCache(cfg);

        try (IgniteDataStreamer<String, Long> stmr = ignite.dataStreamer(stmCache.getName())) {
            // Allow data updates.
            stmr.allowOverwrite(true);


            // Configure data transformation to count instances of the same word.
            stmr.receiver(StreamTransformer.from((e, arg) -> {
                // Get current count.
                Long val = e.getValue();
                System.out.println("val:" + val + ", arg: " + arg.getClass());
                // Increment count by 1.
                e.setValue(val == null ? 1L : val + 1);

                return null;
            }));
            // Stream words into the streamer cache.
            for (String word : text) {
                stmr.addData(word, 1L);
                System.out.println("add data...");
            }

        }

        Long aa = stmCache.get("bb");
        System.out.println("bb: " + aa);

        ignite.close();

    }

    private static void compute() {

        URL resource = Thread.currentThread().getContextClassLoader().getResource("default-config.xml");

        try (Ignite ignite = Ignition.start(resource)) {
            Collection<IgniteCallable<Integer>> calls = new ArrayList<>();

            // Iterate through all the words in the sentence and create Callable jobs.
            for (final String word : "Count characters using callable".split(" "))
                calls.add(word::length);

            // Execute collection of Callables on the grid.
            Collection<Integer> res = ignite.compute().call(calls);

            // Add up all the results.
            int sum = res.stream().mapToInt(Integer::intValue).sum();

            System.out.println("Total number of characters is '" + sum + "'.");
        }
    }

    public static void operateCache() {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("default-config.xml");
        //启动ignite服务
        Ignite ignite = Ignition.start(resource);
        ignite.destroyCache("test");
        //创建cache
        IgniteCache<String, String> cache = ignite.getOrCreateCache("test");
        //存入数据
        cache.put("cord", "hello");
        //查询数据
        System.out.format("key[%s]->value[%s]\n", "cord", cache.get("cord"));
        ignite.close();
    }
}
