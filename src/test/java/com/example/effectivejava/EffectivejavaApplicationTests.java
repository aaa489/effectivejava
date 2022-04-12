package com.example.effectivejava;

import com.example.effectivejava.enums.SingleEnum;
import com.zaxxer.hikari.util.IConcurrentBagEntry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.stream.Collectors;

import static com.zaxxer.hikari.util.UtilityElf.IS_JAVA7;

@SpringBootTest
class EffectivejavaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testSingle(){
        System.out.println(SingleEnum.INSTANCE.createId());
    }

    @Test
    void testClone(){
        MyCloneObj myCloneObj = new MyCloneObj();
        myCloneObj.setI(1);
        myCloneObj.push();
        MyCloneObj myCloneObj1 = (MyCloneObj)myCloneObj.clone();
        myCloneObj1.pop();
        System.out.println("equal: " + myCloneObj.equals(myCloneObj1));
        System.out.println("getClass: " + (myCloneObj.getClass() == myCloneObj1.getClass()));
        System.out.println("object: " + (myCloneObj == myCloneObj1));
    }


    @Test
    void testComparable(){
        Comparator<MyNumber> comparator = Comparator.comparing(MyNumber::getA);

        MyNumber number1 = new MyNumber();
        number1.setA(1);
        MyNumber number2 = new MyNumber();
        number2.setA(1);
        //int result = number1.compareTo(number2);
        int result = comparator.compare(number1, number2);
        if (result == 0){
            System.out.println("number1 和 number2 相等");
        }
        else {
            System.out.println("number1 比 number2 " + (result > 0 ? "大" : "小"));
        }
    }

    @Test
    void test(){
        //srping加載bean类型方式
//        try
//        {
//            Class c = ClassUtils.forName("com.zaxxer.hikari.HikariDataSource", ClassUtils.getDefaultClassLoader());
//            int i = 1;
//        }
//        catch (Exception ex){
//
//        }

        //融易保任务测试
//        ScheduledExecutorService clearUnPayInfoTaskExecutor = Executors
//                .newSingleThreadScheduledExecutor(new RybDefaultThreadFactory("清除UnPayInfo表执行线程"));
//        clearUnPayInfoTaskExecutor.schedule(() -> {
//            System.out.println(new Date() + "：-123");
//            throw new Exception("eee");
//        }, 20000, TimeUnit.MILLISECONDS);

        //弱引用
//        ThreadLocal<ArrayList<WeakReference<String>>> threadList = new ThreadLocal<ArrayList<WeakReference<String>>>();
//        threadList.set(new ArrayList<WeakReference<String>>(16));
//        threadList.get().add(new WeakReference<String>("abc"));
//
//        WeakReference<String> weakReference = new WeakReference<>("abc");
//        System.gc();

        //CopyOnWriteArrayList，修改的时候创建一个新的数组进行拷贝，损耗性能，适合读多写少场景
//        MyNumber number1  = new MyNumber();
//        number1.setA(2);
//        MyNumber number2  = new MyNumber();
//        number2.setA(3);
//        CopyOnWriteArrayList<MyNumber> arrayList = new CopyOnWriteArrayList<>();
//        arrayList.add(number1);
//        arrayList.add(number2);
//
//        System.out.println(arrayList.hashCode());
//
////        arrayList.remove(0);
//
//        MyNumber s = arrayList.get(0);
//        s.setA(1);
//
//        System.out.println(arrayList.hashCode());

//        测试stream
//        MyNumber number1 = new MyNumber();
//        number1.setA(2);
//        MyNumber number2 = new MyNumber();
//        number2.setA(3);
//
//        List<MyNumber> list = new ArrayList<>();
//        list.add(number1);
//        list.add(number2);
//        List<MyNumber> list1 = list.stream().peek( num -> num.setA(5)).collect(Collectors.toList());

        //验证getDeclaredMethods是否会获取父类的接口
//        Sub sub = new Sub();
//        Method[] methods = sub.getClass().getDeclaredMethods();

        try {
            Thread.sleep(10000);
        }
        catch (Exception ex){
            ///
        }
        int i = 1;
    }

     /**
       * 淘汰游戏，比如100人围成圈，从1开始报数，报到几淘汰，求最后留下来的人
       * @author: Don
       * @date: 2022/4/7 14:10
       **/
    @Test
    public void evictGame(){
        //驱逐报数号
        int evictNum = 7;
        int evictTimes = 0;
        int size = 100;
        int[] ints = new int[size];
        for (int i = 0; i < size; i++)
        {
            ints[i] = i+1;
        }

        for (int i = 0, currentNum = 0; ; i++) {
            //新一轮
            if (i == size){
                i = 0;
            }
            //只有没被淘汰的可以报号
            if (ints[i] != 0){
                currentNum++;
            }

            //报到驱逐数字
            if (currentNum == evictNum){
                ints[i] = 0;
                evictTimes++;
                currentNum = 0;
            }
            if (evictTimes == size - 1){
                i = 0;
                while (ints[i] == 0)
                {i++;}
                System.out.println("最后生还者编号：" + ints[i]);
                break;
            }
        }
    }

    public static class Super {
        public void a(){}
        public void b(){}

    }

    public static class Sub extends Super {
        public void c(){}
    }

    public static class RybDefaultThreadFactory implements ThreadFactory {
        private String name;

        public RybDefaultThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, name);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
