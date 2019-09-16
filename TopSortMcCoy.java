package A3P2;

//Donny McCoy 
//A3P2
//4/1/19
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class TopSortMcCoy {
	// recorder number of locations
	int numberOfNodes;
	// create a list to hold the diGraph
	static List<Integer> adjList[];
	// array that keeps track of the inDegree
	int[] inDegree = null;
	// make a printwriter for output
	static PrintWriter w = null;
	//Extra credit, Predicessors array 
	int[] Pred = null ; 

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) { // if there is not 2 files inputed

			System.out.print("Please input two files");
			System.exit(0);
		} else {
			File input = new File(args[0]);

			TopSortMcCoy T = new TopSortMcCoy();
			T.createDiGraph(input);
			// T.sort(adjList);
			T.sort(new File("C:\\Users\\Donny\\Desktop\\output.txt"));
		}
	}
	// End of main Method

	public TopSortMcCoy() {

	}

	public void createDiGraph(File dataFile) {
		// Create Scanner
		Scanner Sc = null;
		try {
			Sc = new Scanner(dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// First number in data is the number of nodes in the graph
		numberOfNodes = Sc.nextInt();
		// make linked list of size number of nodes
		adjList = (List<Integer>[]) new List[numberOfNodes + 1];
		// inDegree = new int [numberOfNodes + 1];
		inDegree = new int[numberOfNodes + 1];
		// put a linked list at each location
		for (int i = 1; i <= numberOfNodes; i++) {
			adjList[i] = new LinkedList<>();
			// System.out.print(i);
		}
		// Go through the remainder of the input lines
		while (Sc.hasNextLine()) {
			// First number is the origin of an edge
			int start = Sc.nextInt();
			// Second number is the destination of the edge
			int end = Sc.nextInt();
			// if the item edge is not already in the list
			if (!(adjList[start].contains(end))) {
				// add it to the list
				adjList[start].add(end);
				// add one to the position of the end number inDegree
				inDegree[end]++;
			}
		}
	}
	// End of createDigraph

	public void sort(File fileOut) throws FileNotFoundException {
		// create a stack
		Stack<Integer> stack = new Stack<Integer>();
		w = new PrintWriter(fileOut);
		// for each number node check if the inDegree
		for (int i = 1; i <= numberOfNodes; i++) {
			// if the inDegree is 0 push the node number onto the stack
			if (inDegree[i] == 0) {
				stack.push(i);
			}
		}

		int i = 1;
		int u;
		while (!stack.isEmpty()) {
			u = stack.pop();
			w.write(u + " ");
			i++;
			// go through every neighbor of u
			// for (int v = 1; v <= adjList[u].size(); v++) {
			for (int v : adjList[u]) {
				// decrease indegree by 1
				inDegree[v]--;

				// if the indegree turns to 0 put the node on the stack
				if (inDegree[v] == 0) {
					stack.push(v);
				}
			}

		}
		if (i > numberOfNodes)
			w.write("\nsorted sucessfully");
		else
			w.println();
			w.write("\nGraph contains a cylce");
		w.close();
	}

}
