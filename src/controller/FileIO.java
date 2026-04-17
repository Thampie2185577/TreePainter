package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Tree;

public class FileIO {
	

	public File loadPaintings(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./Resources/paintings"));
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Custom files (*.painting)", "*.painting");
		fileChooser.getExtensionFilters().add(extFilter);
		File paintFile = fileChooser.showOpenDialog(stage);
		return paintFile;
	}
	
	public void savePaintings(Stage stage, ArrayList<Tree> trees) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./Resources/paintings"));
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Custom files (*.painting)", "*.painting");
		fileChooser.getExtensionFilters().add(extFilter);
		File paintFile = fileChooser.showSaveDialog(stage);
		
		//add convert trees value into string array
		ArrayList<String> sTrees = new ArrayList<String>();
		for (Tree tree : trees) {
			sTrees.add(tree.getType().toString().toLowerCase() + ':' 
					+ tree.getSize().toString() 
					+ ":" + (int)tree.getRelX() + ":"+ (int)tree.getRelY());
		}
		
		if (paintFile != null) {
             try(FileWriter fileWriter = new FileWriter(paintFile)) {
				for (String sTree : sTrees) {
					fileWriter.write(sTree + System.lineSeparator());					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
	}
	
	
	public Font getFontFile(String font, int size) {
		return Font.loadFont(getClass().getResourceAsStream("/fonts/"+font+".ttf"), size);
	}
		
}
