package com.example;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/09 16:38
 */
public class AlgorithmExerciseTest {

    /**
     * 链表结构
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 树结构
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 双向链表，如LinkedList的底层结构
     */
//    public static class Node<E> {
//        E item;
//        Node<E> next;
//        Node<E> prev;
//
//        Node(E element, Node<E> next, Node<E> prev) {
//            this.item = element;
//            this.next = next;
//            this.prev = prev;
//        }
//    }

//    public static class Node {
//        int val;
//        Node next;
//        Node random;
//
//        Node(int x) {
//            this.val = x;
//            this.next = null;
//            this.random = null;
//        }
//    }

    //反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
    //输入: 1->2->3->4->5->NULL, m = 2, n = 4
    //输出: 1->4->3->2->5->NULL
    //1 ≤ m ≤ n ≤ 链表长度
    public static ListNode reverseBetween(ListNode head, int m, int n) {

        // 先找到反转链表头的前一节点 con，即m的前一节点
        ListNode cur = head;
        for (int i = 2; i < m; i++) {
            cur = cur.next;
        }
        // prev前驱节点，cur当前节点
        ListNode con = cur;
        ListNode prev = cur.next;
        ListNode tail = cur.next;

        // 反转 m 到 n
        for (int j = m; j <= n; j++) {
            ListNode tempNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tempNext;
        }
        // 将三部分连接

        tail.next = cur;

        con.next = prev;
        return head;
    }

    public static class UnionFind {
        Map<Integer, Integer> parent;
        int count; // 连通分量的数量

        public UnionFind() {
            parent = new HashMap<>();
        }

        public void union(int index1, int index2) {
            int root1 = find(index1);
            int root2 = find(index2);
            if (root1 == root2) {
                return;
            }
            parent.put(root1, root2);
            --count; // 两个连通分量合并成为一个，连通分量的总数 -1
        }

        public int find(int i) {
            if (!parent.containsKey(i)) {
                parent.put(i, i); // 新加入的节点，根为自己，且连通分量数count + 1
                ++count;
            }
            if (parent.get(i) != i) {
                parent.put(i, find(parent.get(i)));
            }
            return parent.get(i);
        }
    }

    //,166332,1394,45735,142772,1407,142759,150267,46240,139293,169014,54744,1263,1149,140559,156192,92286,168514,1291,140156,139979,58435,39373,141732,75257,165736,152419,155621,168994,68130,76206,67818,155097,140815,140297,140034,146916,140010,89058,142784,156133,155743,25458,169087,164099,56505,165487,165297,169226,143523,93140,91502,37533,164915,1109,140788,82095,140400,165776,142619,1003,1043,153661,1041,164778,165552,164719,165192,56377,26421,142641,129408,80168,140172,139809,166871,31240,170037,168696,165386,142880,169056,169109,155800,139910,43601,165102,165741,139873,118298,23424,159023,164537,170018,12702,169962,168248,142812,37595,140340,1249,154581,1280,1005,165740,139580,1350,106463,1224,56834,155875,164670,142760,139831,60911,168558,150751,140577,1038,169193,156252,40360,164565,153958,168462,42822,140499,144489,164603,169014,139756,156064,140559,165381,39373,139824,142539,139526,153663,28335,156007,107798,139740,43421,166943,1299,158628,40462,140293,168616,168117,40360,164565,153958,168462,42822,140499,170022,144489,164603,169014,139756,156064,140559,165381,39373,139824,142539,139526,153663,28335,156007,107798,139740,43421,166943,1299,158628,40462,140293,168616,168117,1231,156153,7014,169029,1354,1068,139474,155812,166327,170630,164594,126824,156129,97355,142880,164745,169056,142704,165375,109019,50810,43601,165499,132464,165741,164540,164537,1247,139976,120597,140315,1368,168531,75434,142760,142703,168644,166619,156103,167994,140012,141477,143410,169115,170813,1301,1004,1207,1316,1462,1467,21535,33085,34542,36846,44468,46571,52656,57883,62089,64325,71285,71509,71669,76403,82791,101240,105914,113050,114846,121519,131224,139251,139365,139883,140030,140588,142711,142777,142811,142890,152379,153684,154076,155674,155719,155846,156037,156290,162661,164546,164639,164687,164775,166936,168576,15234,139984,165974,139471,155670,140218,150130,16630,71426,155957,121211,165391,155809,127239,142692,164652,59381,142834,155619,153675,165335,164577,168367,151606,95943,168112,165337,167047,139117,135062,142816,168236,142621,153036,164947,108053,65229,143726,69142,140447,153747,156160,165062,1334,68795,74118,143219,168307,165587,140414,168525,156250,

