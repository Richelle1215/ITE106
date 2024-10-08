import java.io.FileReader;

public class GFG{
	public static void main(String[]args) throws Exception
	{
	
		FileReader fr = new FileReader(
			("C:\\user\\pankaj\\Destop\\test.txt")
			
			int i = 0;
			
			while((i - fr.read()) !=-1){
			
				System.out.println((char)i);	
			}		
	}
