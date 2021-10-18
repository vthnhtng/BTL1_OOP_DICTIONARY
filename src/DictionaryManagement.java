import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;

public class DictionaryManagement {

    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of word:");
        int n;
        n = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            Word word = new Word();
            System.out.print("\nEnter your word in English:");
            word.setWord_target(sc.next());
            System.out.print("\nEnter your word in Vietnamese:");
            word.setWord_explain(sc.next());
            dictionary.addWord(word);
        }
    }

    public void ShowAllWords() {
        this.dictionary.ShowAllWords();
    }
    public void InsertFromFile() {
        try {
            Path path = Path.of("C:\\Users\\Admin\\Desktop\\Dictionary\\src\\Database.txt");
            List<String> inputWordsList = Files.readAllLines(path);
            for (String word : inputWordsList) {
                String[] wordAttribute = word.split("-");
                Word newWord = new Word();
                newWord.setWord_target(wordAttribute[0]);
                newWord.setWord_explain(wordAttribute[1]);
                this.dictionary.addWord(newWord);
            }
            System.out.println("Inserted successfully");
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        };
    }

    public void dictionaryLookup(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter english word:");
            String search = sc.next();
            System.out.format("%-5s | %-15s | %-15s\n",
                    "No.", "English", "Vietnamese");
            int count = 0;
            for(Word iter : this.dictionary.wordList) {
                if (iter.getWord_target().contains(search)) {
                    count++;
                    System.out.format("%-5s | %-15s | %-15s\n",
                            count,
                            iter.getWord_target(),
                            iter.getWord_explain());
                }
            }
        }
    }
    //todo: khi tắt chương trình cần lưu lại list vào txt, khi mở chương trình thì tự động import file vào list để chạy hàm show words
}