    static Random random;
    static Map<Integer, Integer> map;
    static int i = 0;


    public static int getRandom() {

        int r = random.nextInt(i + 1);
        return map.get(r);
        // return r;
    }

    //,166332,1394,45735,142772,1407,142759,150267,46240,139293,169014,54744,1263,1149,140559,156192,92286,168514,1291,140156,139979,58435,39373,141732,75257,165736,152419,155621,168994,68130,76206,67818,155097,140815,140297,140034,146916,140010,89058,142784,156133,155743,25458,169087,164099,56505,165487,165297,169226,143523,93140,91502,37533,164915,1109,140788,82095,140400,165776,142619,1003,1043,153661,1041,164778,165552,164719,165192,56377,26421,142641,129408,80168,140172,139809,166871,31240,170037,168696,165386,142880,169056,169109,155800,139910,43601,165102,165741,139873,118298,23424,159023,164537,170018,12702,169962,168248,142812,37595,140340,1249,154581,1280,1005,165740,139580,1350,106463,1224,56834,155875,164670,142760,139831,60911,168558,150751,140577,1038,169193,156252,40360,164565,153958,168462,42822,140499,144489,164603,169014,139756,156064,140559,165381,39373,139824,142539,139526,153663,28335,156007,107798,139740,43421,166943,1299,158628,40462,140293,168616,168117,40360,164565,153958,168462,42822,140499,170022,144489,164603,169014,139756,156064,140559,165381,39373,139824,142539,139526,153663,28335,156007,107798,139740,43421,166943,1299,158628,40462,140293,168616,168117,1231,156153,7014,169029,1354,1068,139474,155812,166327,170630,164594,126824,156129,97355,142880,164745,169056,142704,165375,109019,50810,43601,165499,132464,165741,164540,164537,1247,139976,120597,140315,1368,168531,75434,142760,142703,168644,166619,156103,167994,140012,141477,143410,169115,170813,1301,1004,1207,1316,1462,1467,21535,33085,34542,36846,44468,46571,52656,57883,62089,64325,71285,71509,71669,76403,82791,101240,105914,113050,114846,121519,131224,139251,139365,139883,140030,140588,142711,142777,142811,142890,152379,153684,154076,155674,155719,155846,156037,156290,162661,164546,164639,164687,164775,166936,168576,15234,139984,165974,139471,155670,140218,150130,16630,71426,155957,121211,165391,155809,127239,142692,164652,59381,142834,155619,153675,165335,164577,168367,151606,95943,168112,165337,167047,139117,135062,142816,168236,142621,153036,164947,108053,65229,143726,69142,140447,153747,156160,165062,1334,68795,74118,143219,168307,165587,140414,168525,156250,
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.set(0, 2);
        Integer[] array = list.toArray(new Integer[0]);
        "s".equals(new String("s"));

        int[] array1 = list.stream().mapToInt(Integer::intValue).toArray();


        List<Integer> l  = new ArrayList<>(Arrays.asList(array));


    }




























