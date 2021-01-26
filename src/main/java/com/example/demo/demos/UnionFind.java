package com.example.demo.demos;

/**
 * @Author: yanjie
 * @Description: 并查集
 * @Date: 2021/01/21 18:39
 */

/**
 * 并查集是一种以树形结构来表示不同种类数据的集合。一般当我们需要用到数据的连通性时会用到它。
 * 并查集维护一个数组 parent，parent 数组中维护的不是元素本身，而是元素的根节点下标索引。
 *
 *
 * 并查集一般有两种优化：
 * 「按秩合并」：在并查集中查找代表元素时，会将经过的所有元素「直接」连接到代表元素，也就是将连通分量「压扁」。
 * 「路径压缩」：在并查集中合并两个连通分量时，将「秩」小的连通分量合并到「秩」大的连通分量上面。这里「秩」可以定义为连通分量的大小（包含的节点数量），或者连通分量的高度（连通分量是树的结构，因此可以定义高度。
 *             不过在「路径压缩」优化的基础上，这个高度会不断减小，但我们不用去时刻维护它，这样也可以达到最优的时间复杂度，这是已经被证明的了）。
 *
 *
 * 时间复杂度：（下面给出的是单次并查集操作的时间复杂度）
 *『无优化』时，平均时间复杂度：O(log n)，最坏时间复杂度：O(n)
 *『按秩合并』优化时，平均时间复杂度：O(log n)，最坏时间复杂度：O(log n)
 *『路径压缩』优化时，平均时间复杂度：O(α(n))，最坏时间复杂度：O(log n)
 *『按秩合并 + 路径压缩』优化时，平均时间复杂度：O(α(n))，最坏时间复杂度：O(α(n))
 * 这里 α 表示阿克曼函数的反函数，在宇宙可观测的 n 内（例如宇宙中包含的粒子总数），α(n) 不会超过 5。
 *
 *
 * 主要操作：
 * 1）合并两个不相交集合
 * 2）判断两个元素是否属于同一集合
 */



public class UnionFind {
    private int[] parent; // 记录节点的根
    private int[] rank; // 以 i 为根节点的子树的高度（引入了路径压缩后这个定义并不准确）

    public UnionFind(int n) {
        // 初始化
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 合并
    public void union(int x, int y) {
        // 找到根节点，按秩合并
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            //两者的根节点为同一个，说明已经属于同一集合
            return;
        }
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            if (rank[rootX] == rank[rootY]) {
                rank[rootY] += 1;
            }
        }
    }

    // 查找根节点
    public int find(int x) {
        if (parent[x] != x) {
            // 引入路径压缩，若不是根节点，直接指向根节点
            return parent[x] = find(parent[x]);
        }
        return x;// 自己就是根节点
    }
}
