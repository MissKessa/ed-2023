package graphs;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Prim {

	@Test
	void test() {
		Graph<Integer> graph = new Graph<Integer>(6);

		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);

		graph.addEdge(1, 2, 1);
		graph.addEdge(2, 1, 1);
		graph.addEdge(1, 3, 3);
		graph.addEdge(3, 1, 3);
		graph.addEdge(1, 4, 3);
		graph.addEdge(4, 1, 3);

		graph.addEdge(6, 2, 1);
		graph.addEdge(2, 6, 1);
		graph.addEdge(3, 2, 2);
		graph.addEdge(2, 3, 2);
		graph.addEdge(4, 2, 2);
		graph.addEdge(2, 4, 2);

		graph.addEdge(3, 4, 1);
		graph.addEdge(4, 3, 1);
		graph.addEdge(3, 5, 2);
		graph.addEdge(5, 3, 2);

		graph.addEdge(5, 4, 3);
		graph.addEdge(4, 5, 3);
		graph.addEdge(6, 4, 2);
		graph.addEdge(4, 6, 2);

		ArrayList<String> T = graph.prim(1);
		for (String text : T) {
			System.out.println(text);
		}
		System.out.println("__________________");
//		Graph prim = graph.prim(1);

	}

	@Test
	void test2() {
		Graph<Integer> graph = new Graph<Integer>(6);

		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);

		graph.addEdge(1, 2, 2);
		graph.addEdge(2, 1, 2);
		graph.addEdge(1, 3, 3);
		graph.addEdge(3, 1, 3);
		graph.addEdge(1, 5, 2);
		graph.addEdge(5, 1, 2);

		graph.addEdge(3, 2, 2);
		graph.addEdge(2, 3, 2);

		graph.addEdge(3, 5, 1);
		graph.addEdge(5, 3, 1);
		graph.addEdge(3, 4, 1);
		graph.addEdge(4, 3, 1);

		graph.addEdge(5, 4, 2);
		graph.addEdge(4, 5, 2);

		ArrayList<String> T = graph.prim(3);

		for (String text : T) {
			System.out.println(text);
		}
//		Graph prim = graph.prim(3);

	}

}
