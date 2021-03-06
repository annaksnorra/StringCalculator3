package is.ru.stringcalculator;
import java.util.regex.Pattern;

public class Calculator {

	public static int add(String text) {
		
		if(text.equals("")){
			return 0;
		}
		else if(text.contains("//"))  {
			return sum(splitCustomDelimeter(text));
		}
		else if(text.contains("\n") && text.contains(",")) {
			return sum(splitMultiple(text));
		}
		else if(text.contains(",")){
			return sum(splitNumbers(text));
		}
		else if(text.contains("\n")){
			return sum(newLine(text));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",");
	}

	private static String[] newLine(String numbers){
	    return numbers.split("\n");
	}

	private static String[] splitMultiple(String numbers) {
    	String splitter = "[\n,]+";
    	String[] simbols = numbers.split(splitter);
    		return simbols;
    }

    private static String[] splitCustomDelimeter(String numbers) {
       	String[] array = newLine(numbers);
        array[0] = array[0].replace("//","");
        array[0] = array[0].replace("[","");
        String[] delimeters = array[0].split("]");

        String delimeter = "";
        for(String d : delimeters)
        {
            d = Pattern.quote(d);
            delimeter += d + "|";
        }

        String[] test = array[1].split(delimeter);
    		return array[1].split(delimeter);
    }

    private static int sum(String[] numbers) {
 	    int total = 0;
 	    String errormsg = "Negatives not allowed: ";
 	    boolean isError = false;

        for(String number : numbers){
        	if(number.contains("-")) {
        		errormsg += number + ",";
        		isError = true;
        	}
        	else {
		    	if(!number.isEmpty())
                {
                    int n = toInt(number);
                    if (n <= 1000)
                        total += n;
                }
		    }
		}

		if(isError)
			throw new IllegalArgumentException(errormsg);

		return total;
    }

}