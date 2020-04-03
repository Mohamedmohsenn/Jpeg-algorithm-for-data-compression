import java.io.IOException;
import java.util.ArrayList;


public class Main {
	public static void main(String args[]) throws IOException {
		Jpeg j = new Jpeg();
		System.out.println(j.Compression("-2,0,0,2,0,0,3,2,0,1,0,0,-2,0,-1,0,0,1,0,0,-1,000000000.........0000"));
	}
}