// ArrayList 底层采用Object，初始容量为10。
    // 定义用于空实例的Object数组。  定义用于默认大小的空实例
    // ArrayList ，若有参数构造初始化，指定初始容量，new Object[n]。
    // 无参数构造，并不在初始化时分配容量。扩容：添加第一个元素时，才分配容量。 ensureCapacityInternal ,calculateCapacity
    // 扩容：newCapacity = oldCapacity + oldCapacity >> 1; 即1.5倍。

    // set(int index, Object o), trimToSize()释放多余内存
    // 遍历方式：list的iterator方法，Iterator的while实现或for实现 。list的随机访问get。foreach语法糖。
    // 数组与List。相同点：有序。不同点：数组大小固定，List动态扩容。数组只能存相同类型元素，list泛型。
    // 数组转List：Arrays.asList()。返回的是Arrays的一个内部类，不能add、remove。只能创建新集合: List<Integer> list = new ArrayList<>(Arrays.asList(array)); 或 list = Arrays.stream(array).collect(Collectors.toList());
    // List转Integer数组：Integer[] array = List.toArray(new Integer(list.size))。
    // List转int[]需注意：不能使用list.toArray。toArray(T[] a)的T是泛型，泛型是引用类型，不能传入new int[n]，会报错; 只能通过循环赋值来得到，或者list.stream().mapToInt(Integer::intValue).toArray();

    // list的contains(),indexOf()方法使用的是equals。要使用这些方法，集合中的实例需要正确覆盖equals()。
    // equals()：逻辑，同一类型。














    // final 修饰的字段必须在创建对象时初始化、方法不能被覆写、类不能被继承。 final的可见性、重排序规则。
    // final的可见性：只要对象是正确构造的，且 没有this引用逃逸，那在其他线程中就能看见 final 的值。
    // 写final域的重排序规则：JMM 禁止编译器把final域的 写重排序到构造函数外。保证在对象引用为任意线程可见之前，对象的final域已经被正确初始化了。
    // 读final域的重排序规则：在读一个对象的final域之前，一定会先读包含这个final域的对象的引用。

    // abstract。不需要实现任何功能：抽象方法，必须被子类重写。包含抽象方法的类->abstract类，必须被继承。
    // 如果一个抽象类没有字段->interface。方法都是是abstract的。静态final常量。接口：can-do，处理目标一致，方式不同。
    // 一个接口可以继承另一个接口。
    // 接口中可以定义default方法。实现类不必覆写default方法。

    // 接口和抽象类
    // 静态方法不属于实例。静态方法内部不能访问this变量，不能访问实例字段，只能访问静态字段。


    // 基本，最大，任务队列，时间，单位，拒绝策略
    // RejectedExecutionHandler
    // 当队列和线程池都满了，新请求的拒绝策略：直接抛异常；不处理直接丢弃；抛弃队列里最近的一个请求；用调用者所在线程来执行

    // 机器数 、真值
    // 原码 最高位0表示+，1表示-。符号位
    // 反码 负数的反码：原码的符号位不变，数值位取反
    // 补码 负数的补码：原码的符号位不变，数值位取反再加1。即反码加1。

    // List与Array  有序，索引查找
    // 大小固定、动态扩展；同种类型、多种类型；增加删除
    // 数组转List：Arrays.asList()返回Arrays内部类，无增删操作；new ArrayList<>(Arrays.asList("a","b"))；stream的collect()

    // ==两个基本数据类型是否相等，两个引用是否指向同一对象； equals 两个对象是否是同一个对象
    // 有序，读多写少，支持随机访问，数组。扩容
    // 有序，写多读少，不支持随机访问。双向链表。 实现Deque双向列表。
    // transient不被序列化

    // map-put 。i= (n-1)&(k.hashCode()^k.hashCode()>>>16)。
    // 若数组空，初始化16的tab。对应i位置，若空则直接加入。非空说明碰撞。哈希碰撞
    // 判断与tab[i]的key是否是同一个key，若是则替换。
    // 判断是否红黑树
    // 若与桶中头结点的key不重复，且bucket数量<8。循环bucket，若遇到key重复的进行替换，否则链到尾结点后。
    // 第一次put元素时创建16的Node数组；添加新元素后若map中键值对数量size > 阈值capacity*loadfactor（数组长度*负载因子）。

    // map-resize()。
    // 1、计算newCap,newThr。带参构造初始化时没有真正创建tab，是在第一次put时进行resize。如果旧数组非空oldCap>=16，数组长度扩容为2倍（扩容后<2^30)
    // 2、以newCap创建新数组。Node<K,V>[] newTab
    // 3、若oldTab!=null，移动元素。遍历每个bucket。
    // 如果该bucket的next=null，即只有一个元素。新索引 e.hash&(newCap-1)。 若bucket内冲突，新索引可以取巧通过 e.hash&oldCap的结果判断，若结果=0则索引不变，=1则新索引=原索引+oldCap

    // 线程安全的map：
    // 静态方法Collections.synchronizedMap(map)
    // hashtable 所有方法都加synchronized，锁住整个table。不推荐
    // ReentrantLock：tryLock()不会阻塞，抢锁成功返回true，失败返回false。lock抢锁成功返回，失败会加入同步队列，阻塞获取锁。
    // ConcurrentHashMap put操作：若key定位的位置为空，利用CAS尝试写入，若失败则自旋保证成功。 若key定位的位置非空，且非扩容状态，synchronized同步锁写入，锁住桶中第一个节点对象。
    // 无锁偏向锁轻量级锁重量级锁
    // TreeMap 红黑树，有序。TreeSet是value为固定值(new Object())的TreeMap.
    // Queue-PriorityQueue 优先队列，数组实现的小顶堆
    // Deque-ArrayDeque 数组实现。当做循环数组，head指向队首第一个有效元素，tail指向队尾第一个可插入元素的空位。两倍扩容
    // Deque-LinkedList  双向链表

    // 内存模型：
    // 程序计数器：线程正在执行的字节码的行号指示器，java：正在执行的虚拟机字节码指令的地址
    // 虚拟机栈。java方法，栈帧，
    //          局部变量表（存放编译器可知的基本数据类型、对象引用类型、returnAddress）。以变量槽slot为单位，索引定位。所需内存空间在编译期间完成分配，
    //          操作数栈、动态链接、方法出口。  栈深度：StackOverFlowError。内存：OutOfMemoryError。栈容量参数 -Xss
    // 本地方法栈 Native方法
    // 堆：线程共享，虚拟机启动时创建。对象实例、数组。堆的最小值参数-Xms，堆的最大值参数-Xmx。 OutOfMemoryError
    // 方法区：线程共享，用于存储被虚拟机加载的类信息、变量、静态常量，编译器编译后的代码。 OutOfMemoryError
    //        运行时常量池：用于存放编译期生成的各种字面量和符号引用。

    // 对象的内存分布 ：对象头（存储对象自身的运行时数据、直接指针）、实例数据、对齐填充。  句柄访问、直接指针。
    // 如何判断对象是否存活：计数算法（引用计数器；无法解决对象之间相互循环引用的问题）、
    //                    可达性分析：如果一个对象到GC Roots之间没有任何引用链相连（GC Roots到这个对象不可达），此对象可回收 。
    //                        可作为GC Roots的对象：

    // 死锁：多个线程持有不同的锁，并试图获取对方已持有的锁，无限等待。 避免死锁的方法：多线程获取锁的顺序要一致。避免一个线程同时获取多个锁；避免一个线程在锁内占用多个资源，尽量一个锁一个资源；尝试定时锁

    // volatile 可见性，写操作，lock前缀的指令。仅仅保证单个变量读写具有原子性。
    // synchronized 普通同步方法-锁是当前实例对象；静态同步方法-锁是当前类的Class对象；同步方法块-synchronized括号里配置的对象。synchronized用的锁是存在java对象头里的。
    //    monitorenter、monitorexit
    // synchronized用的锁是存在java对象头里的。Mark Word：存储hashCode、分代年龄、锁标记位。
    // 偏向锁：


    // distinct对后面所有的字段均起去重作用。无法返回不重复目标字段外的其他数据。  from,join,on -> where -> group by -> 分组后聚合函数 -> having -> select -> distinct -> order by ->limit
    // explain。 id,select_type,table,type(查找行的方式const>eq_ref>ref>range>index>all),possible_keys,key,key_len,rows,Extra


    // 锁机制用于管理对共享资源的并发访问。
    // 乐观锁：CAS思想，通过版本号或时间戳实现。 悲观锁：先取锁再操作，通过表锁或行锁等实现。
    // 悲观锁按锁粒度划分。表锁（对整张表加锁，加锁快，锁粒度大，开销小），行锁（对单行加锁，加锁慢，锁粒度小，会出现死锁）。InnoDB支持行锁和表锁共存的多粒度锁。通过给索引上的索引项加锁来实现。
    // 共享锁（读锁，S锁），一个事务获取S锁后允许其他事务获取S锁，但不允许其他事务获取X锁。排他锁（写锁，X锁），一个事务获取X锁后不允许其他事务获取S锁或X锁。 共享锁可以共存。共享锁和排他锁不能共存。排他锁和排他锁也不可以。
    // 意向锁是表级锁，用来表达一个事务想要获取什么锁。

    // #是预编译处理，会将sql中的#{}替换成？，然后赋值。传入字符串后，会在值两边加入单引号。主要用于获取DAO中的参数数据。创建预编译的SQL。
    // $是字符串替换，将${}替换成变量值，SQL注入：恶意SQL命令。主要用于获取配置信息，一般是用$接收参数时，一般是字段名表名等。

    // 网关：统一管理微服务请求，权限控制、路由转发、负载均衡、监控、安全控制黑名单白名单等。
    // 虚拟机的类加载机制：虚拟机把描述类的数据从 Class文件加载到内存，并对数据进行校验、转换解析、初始化，最终形成
    // 双亲委派模型：组织类加载器之间的关系。如果一个类加载器收到了类加载的请求，首先把请求委派给父类加载器去完成。所有的加载请求最终都会传送到顶层的类加载器。自定义类加载器 -> 应用程序类加载器 -> 扩展类加载器 -> 启动类加载器
    // 只有当两个类由同一个类加载器加载时，这两个类才相等。如果没有双亲委派模型，由各个类加载器自行加载的话。当用户自己编写了一个 `java.lang.Object` 类，那样系统中就会出现多个 `Object`，这样 Java 程序中最基本的行为都无法保证，程序会变的非常混乱。
    // i++ 先拿去用再自增。 ++i先自增再拿去用。

    // PECS原则 ： 使用extends通配符表示只能读不能写，使用super通配符表示只能写不能读。如果需要返回T，生产者，使用extends，允许读不允许写；如果需要写入T，消费者，使用super，允许写不允许读。

    // 序列化是指把一个java对象变成二进制内容（byte[]数组）。序列化后可以把这些byte[]保存到文件中，或者通过网络传输到远程。 将数据从内存中 写到磁盘等设备 ，输出流。
    // ObjectOutputStream 负责把一个java对象写入一个字节流。

    // 反序列化把二进制内容恢复为原先的java对象。读取外部数据到内存中，输入流。
    // ObjectInputStream 负责从一个字节流读取 Java对象。从一个字节流读取Java对象
    // serialVersionUID，验证版本一致性。若父类实现Serializable，其子类自动实现Serializable接口，可以序列化。若子类实现Serializable，父类未实现，父类需要有无参数构造函数。

    // Mybatis一级缓存是SqlSession级别的缓存。每个SqlSession中有一个Executor，每个Executor中有一个Local Cache。当用户发起查询，根据语句在localCache中查询。如果缓存命中，将结果返回。若没有命中，查询数据，将结果写入localCache，然后返回。
    // 一级缓存。默认开启一级缓存。localCacheScope.SESSION。 设置，在mybatis配置文件中统一配置。对于sql语句可以单独配置useCache。 useCache默认为true。flushCache查询语句默认flase，修改语句默认true。
    // 一级缓存生命周期：一级缓存只在数据库会话内部共享，生命周期和SqlSession一致。如果SqlSession调用了close()，会释放掉一级缓存PerpetualCache对象，一级缓存将不可用。如果调用了clearCache()或执行修改操作，会清空perpetualCache对象中的数据，但是该对象仍可用。
    // 哪些因素会导致一级缓存失效：通过同一个SqlSession执行修改操作；事务提交；事务回滚。在整个Spring容器中，一般只有一个SqlSession对象。因为Spring一般是主动提交事务的，所以一级缓存经常失效。
    // 对一级缓存的操作实则对 HashMap的操作。

    // 一级缓存的最大共享范围就是一个SqlSession内部。如果多个SqlSession之间需要共享缓存，需要使用到二级缓存。
    // 二级缓存是基于namespace的。数据查询执行的流程：二级缓存 -> 一级缓存 -> 数据库。
    // 默认开启二级缓存。cacheEnabled，true。在myBatis配置文件中统一配置。

    // 加锁+设置超时时间
    // if redis.call('setnx',KEYS[1],ARGV[1]) < 1 then return 0;end; redis.call('expire',KEYS[1],ARGV[2]);return 1;
    // 判断+del释放锁
    // if redis call('get',KEYS[1]) == ARGV[1]) then return redis.call('del',KEYS[1]) else return 0;

    // CAP定理：一致性、可用性、分区容错性；BASE理论：基本可用（响应延时、降级）、软转态（允许系统在不同节点的数据副本之间进行数据同步的过程存在延时）、最终一致性

    // 字符串的最大长度 266634 2^16-2
    // redis 与 Memcached比较：数据类型，持久化、数据备份；分布式存储,Memecached客户端实现， Redis Clustor。


    class Node {
        public int val;
        public List<TreeNode> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<TreeNode> _children) {
            val = _val;
            children = _children;
        }
    }






    // 缓存、降级、限流。限流手段：拒绝处理（友好提示或跳转到错误页面）、延迟处理（排队或等待）、服务降级（兜底数据）
    // 计数器-固定窗口（维护单位时间内的计数值，不是平均速率限流） 计数器-滑动窗口（粒度更细，1s的滑动窗口，10格，LinkedList）
    // 漏桶算法：任意速率接收，固定速率请求，桶容量固定，满了则拒绝。使用队列实现，按照固定速率从队列中取出执行


        // redo log重做日志，是InnoDB引擎特有；是物理日志；循环写，固定空间会用完。 binlog归档日志 是MySQL的Server层实现的；是逻辑日志；追加写
    // 整库备份 ，binlog归档日志
    // redo log 两阶段提交，prepare,commit。为了让两份日志逻辑一致。
}


