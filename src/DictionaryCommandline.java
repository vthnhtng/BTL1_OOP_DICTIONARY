import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public void dictionaryBasic() {
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("option:");
            System.out.println("1.Add new word");
            System.out.println("2.Show all words");
            System.out.println("3.Advanced options");
            System.out.println("4.Exit");
            option = sc.nextInt();
            if (option == 1) {
                this.dictionaryManagement.insertFromCommandline();
            } else if (option == 2) {
                this.dictionaryManagement.ShowAllWords();
            } else if (option == 3) {
                this.dictionaryAdvanced();
            }
        } while (option != 4);

    }
    public void dictionaryAdvanced() {
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Advanced option:");
            System.out.println("1.Insert from database");
            System.out.println("2.Dictionary lookup");
            System.out.println("3.Return");
            option = sc.nextInt();
            if (option == 1) {
                this.dictionaryManagement.InsertFromFile();
            } else if (option == 2) {
                this.dictionaryManagement.dictionaryLookup();
            }
        } while(option != 3);
    }
}
