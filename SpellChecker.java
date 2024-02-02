
public class SpellChecker {


	public static void main(String[] args) {
		//System.out.println(levenshtein("hell0", "hello"));
		String word = args[0];

		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);

	}

	public static int levenshtein(String word1, String word2) {
		int levboth;
		int leva;
		int levb;

		if (word1.length()==0){
			return word2.length();
		}
		if (word2.length() == 0){
			return word1.length();
		}
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		leva = levenshtein(tail(word1), word2);
		levb = levenshtein(word1, tail(word2));
		levboth = levenshtein(tail(word1), tail(word2));

		

		if(word1.charAt(0)==word2.charAt(0)){
			return levboth;
		} 
		else{
			if (leva <= levb){
				if (leva <= levboth){
					return 1+leva;
				}
				else{
					return 1+levboth;
				}

			}
			else if (levb <= levboth){
				return 1+levb;
			}
			else{
				return 1+levboth;
			}	
			}
			
		}
		

	

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		String word ="";
		In in = new In(fileName);
		for (int i = 0; i<dictionary.length;i++){
			word = in.readString();
			dictionary[i] = word;
		}


		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int min = levenshtein(word, dictionary[0]);
		String closeString = dictionary[0];
		int current = min;
		for(int i=1;i<dictionary.length;i++){
			current = levenshtein(word, dictionary[i]);
			if (current<min){
				min = current;
				closeString = dictionary[i];

				
			}
			if (current == min && word.length()==dictionary[i].length())
				closeString = dictionary[i];

		}
		if (min <= threshold){
			return closeString;
		}
		else{
			return word;
		}

	}

}