// 程序计数器（java方法：虚拟机字节码指令的地址）、
// java虚拟机栈。栈帧。用于存储局部变量表、操作数栈、方法出口。 栈内存容量由 -Xss 参数设定。 StackOverFlowError  OutOfMemoryError
   // 局部变量表：编译期可知的基本数据类型、对象引用类型、returnAddress指向字节码的地址
   // 局部变量表所需的内存空间在编译期完成分配。以变量槽为最小单位。索引定位。第0位索引的slot默认用于传递方法所属对象实例的引用。
//  本地方法栈
// java堆。几乎所有的对象实例及数组。 -Xms  -Xmx OutOfMemoryError
// 方法区。用于存储已被虚拟机加载的类信息、变量、静态常量、即时编译器编译后的代码等。 MaxPermSize  OutOfMemoryError
   // 运行时常量池。存放编译器生成的各种字面量和符号引用。
// 对象：1）对象头，两部分：对象自身的运行时数据 Mark Word（如哈希码、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID）；直接指针。2）实例数据。 3）对齐填充
// 判断对象是否存活：计数算法（无法解决对象循环引用）、
   // 可达性分析算法，从GC Roots到这个对象不可达，说明对象可回收。
   // 可作为GC Roots的对象：虚拟机栈（局部变量）引用的对象；本地方法栈中引用的对象；方法区中类静态属性引用的对象；方法区中常量引用的对象。
