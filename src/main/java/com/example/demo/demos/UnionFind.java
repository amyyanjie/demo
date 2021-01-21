package com.example.demo.demos;

/**
 * @Author: yanjie
 * @Description: 并查集
 * @Date: 2021/01/21 18:39
 * <p>
 * 并查集是一种以树形结构来表示不同种类数据的集合。一般当我们需要用到数据的连通性时会用到它。
 * 并查集维护一个数组 parent，parent 数组中维护的不是元素本身，而是元素的根节点下标索引。
 * 按秩合并。 rank 数组中维护的是以 i 为根节点的子树的高度。合并时将元素所在深度小的集合合并到元素所在深度大的集合。
 * 路径压缩。在查找根节点时，若不是根节点，直接指向根节点。缩小数的高度
 * <p>
 * 「路径压缩」和「按秩合并」一起使用的时候，难以维护「秩」准确的定义，但依然具有参考价值。这是因为：虽然 rank 不是此时树的精确高度，但是不会出现树 a 的高度比树 b 结点高，但是树 a 的 rank 却比树 b 的 rank 低的情况。
 * <p>
 * 并查集是一种以树形结构来表示不同种类数据的集合。一般当我们需要用到数据的连通性时会用到它。
 * 并查集维护一个数组 parent，parent 数组中维护的不是元素本身，而是元素的根节点下标索引。
 * 按秩合并。 rank 数组中维护的是以 i 为根节点的子树的高度。合并时将元素所在深度小的集合合并到元素所在深度大的集合。
 * 路径压缩。在查找根节点时，若不是根节点，直接指向根节点。缩小数的高度
 * <p>
 * 「路径压缩」和「按秩合并」一起使用的时候，难以维护「秩」准确的定义，但依然具有参考价值。这是因为：虽然 rank 不是此时树的精确高度，但是不会出现树 a 的高度比树 b 结点高，但是树 a 的 rank 却比树 b 的 rank 低的情况。
 */

/**
 * 并查集是一种以树形结构来表示不同种类数据的集合。一般当我们需要用到数据的连通性时会用到它。
 * 并查集维护一个数组 parent，parent 数组中维护的不是元素本身，而是元素的根节点下标索引。
 * 按秩合并。 rank 数组中维护的是以 i 为根节点的子树的高度。合并时将元素所在深度小的集合合并到元素所在深度大的集合。
 * 路径压缩。在查找根节点时，若不是根节点，直接指向根节点。缩小数的高度
 *
 * 「路径压缩」和「按秩合并」一起使用的时候，难以维护「秩」准确的定义，但依然具有参考价值。这是因为：虽然 rank 不是此时树的精确高度，但是不会出现树 a 的高度比树 b 结点高，但是树 a 的 rank 却比树 b 的 rank 低的情况。
 */

/**
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
