import com.xcvgsystems.hypergiant.eventscript.*;

public class ESDriver
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
		
		ESEngine engine = new ESEngine();
		System.out.println(engine.tokenizeLine("$variable++"));
		System.out.println(engine.tokenizeLine("6 + 4 * 2"));
		System.out.println(engine.tokenizeLine("++5"));
		System.out.println(engine.tokenizeLine("5++"));
		System.out.println(engine.tokenizeLine("56.27283 + 177772823 - 1 % 3"));
		System.out.println(engine.tokenizeLine("6+4*2"));
		System.out.println(engine.tokenizeLine("6 + 4 * build((2+3)*5)"));
		System.out.println(engine.tokenizeLine("\"Hello!\""));
		System.out.println(engine.tokenizeLine("under_score(subexpr) + $under_score"));
	}

}
