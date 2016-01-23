package utilities;

import java.util.Random;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class Generic {
	public static enum Mode {
		AlphaLC, AlphaUC, AlphaNumeric, Numeric 
	}

	public static String generateRandomString(int length, Mode mode) {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch(mode){

		case AlphaLC:
			characters = "abcdefghijklmnopqrstuvwxyz";
			break;
			
		case AlphaUC:
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case AlphaNumeric:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;

		case Numeric:
			characters = "123456789";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	public static int getRandomInteger(int aStart, int aEnd){
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    Random random = new Random();
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * random.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);
	    return randomNumber;
	  }
	
	public static String[] shuffleStringArray(String[] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      String a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	    return ar;
	  }
}