// 标记-清除算法。复制算法。标记-整理算法。  分代收集算法，新生代：复制算法，将存活对象从Eden区和survivor拷贝到另一个survivor区。
// 两个survivor的意义：减少被送往老年代的对象，减少MajorGc；减少碎片化，可以复制。

// java内层模型：定义程序中各个变量（实例字段、静态字段、构成数组对象的元素）的访问规则。
// 每条线程都有自己的工作内存，保存被该线程用到的变量的主内存副本拷贝。 lock unlock read load use  assign store write
// volatile 可见性（）；禁止重排序。volatile变量的运算在并发下不是安全的，无法保证原子性。 volatile的修改：读取变量到local，修改，将local值写回，内存屏障其他线程可见。前三步不是原子性
// volatile变量的运算，需要加锁来保证原子性。读取，修改，写回，内存屏障

// 原子性（read,load,use,assign,store,write，monitorenter,monitorexit)  基本数据类型的读写，synchronized同步块
// 可见性（volatile, synchronized对一个变量unlock之前先同步回主内存, final 一旦初始化完成其他线程可见）
// 有序性： volatile,synchronized(一个变量在同一时刻只允许一个线程对其lock操作）

// 输入流 读取 输出流 写

// 创建、运行（Running,Ready）
// 无限期等待（不会分配CPU执行时间，等待其他线程显式唤醒）、限时等待（不会分配CPU执行时间，一定时间后系统自动唤醒） wait,join LockSupport
// 阻塞：在线程等待进入同步区域时进入阻塞。  阻塞与等待的区别：阻塞状态是在等待获取一个同步锁。等待状态是等待一段时间或唤醒动作的发生

