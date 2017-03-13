package krisi;
import java.util.Random;
import java.util.Stack;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utility {
	private String letters;
	private int score;
	private Stack<String> buffer;
	public Utility() {
		letters = new String();
		setBuffer(new Stack<>());
	}
	public void drawRandomLetters(int size) {
		Random random = new Random();
		char[] c= new char[size];
		for (int i = 0; i < size; i++) {
		 c[i] =mapper(  (char) (random.nextInt(30)+ 'a') );
		}
		String a = new String(c);
		StringBuilder sb = new StringBuilder();
		sb.append(a);
		letters = a.toString().toUpperCase();
	}
	private char mapper(char c) {//convert english to bulgarian 
		switch(c){
		case 'a': return 'я';
		case 'b': return 'б';
		case 'c': return 'в';
		case 'd': return 'г';
		case 'e': return 'д';
		case 'f': return 'е';
		case 'g': return 'ж';
		case 'h': return 'з';
		case 'i': return 'и';
		case 'j': return 'к';
		case 'k': return 'л';
		case 'l': return 'м';
		case 'm': return 'Н';
		case 'n': return 'О';
		case 'o': return 'П';
		case 'p': return 'Р';
		case 'q': return 'С';
		case 'r': return 'Т';
		case 's': return 'У';
		case 't': return 'Ф';
		case 'u': return 'Х';
		case 'v': return 'Ц';
		case 'w': return 'Ч';
		case 'x': return 'Ш';
		case 'y': return 'Щ';
		case 'z': return 'Ъ';
		case '{': return 'Ь';
		case '|': return 'Ю';
		case '}': return 'Й';
		default: return 'а';
		}
	}
	public String getLetters() {
		return letters;
	}
	public void setLetters(String letters) {
		this.letters = letters;
	}
	
	private void calcScore(String word) {
		word.toLowerCase();
		int sum = 0;
		for(int i = 0; i < word.length(); i++) {
			switch(word.charAt(i)) {
			case 'а':
			case 'е':
			case 'и':
			case 'н':
			case 'о':
			case 'п':
			case 'р':
			case 'с':
			case 'т': sum+=1;break;
			case 'б':
			case 'в':
			case 'д':
			case 'к':
			case 'л':
			case 'м': sum+=2;break;
			case 'г':
			case 'ъ': sum+=3;break;
			case 'ж':
			case 'з': sum+=4;break;
			case 'у':
			case 'х':
			case 'ч':
			case 'й':
			case 'я': sum+=5; break;
			case 'ш':
			case 'ц':
			case 'ю': sum+=8;break;
			case 'щ':
			case 'ф':
			case 'ь': sum+=10;break;
			default: sum+=1;	
			}
		}
		score = sum;
	}
	//BUTONS UTILITIES
	public void connect(Stage primaryStage) {
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20); 
        Text info = new Text();
        info.setId("info");
        info.setText("Kристина Гочева\n2017");
        dialogVbox.getChildren().add(info);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialogScene.getStylesheets().add("/krisi/stylesheet.css");
        dialog.setScene(dialogScene);
        dialog.show();
	}
	public String lettersBar(String text)  {
		StringBuilder sb = new StringBuilder();
		sb.append(text);
		if(text.length() - 7 == 0){
			drawRandomLetters(12); //starting number of letters
		}else {
			drawRandomLetters(Math.abs(19 - text.length())); //12 - existing letters - 7 ( for "букви: " label)
		}
		sb.append(letters);
		return sb.toString();
	}
	public void help(Stage primaryStage) {
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        Text info = new Text();
        info.setId("info");
        info.setText("Kристина Гочева\n2017");
        dialogVbox.getChildren().add(info);
        Scene dialogScene = new Scene(dialogVbox, 100, 100);
        dialogScene.getStylesheets().add("/krisi/stylesheet.css");
        dialog.setScene(dialogScene);
        dialog.show();
	}
	public Stack<String> getBuffer() {
		return buffer;
	}
	public void setBuffer(Stack<String> buffer) {
		this.buffer = buffer;
	}
}
