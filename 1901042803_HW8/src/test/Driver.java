package test;

import Questions.Q1.MyGraph;
import Questions.Q1.Vertex;
import Questions.Q2;
import Questions.Q3;

import java.util.Arrays;
import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        MyGraph mumu = new MyGraph(true);

        Vertex target = mumu.newVertex("one", 500);
        mumu.addVertex(target);
        target.addProperty("farm", "big");

        target = mumu.newVertex("two", 5000);
        mumu.addVertex(target);
        target.addProperty("farm", "big");

        target = mumu.newVertex("three", 50);
        mumu.addVertex(target);

        target = mumu.newVertex("four", 5);
        mumu.addVertex(target);

        target = mumu.newVertex("five", 5000);
        mumu.addVertex(target);
        target.addProperty("farm", "big");

        mumu.addEdge(0, 1, 10);
        mumu.addEdge(1, 3, 10);
        mumu.addEdge(2, 0, 10);
        mumu.addEdge(4, 1, 10);
        mumu.addEdge(3, 4, 10);
        mumu.addEdge(4, 2, 10);
        mumu.addEdge(0, 4, 9);

        mumu.removeVertex("Three");
        for (Double[] vertex : mumu.exportMatrix())
            System.out.print(Arrays.deepToString(vertex) + "\n");
        System.out.print("\n");
        mumu.printGraph();
        System.out.print("\n");
        mumu.filterVertices("farm", "big").printGraph();

        Random rand = new Random();

        for (int i = 0; i < 20; ++i) {
            Vertex newV = mumu.newVertex("", 10);
            newV.setBoost(rand.nextDouble(25));
            mumu.addVertex(newV);
        }

        System.out.print("\nQ2:\n" + Q2.BFS_DFS_Dif(mumu));

        for (int j = 0; j < 100; ++j)
            mumu.addEdge(rand.nextInt(25), rand.nextInt(25), rand.nextInt(100));

        System.out.print("\n\nQ3\n" + Q2.calc_BFS(mumu, 3));
        System.out.print("\n" + Q3.calc_Dijkstra(mumu, 3));
        System.out.print("\n");

//        MyGraph mumu2 = new MyGraph(true);
//        mumu2.addVertex(mumu2.newVertex("", 10));
//        mumu2.addVertex(mumu2.newVertex("", 10));
//        mumu2.addVertex(mumu2.newVertex("", 10));
//        mumu2.addVertex(mumu2.newVertex("", 10));
//        mumu2.addVertex(mumu2.newVertex("", 10));
//        mumu2.addVertex(mumu2.newVertex("", 10));
//
//        mumu2.addEdge(0, 3, 5);
//        mumu2.addEdge(0, 1, 3);
//        mumu2.addEdge(1, 2, 7);
//        mumu2.addEdge(3, 2, 1);
//        mumu2.addEdge(2, 5, 7);
//        mumu2.addEdge(2, 4, 2);
//
//        System.out.print("\nQ2:\n" + Q2.BFS_DFS_Dif(mumu2));
    }
}
