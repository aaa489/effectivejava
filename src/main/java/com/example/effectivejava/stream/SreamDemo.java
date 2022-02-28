package com.example.effectivejava.stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

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
        streamFirst();
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


     /**
       *《effective java》-46： 优先考虑流中无副作用的函数
       * forEach操作应仅用于报告流计算的结果，而不是用于执行计算
       * 将流的元素收集到真正的集合中的收集器非常简单。有三个这样的收集器: toList()，toSet()和 toCollection(collectionFactory)
       * 静态导入收集器的所有成员是一种惯例和明智的做法，因为它使流管道更易于阅读
       * @author: Don
       * @date: 2022/2/28 13:43
       **/
    private static void streamFirst(){
        List<String> words = new ArrayList<>();
        words.add("A");
        words.add("B");

        final Map<String, Long> freq = new HashMap<>();
        freq.put("a", 7L);
        //不好的方式
        words.forEach(word -> {
            freq.merge(word.toLowerCase(), 1L, Long::sum); });

        //好的方式，使用了流 API，它更短更清晰
        Map<String, Long> freq1 = words.stream().collect(groupingBy(String::toLowerCase, counting()));

        //使用静态收集器toList来做列表的转换
        List<String> topTen = freq.keySet().stream().sorted(comparing(freq::get).reversed()).limit(10).collect(toList());
        //使用静态收集器toMap来做列表的转换
        final Map<String, String> strings= words.stream().collect(toMap(Object::toString, e -> e));
    }

     /**
       *《effective java》-47：优先使用Collection而不是Stream来作为方法的返回类型
       * 返回Stream用户想用Iterable，返回Iterable用户想用Stream都不是很方便，需要做适配器来进行转换，而
       * Collection 接口是 Iterable 的子类型，并且具有 stream 方法，因此它提供迭代和流访问
       * 如果在将来的 Java 版本中， Stream 接口声明被修改为继承 Iterable ，那么应该随意返回流
       * 缺点： Collection 有 int 返回类型的 size 的方法，该方法将返回序列的长度限制为 Integer.MAX_VALUE 或 2的31次方-1
       * @author: Don
       * @date: 2022/2/28 15:51
       **/
    private static final <E> Collection<Set<E>> CollectionFirst(Set<E> s){
        List<E> src = new ArrayList<>(s);
        if (src.size() > 30) {
            throw new IllegalArgumentException("Set too big " + s);
        }
        return new AbstractList<Set<E>>() {
            //元素数量大于30个会抛异常，超出int最大值
            @Override public int size() {
                return 1 << src.size(); // 2 to the power srcSize
            }
            @Override public boolean contains(Object o) {
                return o instanceof Set && src.containsAll((Set)o);
            }
            @Override public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; i++, index >>= 1){
                    if ((index & 1) == 1) {
                        result.add(src.get(i));
                    }
                }
                return result;
            }
        };
    }

     /**
       * 迭代器转流适配
       * @author: Don
       * @date: 2022/2/28 16:04
       **/
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

     /**
       * Stream的使用
       * @author: Don
       * @date: 2022/2/28 16:46
       **/
    public static class SubLists {
        public static <E> Stream<List<E>> of(List<E> list) {
            return Stream.concat(Stream.of(Collections.emptyList()), prefixes(list).flatMap(SubLists::suffixes));
        }
        private static <E> Stream<List<E>> prefixes(List<E> list) {
            return IntStream.rangeClosed(1, list.size()).mapToObj(end -> list.subList(0, end));
        }
        private static <E> Stream<List<E>> suffixes(List<E> list) {
            return IntStream.range(0, list.size()).mapToObj(start -> list.subList(start, list.size()));
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
