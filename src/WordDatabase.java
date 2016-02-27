import java.util.ArrayList;
import java.util.List;

/**
 * Database to store meaning and language for a given word ID
 */
public class WordDatabase {

	private List<String> meaning;
	private List<String> language;

	/**
	 * Constructs a new database.
	 */
	public WordDatabase() {
		meaning = new ArrayList<String>();
		language = new ArrayList<String>();
	}

	/**
	 * add meaning and language to its lists
	 * 
	 * @param meaning
	 * @param language
	 */
	public void addEntry(String meaning, String language) {
		(this.meaning).add(meaning);
		(this.language).add(language);
	}

	/**
	 * returns meaning for a wordID
	 * 
	 * @param wordID
	 * @return meaning
	 */
	public String getMeaning(int wordID){
		return meaning.get(wordID);
	}

	/**
	 * returns language for a wordID
	 * 
	 * @param wordID
	 * @return language
	 */
	public String getLanguage(int wordID){
		return language.get(wordID);
	}

	/**
	 * checks whether tow words have the same meaning
	 * 
	 * @param wordID1
	 * @param wordID2
	 * @return bool
	 */
	public boolean sameMeaning(int id1, int id2) {

		if(id1 == -1)
			System.out.println("first word ID not set");

		if(id2 == -1)
			System.out.println("second word ID not set");

		if( (meaning.get(id1)).equals(meaning.get(id2)) )
			return true;
		else
			return false;
	}

}
