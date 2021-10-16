import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public void dictionaryBasic() {
        int option;
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        do {
            System.out.println("option:");
            System.out.println("1.Add new word");
            System.out.println("2.Show all words");
            System.out.println("3.Exit");
            option = sc1.nextInt();
            if (option == 1) {
                this.dictionaryManagement.insertFromCommandline();
            } else if (option == 2) {
                this.dictionaryManagement.ShowAllWords();
            }
        } while (option != 3);

    }
}
