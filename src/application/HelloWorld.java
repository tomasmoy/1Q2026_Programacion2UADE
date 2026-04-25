package application;
import java.util.Scanner;

public class HelloWorld extends Exercise{
	
	public HelloWorld(Scanner scanner){
		super(scanner);
	}

	@Override
	protected void exerciseLogic() {
		System.out.println("Ejercicio HelloWorld");
		running = false;
	}


}
