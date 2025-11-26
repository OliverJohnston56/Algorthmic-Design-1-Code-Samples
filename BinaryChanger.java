/*
 * Oliver Johnston
 */
import java.util.Scanner; 
public class BinaryChanger {

	public static void main(String[] args) {
		//takes the users two 4-bit binary numbers
		System.out.println("Enter a 4-bit binary number.");
		Scanner keyboard = new Scanner(System.in);
		String bin1 = keyboard.nextLine();
		System.out.println("Enter another 4-bit binary number.");
		String bin2 = keyboard.nextLine();
		//converts the first binary number to decimal
		int bin1_0 = Integer.parseInt(bin1.substring(0,1));
		int bin1_1 = Integer.parseInt(bin1.substring(1,2));
		int bin1_2 = Integer.parseInt(bin1.substring(2,3));
		int bin1_3 = Integer.parseInt(bin1.substring(3));
		int num1 = bin1_0*8+bin1_1*4+bin1_2*2+bin1_3;
		//converts the second binary number to decimal
		int bin2_0 = Integer.parseInt(bin2.substring(0,1));
		int bin2_1 = Integer.parseInt(bin2.substring(1,2));
		int bin2_2 = Integer.parseInt(bin2.substring(2,3));
		int bin2_3 = Integer.parseInt(bin2.substring(3));
		int num2 = bin2_0*8+bin2_1*4+bin2_2*2+bin2_3;
		//prints the two numbers
		System.out.println("The first number is "+num1);
		System.out.println("The second number is "+num2);
		//adds the two numbers and prints the result
		int result = num1+num2;
		System.out.println("Their sum is "+result);
		
		keyboard.close();

	}

}
