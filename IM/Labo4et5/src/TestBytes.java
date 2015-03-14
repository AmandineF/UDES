public class TestBytes
{    
	public static void main(String[] argv) {
		String input = "01000001";
		String output = new Character((char)Integer.parseInt(input, 2)).toString();
		System.out.println(output);
 
	}
}