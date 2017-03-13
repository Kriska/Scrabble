package krisi;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorDialog {
	private String message;
	private Button button;
	
	public ErrorDialog(String message) {
		this.setMessage(message);
		this.button = new Button("OК");
		Stage dialogStage = new Stage();
	    dialogStage.initModality(Modality.WINDOW_MODAL);

	    VBox vbox = new VBox(new Text(message), button);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(15));

	    button.setOnAction(e->dialogStage.close());
	    vbox.setOnKeyPressed(new EventHandler<KeyEvent>() {

	    	@Override
	    	public void handle(KeyEvent event) {
	    		if(event.getCode() == KeyCode.ENTER) {
	    			dialogStage.close();
	    		}
	    	}
	    });
	    dialogStage.setScene(new Scene(vbox));
	    dialogStage.show();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
