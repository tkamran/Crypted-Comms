package ccsNew;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ccgui extends Application {
	
	private boolean isServer = false;
	
	private TextArea messages = new TextArea();
	private serverConnect connection = isServer ? createServer() : createClient();
	
	
	private Parent createContent() {
		
		messages.setPrefHeight(400);
		
		// Dis-allow editing
		messages.setEditable(false);
		
		// The two lines below dis-allow touching of the textArea
//		messages.setDisable(true);
//		messages.setStyle("-fx-opacity: 1;");
		
		TextField input = new TextField();
		
		input.setOnAction(event -> {
			String message = isServer ? "Server: " : "Client: ";
			
			message += input.getText();
			input.clear();
			
			messages.appendText(message + "\n");
			
			try {
				connection.send(message);
				
			} catch (Exception e) {
				
				messages.appendText("Failed to Send" + "\n");
			}
			
		});
		
		Button buttonE = new Button("Encrypt");
		
		buttonE.setOnAction(action -> {
			String text = input.getText();
			//System.out.println(text);
			
			CaesarCipher obj = new CaesarCipher();
			String varE = obj.encrypt(text, 10);
			System.out.println(varE);
		});
		
		Button buttonD = new Button ("Decrypt");
		
		buttonD.setOnAction(action -> {
			String text = input.getText();
			
			CaesarCipher obj = new CaesarCipher();
			String varD = obj.decrypt(text, 10);
			System.out.println(varD);
		});

		Button clearAll = new Button("Clear All");
		clearAll.setOnAction(action -> {
		    messages.clear();
		    
		    // These might help clear the decrpyed text
//		    messages.replaceSelection(replacement);
//		    messages.replaceText(range, text);
//		    messages.replaceText(start, end, text);
		});
		
		
		
		VBox root = new VBox(20, messages, input, buttonE, buttonD, clearAll);
		root.setPrefSize(600, 300);
		return root;
		
		
		
	}
	
	public void init() throws Exception {
		connection.startConnection();
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("CCS Chat Box");
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		
	}
	
	public void stop() throws Exception {
		connection.closeConnection();
	}
	
	private Server createServer() {
		return new Server(55555, data -> {
			Platform.runLater(() -> {
				messages.appendText(data.toString() + "\n");
			});
		});
	}
	
	private Client createClient() {
		return new Client("127.0.0.1", 55555, data -> {
			Platform.runLater(() -> {
				messages.appendText(data.toString() + "\n");
			});
		});
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

}



/*
package ccsNew;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ccgui extends Application {
	
	private boolean isServer = false;
	
	private TextArea messages = new TextArea();
	private serverConnect connection = isServer ? createServer() : createClient();
	
	
	private Parent createContent() {
		
		messages.setPrefHeight(400);
		TextField input = new TextField();
		input.setOnAction(event -> {
			String message = isServer ? "Server: " : "Client: ";
			
			message += input.getText();
			input.clear();
			
			messages.appendText(message + "\n");
			
			try {
				connection.send(message);
				
			} catch (Exception e) {
				
				messages.appendText("Failed to Send" + "\n");
			}
			
		});
		
		VBox root = new VBox(20, messages, input);
		root.setPrefSize(600, 300);
		return root;
		
		
	}
	
	public void init() throws Exception {
		connection.startConnection();
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		
	}
	
	public void stop() throws Exception {
		connection.closeConnection();
	}
	
	private Server createServer() {
		return new Server(55555, data -> {
			Platform.runLater(() -> {
				messages.appendText(data.toString() + "\n");
			});
		});
	}
	
	private Client createClient() {
		return new Client("127.0.0.1", 55555, data -> {
			Platform.runLater(() -> {
				messages.appendText(data.toString() + "\n");
			});
		});
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

}
*/