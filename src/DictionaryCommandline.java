import java.util.Scanner;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public DictionaryCommandline() {
        this.dictionaryManagement.InsertFromFile();
        this.dictionaryManagement.InsertFromBookmark();
    }
    public void dictionaryBasic() throws Exception{
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
        this.dictionaryManagement.exportFile();
        this.dictionaryManagement.exportBookmark();
    }
    public void dictionaryAdvanced() throws Exception{
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Advanced option:");
            System.out.println("1.Insert from database");
            System.out.println("2.Dictionary lookup");
            System.out.println("3.Translation");
            System.out.println("5.EditWord");
            System.out.println("6.DeleteWord");
            System.out.println("7.Searcher");
            System.out.println("4.Return");
            option = sc.nextInt();
            if (option == 1) {
                this.dictionaryManagement.InsertFromFile();
            } else if (option == 2) {
                this.dictionaryManagement.dictionaryLookup();
            } else if (option == 3) {
                this.dictionaryManagement.translate();
            } else if (option == 5) {
                this.dictionaryManagement.editWord();
            } else if (option == 6) {
                this.dictionaryManagement.deleteWord();
            } else if (option == 7){
                this.dictionaryManagement.dictionarySearcher();
            }else if (option == 8){
                this.dictionaryManagement.ShowAllBookmark();
            }
        } while(option != 4);
    }
}
