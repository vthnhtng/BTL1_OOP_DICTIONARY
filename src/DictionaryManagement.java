import java.util.Scanner;

public class DictionaryManagement {

    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Word word = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your word in English:");
        word.setWord_target(sc.nextLine());
        System.out.print("\nEnter your word in Vietnamese:");
        word.setWord_explain(sc.nextLine());
        dictionary.addWord(word);
    }

    public void ShowAllWords() {
        this.dictionary.ShowAllWords();
    }
}
