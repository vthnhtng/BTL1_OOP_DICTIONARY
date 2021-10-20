import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    public List<Word> wordList = new ArrayList<>();
    public List<Word> Bookmark = new ArrayList<>();
    private int size;
    Dictionary() {
        size = 0;
    }

    public void addWord(Word word) {
        wordList.add(word);
        size++;
    }
    public void addBookmark(Word word) {
        Bookmark.add(word);
    }
    public int getSize() {
        return this.size;
    }

    public void ShowAllWords() {
        System.out.format("%-5s | %-15s | %-15s\n",
                "No.", "English", "Vietnamese");
        for (int i = 0; i < this.getSize(); i++) {
            System.out.format("%-5s | %-15s | %-15s\n",
                    i + 1,
                    this.wordList.get(i).getWord_target(),
                    this.wordList.get(i).getWord_explain());

        }
    }
    public void ShowAllBookmark() {
        System.out.format("%-5s | %-15s | %-15s\n",
                "No.", "English", "Vietnamese");
        int i = 0;
        for (Word iter: Bookmark) {
            System.out.format("%-5s | %-15s | %-15s\n",
                    i + 1,
                    iter.getWord_target(),
                    iter.getWord_explain());
            i++;

        }
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter word edit needed:");
        String target = sc.next();
        boolean check = false;
        for(Word iter : this.wordList) {
            if(iter.getWord_target().equals(target)) {
                check = true;
                System.out.println("Enter new explain:");
                String explain = sc.next();
                iter.setWord_explain(explain);
            }
        }
        if (!check) {
            System.out.println("deo co tu day trong tu dien");
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter delete word:");
        String deletion = sc.next();
        boolean check = false;
        for(Word iter : this.wordList) {
            if(iter.getWord_target().equals(deletion)) {
                check = true;
                this.wordList.remove(iter);
                this.size = size-1;
                break;
            }
        }
        if (!check) {
            System.out.println("Deo co tu day trong tu dien");
        }
    }



}
