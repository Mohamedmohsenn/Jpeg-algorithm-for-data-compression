import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
	Node left;
	Node right;
	String value;
	int frequency;
	String nodeCode;

	public Node() {
		nodeCode = "";
	}

}

public class Hoffman {
	public ArrayList<Node> node = new ArrayList<Node>();
	public ArrayList<Node> finalNodes = new ArrayList<Node>();
	Node root = new Node();


	public Hoffman(ArrayList<String> arr) throws IOException {
		makeNodes(arr);
		makeTree();
		setNodeCode();
		FileWriter out = new FileWriter("hoffman table.txt");
		writePostorder(root , out);
		out.close();
	}

	public void makeNodes(ArrayList<String> arr) {

		ArrayList<String> visited = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			int counter = 1;
			if (!(visited.contains(arr.get(i)))) {
				visited.add(arr.get(i));
				for (int j = i + 1; j < arr.size(); j++) {
					if (arr.get(i).equals(arr.get(j))) {
						counter++;
					}
				}
				Node n = new Node();
				n.value = arr.get(i);
				n.frequency = counter;
				node.add(n);

			}
		}
	}

	private void sort(List<Node> arr) {
		int n = arr.size();

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (arr.get(j).frequency < arr.get(min).frequency) {
					min = j;
				}
			}

			int temp = arr.get(min).frequency;
			arr.get(min).frequency = arr.get(i).frequency;
			arr.get(i).frequency = temp;

			String temp2 = arr.get(min).value;
			arr.get(min).value = arr.get(i).value;
			arr.get(i).value = temp2;

			Node temp3 = arr.get(min).right;
			arr.get(min).right = arr.get(i).right;
			arr.get(i).right = temp3;

			Node temp4 = arr.get(min).left;
			arr.get(min).left = arr.get(i).left;
			arr.get(i).left = temp4;

		}
	}

	public void makeTree() {
		sort(node);
		while (node.size() != 1) {
			Node n = new Node();
			n.value = "--";
			n.right = node.get(0);

			n.left = node.get(1);
			node.remove(0);
			node.remove(0);
			n.frequency = n.right.frequency + n.left.frequency;
			node.add(n);
			sort(node);
			if (node.size() == 1) {
				root = n;
			}
		}
	}

	public void setNodeCode() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		root.nodeCode = "";
		while (!queue.isEmpty()) {

			Node tempNode = queue.poll();

			if (tempNode.left != null) {
				tempNode.left.nodeCode = tempNode.nodeCode + "0";
				queue.add(tempNode.left);
			}

			if (tempNode.right != null) {
				tempNode.right.nodeCode = tempNode.nodeCode + "1";
				queue.add(tempNode.right);
			}
		}
	}

	public void writePostorder(Node node , FileWriter out) throws IOException {
		if (node == null) {
			return;
		}

		writePostorder(node.left , out);

		writePostorder(node.right,out);
		if (node.value!="--") {
			finalNodes.add(node);
			out.write(node.value+"\n");
			out.write(node.nodeCode+"\n");	
		}
	}

}
