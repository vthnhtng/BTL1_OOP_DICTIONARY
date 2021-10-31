package com.example.dictionaryapplication;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8;

    @FXML
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    public void exitAlert() throws IOException {
        alert.setTitle("COMFIRMATION");
        alert.setHeaderText("Bạn có thể thấy dấu X ở góc phải của ứng dụng, bạn có thể thoát bằng cách ấn vào nút đó");
        alert.setContentText("Lựa chọn của bạn là:");

        ButtonType buttonTypeYes = new ButtonType("Tôi thích bấm ở đây", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Oke bạn", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeYes) {
            System.exit(0);
        }

    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        try {
            if (event.getSource() == login && (Filled() && isValid())) {
                stage = (Stage) login.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
            } else if (event.getSource() == button1) {
                stage = (Stage) button1.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
            } else if (event.getSource() == button2) {
                stage = (Stage) button2.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Bookmark.fxml"));
            } else if (event.getSource() == button3) {
                stage = (Stage) button3.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Add-New-Word.fxml"));
            } else if (event.getSource() == button4) {
                stage = (Stage) button4.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Delete-Word.fxml"));
            } else if (event.getSource() == button5) {
                stage = (Stage) button5.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Edit-Word.fxml"));
            } else if (event.getSource() == button6) {
                stage = (Stage) button6.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Exit.fxml"));
            } else if (event.getSource() == button7) {
                stage = (Stage) button7.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Search-Data.fxml"));
            } else {
                stage = (Stage) button8.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dictionaryManagement.insertFromFile();
        searcher.textProperty().addListener((observable, oldValue, newValue) -> {
            autoComplete();
        });
        setComboBox();
        setBookMark();
    }

    //translate:
    @FXML
    private ComboBox<String> from = new ComboBox<>();

    @FXML
    private ComboBox<String> to = new ComboBox<>();

    @FXML
    ObservableList<String> languages = FXCollections.
            observableArrayList(
                    "English", "Vietnamese",
                    "French", "Chinese",
                    "Japanese", "German",
                    "Dutch", "Korean", "Croatia", "Russian", "Thai", "Spanish");
    @FXML
    private TextArea in, out;

    @FXML
    public void setComboBox() {
        from.setItems(languages);
        to.setItems(languages);
    }

    @FXML
    public void translate(ActionEvent event) throws IOException {
        String fromLang = Dictionary.getFromLanguageFromFile(from.getValue());
        String toLang = Dictionary.getToLanguageFromFile(to.getValue());
        String translate = Translator.translate(fromLang,
                toLang, in.getText());
        out.clear();
        out.setText(translate);
        in.setWrapText(true);
        out.setWrapText(true);
        Word word = new Word();
        word.setWord_target(in.getText());
        word.setWord_explain(translate);
        dictionaryManagement.dictionary.addBookmark(word);
        dictionaryManagement.exportBookmark();
    }

    //login:
    @FXML
    private Label error_login;
    @FXML
    private PasswordField pass;

    @FXML
    private TextField user;

    @FXML
    private Button login;

    private String error = "";


    private boolean Filled() {
        if (user.getText().isEmpty()) {
            error_login.setText("Username is empty");
            if (pass.getText().isEmpty()) {
                error_login.setText("Username is empty\nPassword is empty");
            }
            return false;
        }
        if (pass.getText().isEmpty()) {
            error_login.setText("Password is empty");
            return false;
        }
        return true;
    }

    private boolean isValid() {
        if (user.getText().equals(DictionaryApplication.USERNAME_TUNG)) {
            if (pass.getText().equals(DictionaryApplication.PASSWORD)) {
                return true;
            }
        }
        if (user.getText().equals(DictionaryApplication.USERNAME_lONG)) {
            if (pass.getText().equals(DictionaryApplication.PASSWORD)) {
                return true;
            }
        }
        if (user.getText().equals(DictionaryApplication.USERNAME_KHOIMOM)) {
            if (pass.getText().equals(DictionaryApplication.PASSWORD)) {
                return true;
            }
        }
        error_login.setText("Username or Password is invalid");
        return false;
    }

    //add new word:
    @FXML
    private TextArea enWord = new TextArea();
    @FXML
    private TextArea vnWord = new TextArea();
    @FXML
    Alert notice = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public void saveNotice() {
        notice.setTitle("Add a new word");
        notice.setHeaderText("Your word '" + enWord.getText() + "' is added successfully");

        Optional<ButtonType> result = notice.showAndWait();
    }

    @FXML
    public void addNewWord() throws IOException {
        dictionaryManagement.addWord(enWord.getText(), vnWord.getText());
        dictionaryManagement.exportData();
        saveNotice();
    }

    //lookup:


    @FXML
    private TextArea result = new TextArea();

    @FXML
    private TextField searcher = new TextField();

    @FXML
    private ListView<String> suggestions = new ListView<>();

    @FXML
    Label seacherEmpty = new Label();

    @FXML
    void clearAll(ActionEvent e) {
        searcher.clear();
        result.clear();
    }

    @FXML
    public void clearListView() {
        if (searcher.getText().isEmpty()) {
            suggestions.getItems().clear();
        }
    }

    @FXML
    public void autoComplete() {
        try {
            clearListView();
            seacherEmpty.setText("");
            //select word in listview:
            ObservableList<String> elements = FXCollections.observableArrayList(dictionaryManagement.dictionarySearcherFX(searcher.getText()));
            suggestions.setItems(elements);
            suggestions.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                String selectedItem = suggestions.getSelectionModel().getSelectedItem();
                searcher.setText("");
                searcher.setText(selectedItem);
                });
            if (dictionaryManagement.dictionarySearcherFX(searcher.getText()).get(0).equals("Word not found!")) {
                seacherEmpty.setText("Word not found!");
                suggestions.getItems().clear();
            }
        } catch (java.lang.NullPointerException e) {
        } catch (java.lang.IndexOutOfBoundsException e) {
            searcher.setText("");
        }

    }
    @FXML
    Alert wordNotFound_Alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    public void lookUpAlert() throws IOException {
        wordNotFound_Alert.setTitle("THÊM TỪ MỚI");
        wordNotFound_Alert.setHeaderText("Hình như từ điển của chúng tôi chưa có từ này, bạn có muốn thêm từ này không:");
        wordNotFound_Alert.setContentText("Lựa chọn của bạn là:");

        ButtonType buttonTypeYes = new ButtonType("Có", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);

        wordNotFound_Alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = wordNotFound_Alert.showAndWait();

        if (result.get() == buttonTypeYes) {
            Stage stage;
            Parent root;
            stage = (Stage) button3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Add-New-Word.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void lookUp(ActionEvent event) throws IOException {
        if (searcher.getText().isEmpty()) {
            seacherEmpty.setText("Hãy nhập từ!");
        } else {
            result.setText(dictionaryManagement.lookUp(searcher.getText()));
            if (result.getText().equals("")) {
                lookUpAlert();
            }
        }
    }

    //delete:
    @FXML
    private TextArea word;

    @FXML
    Alert delAlert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public void deleteSuccess() {
        delAlert.setTitle("Delete a word");
        delAlert.setHeaderText("Your word '" + word.getText() + "' is deleted successfully");

        Optional<ButtonType> result = delAlert.showAndWait();
    }

    public void deleteFail() {
        delAlert.setTitle("Delete a word");
        delAlert.setHeaderText("Your word '" + word.getText() + "' is not deleted successfully");

        Optional<ButtonType> result = delAlert.showAndWait();
    }

    @FXML
    public void deleteWord() throws IOException {
        if (dictionaryManagement.deleteWordFx(word.getText())) {
            deleteSuccess();
        } else {
            deleteFail();
        }
        dictionaryManagement.exportData();
    }


    //Edit:
    @FXML
    private TextArea editWord;
    @FXML
    private TextArea newExplain;
    @FXML
    Alert editAlert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public void editSuccess() {
        editAlert.setTitle("Edit word");
        editAlert.setHeaderText("Your word '" + editWord.getText() + "' is edited successfully!");

        Optional<ButtonType> result = editAlert.showAndWait();
    }

    @FXML
    public void editFail() {
        editAlert.setTitle("Edit word");
        editAlert.setHeaderText("Your word '" + editWord.getText() + "' is not found!");

        Optional<ButtonType> result = editAlert.showAndWait();
    }

    public void changeToNewWord() throws IOException {
        boolean check;
        check = dictionaryManagement.editWordFX(editWord.getText(), newExplain.getText());
        if (check) {
            editSuccess();
        } else {
            editFail();
        }
        dictionaryManagement.exportData();
    }

    //Bookmark:
    @FXML
    private TextArea wordTarget = new TextArea();

    @FXML
    private TextArea wordExplain = new TextArea();

    @FXML
    public void setBookMark() {
        int i = 0;
        String left = "";
        String right = "";
        dictionaryManagement.insertFromBookmark();
        for (Word iter : dictionaryManagement.dictionary.Bookmark) {
            i++;
            left = left + i + "." + iter.getWord_target() + "\n";
            right = right + iter.getWord_explain() + "\n";
        }
        wordTarget.setText(left);
        wordExplain.setText(right);
    }

    //forgot password:
    @FXML
    Alert passWord = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public void forgotPassword() {
        alert.setTitle("FORGOT PASSWORD");
        alert.setHeaderText("Hãy nạp lần đầu để mở khóa chức năng này !");
        alert.setContentText("Anh bạn có những lựa chọn sau:");

        ButtonType buttonTypeYes = new ButtonType("Tài khoản: **** \nPassword:****", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNapLandau = new ButtonType("Liên hệ với admin Khôi Moãm qua SĐT: \n0886043077 để nạp lần đầu", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNapLandau);

        Optional<ButtonType> result = alert.showAndWait();
    }
}
