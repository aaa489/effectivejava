package com.example.effectivejava.stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * 《effective java》-45： 明智审慎地使用 Stream
 * 如果使用得当，流可以使程序更短更清晰；如果使用不当，它们会使程序难以阅读和维护
 * 该避免使用流来处理 char 值
 * 重构现有代码以使用流，并仅在有意义的情况下在新代码中使用它们
 * 如果不确定一个任务是通过流还是迭代更好地完成，那么尝试这两种方法，看看哪一种效果更好
 * @author Don
 * @date 2022/2/25.
 */
public class SreamDemo {


    public static void main(String[] args) throws IOException {
        noStream(args);
        doStream(args);
        compromiseStream(args);
    }

    //不使用流的代码
    private static void noStream(String[] args){
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
            }
        }
        catch (Exception ex){
            //处理异常
        }
        for (Set<String> group : groups.values()) {
            if (group.size() >= minGroupSize) {
                System.out.println(group.size() + ": " + group);
            }
        }
    }

    //使用流的代码，显得难阅读和维护，这就是过度使用了流
    private static void doStream(String[] args){
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(word -> word.chars().sorted().collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append).toString()))
                .values().stream().filter(group -> group.size() >= minGroupSize).map(group -> group.size() + ": " + group).forEach(System.out::println);
        }
        catch (Exception ex){
            //处理异常
        }
    }

    //折中的方式，使用辅助方法对于流管道中的可读性比在迭代代码中更为重要，因为管道缺少显式类型信息和命名临时变量
    private static void compromiseStream(String[] args){
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(SreamDemo::alphabetize)).values().stream().filter(group -> group.size() >= minGroupSize).forEach(g -> System.out.println(g.size() + ": " + g));
        }
        catch (Exception ex){
            //处理异常
        }
    }

    //避免流中使用char
    private static void avoidChar(){
        //结果打印721011081081113211911111410810033而不是Hello world!，因为 “Hello world!”.chars() 返回的流的元素不是 char 值，而是 int 值
        "Hello world!".chars().forEach(System.out::print);
        //下面结果是正确返回的
        "Hello world!".chars().forEach(x -> System.out.print((char) x));
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray(); Arrays.sort(a); return new String(a);
    }
}
