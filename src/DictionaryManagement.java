import java.io.*;
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
        for (int i = 0; i < n; i++) {
            Scanner sc1 = new Scanner(System.in);
            Word word = new Word();
            System.out.print("\nEnter your word in English:");
            word.setWord_target(sc1.nextLine());
            System.out.print("\nEnter your word in Vietnamese:");
            word.setWord_explain(sc1.nextLine());
            dictionary.addWord(word);
        }
    }

    public void ShowAllWords() {
        this.dictionary.ShowAllWords();
    }

    public void InsertFromFile() {
        try {
            Path path = Path.of("src\\Database.txt");
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
        }
    }

    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        int run = 1;
        boolean check = false;
        while (run == 1) {
            System.out.println("Enter english word:");
            String search = sc.next();
            for (Word iter : this.dictionary.wordList) {
                if (iter.getWord_target().equals(search)) {
                    check = true;
                    System.out.format("%-15s | %-15s\n",
                            "English", "Vietnamese");
                    System.out.format("%-15s | %-15s\n",
                            iter.getWord_target(),
                            iter.getWord_explain());
                    break;
                }
            }
            if(!check){
                System.out.println("Not found!");
            }
            System.out.println("1.Continue");
            System.out.println("2.Return");
            run = sc1.nextInt();
        }
    }

    public void dictionarySearcher() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        int run = 1;
        boolean check = false;
        while (run == 1) {
            System.out.println("Enter english word:");
            String search = sc.next();
            System.out.format("%-5s | %-15s | %-15s\n",
                    "No.", "English", "Vietnamese");
            int count = 0;
            for (Word iter : this.dictionary.wordList) {
                if (iter.getWord_target().contains(search)) {
                    count++;
                    check = true;
                    System.out.format("%-5s | %-15s | %-15s\n",
                            count,
                            iter.getWord_target(),
                            iter.getWord_explain());
                }
            }
            if(!check){
                System.out.println("Not found!");
            }
            System.out.println("1.Continue");
            System.out.println("2.Return");
            run = sc1.nextInt();
        }
    }

    public void translate() throws Exception {
        try {
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Translate from: ");
            String fromLang = sc.next();
            fromLang = getLanguageFromFile(fromLang);
            System.out.println("To: ");
            String toLang = sc.next();
            toLang = getLanguageFromFile(toLang);
            System.out.println("Enter your word:");
            String s = sc1.nextLine();
            System.out.println(Translator.translate(fromLang, toLang, s));
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        }
    }

    public String getLanguageFromFile(String lang){
        try {
            Path path = Path.of("src\\Languages.txt");
            List<String> inputLanguages = Files.readAllLines(path);
            for (String language : inputLanguages) {
                String[] split = language.split(": ");
                if(split[1].equals(lang)){
                    return split[0];
                }
            }
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        }
        return "en";
    }

    public void editWord() {
        this.dictionary.edit();
    }

    public void deleteWord() {
        this.dictionary.delete();
    }

    public void exportFile() throws IOException {
        PrintWriter writer = null;
        writer = new PrintWriter(new File("src\\Database.txt"));
        for (Word iter : this.dictionary.wordList) {
            writer.write(iter.getWord_target() + "-" + iter.getWord_explain() + "\n");
        }
        writer.flush();
        writer.close();
        System.out.println("Successful");
    }
}
