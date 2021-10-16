import java.util.Scanner;

public class DictionaryManagement {

    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of word:");
        int n;
        n = sc.nextInt();
        String s1, s2;
        for (int i = 0; i < n; i ++) {
            System.out.print("\nEnter your word in English:");
            s1 = sc.next();
            System.out.print("\nEnter your word in Vietnamese:");
            s2 = sc.next();
            Word word = new Word();
            word.setWord_target(s1);
            word.setWord_explain(s2);
            dictionary.addWord(word);
        }
    }

    public void ShowAllWords() {
        this.dictionary.ShowAllWords();
    }
    public void insertFromFile(){

    }
    public void dictionaryLookup(){

    }

}
