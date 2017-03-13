package krisi;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI {
	private GridPane root;
	private List<Tile> tiles;
	private Button connect;
	private Button send;
	private Button draw;
	private Button approve;
	private Button cancel;
	private Button help;
	private Label me;
	private TextArea meScore;
	private Label you;
	private TextArea youScore;
	private TextArea textArea;
	private Text letters;
	private Utility utility;
	
	private Stage primaryStage;
	
	
	public GUI(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		root = new GridPane();
		tiles = new ArrayList<>();
		connect = new Button("Свържи се");
		textArea = new TextArea();
		setSend(new Button("Изпрати"));
		setDraw(new Button("Изтегли"));
		setApprove(new Button("Одобри"));
		setCancel(new Button("Откажи"));
		setLetters(new Text("Букви: "));
		
		help = new Button("Инфо");
		setUtility(new Utility());
		me = new Label("Aз:");
		you = new Label("Опонент:");
		meScore = new TextArea("0");
		meScore.setEditable(false);
		youScore = new TextArea("0");
		youScore.setEditable(false);
		connect.setPrefSize(150,20);
		send.setPrefSize(150,20);
		draw.setPrefSize(150,20);
		approve.setPrefSize(150,20);
		cancel.setPrefSize(150, 20);
		help.setPrefSize(150, 20);
		
		meScore.setPrefSize(20, 40);
		youScore.setPrefSize(20, 40);
		
		letters.setId("letters");
		
	}

	public Button getHelp() {
		return help;
	}

	public void setHelp(Button help) {
		this.help = help;
	}

	public GridPane getRoot() {
		return root;
	}
	public void setRoot(GridPane root) {
		this.root = root;
	}
	public List<Tile> getTiles() {
		return tiles;
	}
	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	public Button getConnect() {
		return connect;
	}
	public void setConnect(Button connect) {
		this.connect = connect;
	}
	public TextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}
	public Button getSend() {
		return send;
	}

	public void setSend(Button send) {
		this.send = send;
	}

	public Button getDraw() {
		return draw;
	}

	public void setDraw(Button draw) {
		this.draw = draw;
	}

	public Button getApprove() {
		return approve;
	}

	public void setApprove(Button approve) {
		this.approve = approve;
	}

	public Text getLetters() {
		return letters;
	}

	public void setLetters(Text letters) {
		this.letters = letters;
	}

	public void functionality() {
		draw.setOnAction(e->letters.setText(utility.lettersBar(letters.getText())));
		connect.setOnAction(e-> utility.connect(primaryStage));
		help.setOnAction(e-> utility.help(primaryStage));
		textArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String headerTitle = letters.getText().substring(0, 6);
				String substring = letters.getText().substring(6);
				if(!event.getCharacter().equals("\b")) {
					if(!substring.contains(event.getCharacter())) {
						@SuppressWarnings("unused")
						ErrorDialog errorDialog = new ErrorDialog("Нямаш тази буква");
						textArea.setText(""); 
						while(!utility.getBuffer().isEmpty()) {//ВЪРНИ ВСИЧКИ ОСТАНАЛИ ГОРЕ
							letters.setText(letters.getText().concat(utility.getBuffer().pop()));
						}
					} else { //ако натиснеш някоя буква
						letters.setText(headerTitle + substring.replace(event.getCharacter(), "")); //махния от полето с букви горе
						utility.getBuffer().push(event.getCharacter()); //ДОБАВИ Я В БУФЕРА
					}
				} else {
					if(!utility.getBuffer().isEmpty()) {
					letters.setText(letters.getText().concat(utility.getBuffer().pop()));
					}
				}
			}
		});
		//докато textArea.getText() е празно да не може да се пише по дъската
		for (Tile tile : tiles) {
			tile.disableProperty().bind(
				        textArea.textProperty().isEqualTo(""));
		}
		//докато textArea.getText() не е празно да не може да се натиска draw button
		for (Tile tile : tiles) {
			draw.disableProperty().bind(
				        textArea.textProperty().isNotEmpty());
		}
		//TODO: изпрати(смята точки, праща на другия tiles от този)
		//TODO: одобри(чисти текстarea, добавя точките в youScore, 
		//праща тази сума в meScore на другия клиент) 
		//TODO: откажи(извежда попъп в другия клиент записаната дума не е валида
		//връща тва от textarea горе, човекът трябва сам да си зачисти дъската!!! )
	}
	public void drawBoard() {
		root.setHgap(1);
		root.setVgap(1);
		root.setPadding(new Insets(10,10,10,10));
        final int size = 20;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                Tile square = new Tile();
                String color ;
                TextArea tmp = new TextArea();
                if(row == 0 && col == 0 || row == 0 && col == 1 || row == 1 && col == 0
            			|| row == 0 && col== size-2 || row == 0 && col == size-1 || row == 1 && col == size-1
            			|| row == size-1 && col == size-1 || row == size-1 && col == size-2 || row == size-2 && col == size-1
            			|| row == size-1 && col== 0 || row == size-1 && col == 1 || row == size-2 && col == 0) {
                	color = "white";
                	tmp.setEditable(false);
                }
            	else if (row < size/2 && col < size/2 || row >= size/2 && col >= size/2) {
                	color = "#6c8e6c"; //тъмно зелено
                }
            	else { 
                	color = "#90be90"; //светло зелено
                }
                square.setStyle("-fx-background-color: "+color+";");
                square.getChildren().add(tmp);
            	tiles.add(square);
                root.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
        	root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        	root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
	}

	public Utility getUtility() {
		return utility;
	}

	public void setUtility(Utility utility) {
		this.utility = utility;
	}

	public Label getMe() {
		return me;
	}

	public void setMe(Label me) {
		this.me = me;
	}

	public Label getYou() {
		return you;
	}

	public void setYou(Label you) {
		this.you = you;
	}

	public TextArea getMeScore() {
		return meScore;
	}

	public void setMeScore(TextArea meScore) {
		this.meScore = meScore;
	}

	public TextArea getYouScore() {
		return youScore;
	}

	public void setYouScore(TextArea youScore) {
		this.youScore = youScore;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
    
}