// java代码在编译后变成java字节码，字节码被类加载器加载到JVM里，JVM执行字节码，最终转换成汇编指令在CPU上执行。
// 死锁：多线程各自持有不同的锁，并试图获取对方已持有的锁。
// 避免死锁的方法：避免多个线程获取一个资源，避免一个线程在锁内占用多个资源，尝试使用定时锁，获取锁的顺序一致
// synchronized 对于普通同步方法，锁是实例对象，对于静态同步方法，锁是当前类的Class对象；同步方法块，锁的是括号里的对象。
// 锁存在对象头里。Mark Word（hashCode,GC分代年龄，锁标记位，线程持有的锁，偏向线程ID )

// 类加载过程
// 1、加载。通过类限定名获取这个类的二进制字节流；将字节流代表的静态存储结构转化为运行时数据结构；生成代表这个类的Class对象
// 2、连接。 1）验证：文件格式、元数据、字节码、符号引用。 2）准备：对类变量在方法区分配内存并设置初始值 3）解析：虚拟机将常量池中的符号引用替换成直接引用
// 3、初始化：执行类构造器<clinit>()方法

// CountDownLatch 同步辅助器。允许一个或多个线程一直等待，直到一组在其他线程执行的操作全部完成。
// CyclicBarrier 一组线程相互等待，直到所有线程到达一个同步点
// Semaphore 信号量，用于同一时间资源可被访问的线程数量

// synchronized 普通方法，锁实例对象，锁静态方法，锁类的Class对象，同步方法块，锁括号里对象

// 运行时异常 NullPointerException,IndexOutOfBoundsException,NumberFormatException,ClassNotFoundException,ClassCastException,ConcurrentModificationException
//           IllegalArgumentException,UnsupportedOperationException(如Arrays.asList后修改）
// String toString(),length(),equals(),indexOf(),substring(),toCharArray(),startsWith(),format(),split(),toLowerCase()

