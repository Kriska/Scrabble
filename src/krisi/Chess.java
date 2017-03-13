package krisi;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Chess extends Application  {
	private GUI gui;
	private BorderPane pane;
	
	
	@Override
    public void start(Stage primaryStage) {
		gui = new GUI(primaryStage);
		pane = new BorderPane();
	   gui.drawBoard();
	   pane.setPadding(new Insets(10,10,10,10));
       gui.getTextArea().setPrefSize(100,100);
       pane.setCenter(gui.getRoot()); 
       pane.setLeft(new VBox(gui.getConnect(), gui.getSend(), gui.getDraw(), gui.getApprove(), gui.getCancel(),gui.getHelp()));
       pane.setTop(gui.getLetters());
       pane.setBottom(gui.getTextArea());
       pane.setRight(new VBox(gui.getMe(), gui.getMeScore(), gui.getYou(), gui.getYouScore()));
       gui.functionality();	//initialize all functionality of buttons and so on
       Scene scene = new Scene(pane);
       scene.getStylesheets().add("/krisi/stylesheet.css");
       primaryStage.setScene(scene); 
       pane.setPrefSize(1024, 900);
       primaryStage.setResizable(false);
      primaryStage.show();
    	
    }

    public static void main(String[] args) {
    	launch(args);
    }
}
