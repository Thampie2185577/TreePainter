package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.TreeSize;
import model.TreeType;
import model.World;
import view.PaintingScene;

public class Controller extends Application {
	
	private World world;
	private PaintingScene scene;
    private Stage stage;
	private FileIO fileIO;
    
	@Override
	public void start(Stage primaryStage) {
		try {
			
			this.world = new World();
			fileIO = new FileIO();
			scene = new PaintingScene(this);
			
			Font font = fileIO.getFontFile("GreatVibes", 35);
			scene.paintingPane.setTextFont(font);
						
			primaryStage.setScene(scene);
			primaryStage.setTitle("Thampie Anthonyrajah Painting");
			primaryStage.centerOnScreen();
			primaryStage.show();
			
			stage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startup(String[] args) {
		launch(args);
	}
	
	
	//all tree methods
	public EventHandler<ActionEvent> addLeafTree() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				leafTree();
			}
		};
	};
	
	public EventHandler<ActionEvent> addPineTree() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pineTree();
			}
		};
			
	};
	
	//create 100 trees
	public EventHandler<ActionEvent> addHundredTrees() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (int i = 100; i> 0; i--) {
					TreeType type = TreeType.values()[new Random().nextInt(1 - 0 + 1) + 0];
					
					if(type == TreeType.LEAF) {
						leafTree();
					}
					else if(type == TreeType.PINE) {
						pineTree();
					}
					
				
				}
			}
		};
		
	};
	
	//make a leaf tree and show on canvas
	public void leafTree() {
		world.addTree(TreeType.LEAF, 
				TreeSize.values()[new Random().nextInt(4 - 0 + 1) + 0],
				new Random().nextInt(100 - 0 + 1) + 0, 
				new Random().nextInt(100 - 80 + 1) + 80);
				
		scene.paintingPane.setTrees(world.getTrees());
		scene.paintingPane.drawTrees();
	}
	
	//make a pine tree and show on canvas
	public void pineTree() {
		world.addTree(TreeType.PINE, 
				TreeSize.values()[new Random().nextInt(4 - 0 + 1) + 0],
				new Random().nextInt(100 - 0 + 1) + 0, 
				new Random().nextInt(100 - 80 + 1) + 80);
		
		scene.paintingPane.setTrees(world.getTrees());
		scene.paintingPane.drawTrees();
	}
	
	public EventHandler<ActionEvent> clearAllTrees() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				scene.paintingPane.clearTrees();
				world.cleartTrees();
			}
		};
	};
	
	//all file methods
	//load trees from a file
	public EventHandler<ActionEvent> loadTrees() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			
				//first clear all trees.
				scene.paintingPane.clearTrees();
				world.cleartTrees();
				
				File file = fileIO.loadPaintings(stage);
				Scanner scanner = null;
				if (file != null) {
					try {
						scanner = new Scanner(file);
						while (scanner.hasNext()) {
							String line = scanner.nextLine();
							String[] lines  = line.split(":");
												
							world.addTree(TreeType.valueOf(lines[0].toUpperCase()), 
									TreeSize.valueOf(lines[1].toUpperCase()), 
									Double.parseDouble(lines[2]), 
									Double.parseDouble(lines[3]));
							
							scene.paintingPane.setTrees(world.getTrees());
							scene.paintingPane.drawTrees();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
	}
	
	
	//save current trees
	public EventHandler<ActionEvent> saveTrees() {
		
		return new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fileIO.savePaintings(stage, world.getTrees());
			}
			
		};
		
	}
	

	//close application
	public EventHandler<ActionEvent> exitApp() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.close();
				running = false;
				try {
		            movieThread.join(); 
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			}
			
		};
	}
	
	
	//run the movie
	public ChangeListener<Boolean> playOrStop(){
		return new ChangeListener<Boolean>() {
			
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			
				if (newValue) {
                    playMovie();
                } else {
                    stopMovie();
                }
			}
		};
		
	}
	
	private Thread movieThread;
	private  boolean running = false;
	private void playMovie() {
		running = true;
		movieThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
	
				while (running) {
					Platform.runLater(() -> {
						//generate of screen new trees when film is running
						world.addTree(TreeType.values()[new Random().nextInt(1 - 0 + 1) + 0], 
								TreeSize.values()[new Random().nextInt(4 - 0 + 1) + 0],
								new Random().nextInt(-10 - -100 + 1) + -100, 
								new Random().nextInt(100 - 50 + 1) + 50);
						
						world.moveTrees();
                        scene.paintingPane.setTrees(world.getTrees());
                        scene.paintingPane.drawTrees();
                    });
					
					 try {
	                     Thread.sleep(1000 /24);
	                 } catch (InterruptedException e) {
	                     e.printStackTrace();
	                 }
				}
			}
		});
		movieThread.start();
	}
	
	
	private void stopMovie() {
		running = false;
		try {
            movieThread.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	
	//all font methods
	public EventHandler<ActionEvent> setFontGreatVibes(){
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Font font = fileIO.getFontFile("GreatVibes", 35);
				scene.paintingPane.setTextFont(font);
			}
		};
		
	}
	
	public EventHandler<ActionEvent> setFontHomeMadeApple(){
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Font font = fileIO.getFontFile("HomemadeApple", 20);
				scene.paintingPane.setTextFont(font);
			}
		};
	}
	
	public EventHandler<ActionEvent> setFontQuikHand(){
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Font font = fileIO.getFontFile("QuikHand", 30);
				scene.paintingPane.setTextFont(font);
			}
		};
	}
	
	
	
	
}
