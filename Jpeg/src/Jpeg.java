import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Jpeg {
	ArrayList<String> input = new ArrayList<>();
	ArrayList<String> inputAfterSplit = new ArrayList<>();
	ArrayList<String> hoffmanString = new ArrayList<>();
	ArrayList<String> binaryString = new ArrayList<>();
	ArrayList<String> finalCompression = new ArrayList<>();
	ArrayList<String> finalDecompression = new ArrayList<>();

	public Jpeg() {
		
	}

	private void convertToBinary() {
		for (int i = 0; i < inputAfterSplit.size(); i++) {
			String s = "";
			if (inputAfterSplit.get(i).length() > 2) {
				if (inputAfterSplit.get(i).charAt(
						inputAfterSplit.get(i).length() - 3) != '-') {
					int num = Character.getNumericValue(inputAfterSplit.get(i)
							.charAt(inputAfterSplit.get(i).length() - 2));
					s = Integer.toBinaryString(num);
					binaryString.add(s);
				} else {
					int num = Character.getNumericValue(inputAfterSplit.get(i)
							.charAt(inputAfterSplit.get(i).length() - 2));
					s = Integer.toBinaryString(num);
					StringBuilder s2 = new StringBuilder(s);
					for (int j = 0; j < s.length(); j++) {
						if (s.charAt(j) == '0')
							s2.setCharAt(j, '1');
						else if (s.charAt(j) == '1') {
							s2.setCharAt(j, '0');
						}
					}
					binaryString.add(s2.toString());
				}
			} else {
				int num = Character.getNumericValue(inputAfterSplit.get(i)
						.charAt(inputAfterSplit.get(i).length() - 2));
				s = Integer.toBinaryString(num);
				binaryString.add(s);
			}
		}
	}

	String Compression(String inn) throws IOException {

		input.clear();
		String[] inp = inn.split(",");
		for (int i = 0; i < inp.length; i++) {
			input.add(inp[i]);
		}
		String str = "";
		for (int i = 0; i < input.size(); i++) {
			str += input.get(i);
			str += ",";
			if (input.get(i).charAt(0) != '0') {
				inputAfterSplit.add(str);
				str = "";
			}
		}

		for (int i = 0; i < inputAfterSplit.size(); i++) {
			int counter = 0;
			for (int j = 0; j < inputAfterSplit.get(i).length(); j++) {
				if (inputAfterSplit.get(i).charAt(j) == '0')
					counter++;
			}

			if (inputAfterSplit.get(i).charAt(
					inputAfterSplit.get(i).length() - 2) != '1') {
				String st = Integer.toString(counter) + '/' + '2';
				hoffmanString.add(st);
			} else {
				String st = Integer.toString(counter) + '/' + '1';
				hoffmanString.add(st);
			}

			if (i == inputAfterSplit.size() - 1) {
				hoffmanString.add("EOB");
			}
		}
		convertToBinary();
		Hoffman h = new Hoffman(hoffmanString);

		for (int i = 0; i < hoffmanString.size(); i++) {
			String s = "";
			for (int j = 0; j < h.finalNodes.size(); j++) {
				if (hoffmanString.get(i).equals(h.finalNodes.get(j).value)) {
					if (i != hoffmanString.size() - 1) {
						s = h.finalNodes.get(j).nodeCode + ","
								+ binaryString.get(i);
					} else {
						s = h.finalNodes.get(j).nodeCode;
					}
					finalCompression.add(s);
					break;
				}
			}
		}
		String z = "";
		for (int i = 0; i < finalCompression.size(); i++) {
			z += finalCompression.get(i) + " ";
		}
		return z;
	}

	public String deCompression(String inn) throws IOException {
		finalDecompression.clear();
		input.clear();
		hoffmanString.clear();
		ArrayList<Node> temp = new ArrayList<>();
		ArrayList<String> allData = new ArrayList<>();
		ArrayList<Integer> finalValues = new ArrayList<>();
		File f = new File("hoffman table.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String st;
		while ((st = br.readLine()) != null)
			allData.add(st);

		for (int i = 0; i < allData.size(); i += 2) {
			Node n = new Node();
			n.value = allData.get(i);
			n.nodeCode = allData.get(i + 1);
			temp.add(n);
		}

		String[] inp = inn.split(" ");

		String[] inp2 = null;
		for (int i = 0; i < inp.length; i++) {
			inp2 = inp[i].split(",");
			for (int j = 0; j < inp2.length; j++) {
				input.add(inp2[j]);
			}
		}

		for (int i = 0; i < input.size(); i += 2) {
			for (int j = 0; j < temp.size(); j++) {
				if (input.get(i).equals(temp.get(j).nodeCode)) {
					hoffmanString.add(temp.get(j).value);
					break;
				}
			}
			if (i == input.size() - 1)
				break;

			if (input.get(i + 1).charAt(0) == '1') {
				int decimal = Integer.parseInt(input.get(i + 1), 2);
				finalValues.add(decimal);
			} else {
				StringBuilder s2 = new StringBuilder(input.get(i + 1));
				for (int j = 0; j < input.get(i + 1).length(); j++) {
					if (input.get(i + 1).charAt(j) == '0')
						s2.setCharAt(j, '1');
					else if (input.get(i + 1).charAt(j) == '1') {
						s2.setCharAt(j, '0');
					}
				}
				int decimal = Integer.parseInt(s2.toString(), 2);
				decimal = decimal - (2 * decimal);
				finalValues.add(decimal);
			}
		}

		for (int i = 0; i < finalValues.size(); i++) {
			String str = "";
			int num = Character
					.getNumericValue((hoffmanString.get(i).charAt(0)));
			for (int j = 0; j < num; j++) {
				str += "0" + ",";
			}

			str += finalValues.get(i).toString() + ",";
			finalDecompression.add(str);
		}

		finalDecompression.add("0000000000");

		String z = "";
		for (int i = 0; i < finalDecompression.size(); i++) {
			z += finalDecompression.get(i);

		}
		return z;

	}

}
