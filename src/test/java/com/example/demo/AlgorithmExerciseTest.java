package com.example.demo;

import javafx.util.Pair;

import java.util.*;

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


    public int minimumEffortPath(int[][] heights) {

        return 0;
    }


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
    // 若数组空，初始化16的tab。对应i位置，若空则直接加入。非空说明碰撞。
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

    // PECS原则

    // 序列化是指把一个java对象变成二进制内容（byte[]数组）。序列化后可以把这些byte[]保存到文件中，或者通过网络传输到远程。 将数据从内存中 写到磁盘等设备 ，输出流。
    // ObjectOutputStream 负责把一个java对象写入一个字节流。

    // 反序列化把二进制内容恢复为原先的java对象。读取外部数据到内存中，输入流。
    // ObjectInputStream 负责从一个字节流读取 Java对象。从一个字节流读取Java对象
    // serialVersionUID，验证版本一致性。若父类实现Serializable，其子类自动实现Serializable接口，可以序列化。若子类实现Serializable，父类未实现，父类需要有无参数构造函数。

    // introduce：我是XX，毕业于吉林财经大学，于2018年4月在上家公司任职java职位。

    // linux





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


    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.right = new TreeNode(7);
//        root.right.left = new TreeNode(15);

//        int i = 4;
//        int a = i++;
//        int b = ++i;
//        System.out.println("i=" + i);
//        System.out.println("a=" + a);
//        System.out.println("b=" + b);

        String h = "ABC";
        String i = "AB" + "C";
        String j = "AB";
        String k = j + "C";
        System.out.println(h == i);  //true
        System.out.println(h == k);  //false



    }



}




