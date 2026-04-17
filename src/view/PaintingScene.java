package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PaintingScene extends Scene{
	private BorderPane root;
	private Controller controller;
	public  PaintingPane paintingPane;
	private MenuBar bar;
	
	public PaintingScene(Controller controller) {
		super(new Pane(), 800, 600);
		// TODO Auto-generated constructor stub
		this.controller = controller;
		this.bar = createMenu();
		this.paintingPane = new PaintingPane(this, bar);
		root = new BorderPane();
		
		this.root.setTop(bar);
		this.root.setCenter(paintingPane);
		
		this.setRoot(root);
	}
	
	private MenuBar createMenu() {
		MenuBar bar = new MenuBar();
		
		Menu fileMenu = fileMenu();
		Menu treeMenu = treeMenu();
		Menu fontMenu = fontMenu();
		Menu movieMenu = movieMenu();
		
		bar.getMenus().addAll(fileMenu, treeMenu, fontMenu, movieMenu);
		return bar;
	}
	
	
	private Menu fileMenu() {
		//file menu
		Menu fileMenu = new Menu("File");
		
		MenuItem loadItem = new MenuItem("load painting...");
		loadItem.setOnAction(this.controller.loadTrees());
		
		MenuItem saveItem = new MenuItem("save painting as...");
		saveItem.setOnAction(this.controller.saveTrees());
		
		MenuItem exitItem = new MenuItem("exit");
		exitItem.setOnAction(this.controller.exitApp());
		
		fileMenu.getItems().addAll(loadItem, saveItem, exitItem);
		
		return fileMenu;
	}
	
	
	private Menu treeMenu() {
		//tree menu
		Menu treeMenu = new Menu("Tree");
		MenuItem addLeafTreeItem = new MenuItem("add Leaf Tree");
		addLeafTreeItem.setOnAction(this.controller.addLeafTree());
		
		MenuItem addPineTreeItem = new MenuItem("add Pine Tree");
		addPineTreeItem.setOnAction(this.controller.addPineTree());
		
		MenuItem addHundredTreesItem = new MenuItem("add 100 Trees");
		addHundredTreesItem.setOnAction(this.controller.addHundredTrees());
		
		MenuItem clearAllTreesItem = new MenuItem("Clear all Tree");
		clearAllTreesItem.setOnAction(this.controller.clearAllTrees());
		
		treeMenu.getItems().addAll(addLeafTreeItem, addPineTreeItem, addHundredTreesItem, clearAllTreesItem);
		
		return treeMenu;
	}
	
	private Menu movieMenu() {
		Menu movieMenu = new Menu("Movie");
		CheckMenuItem playItem = new CheckMenuItem("play");
		playItem.selectedProperty().addListener(this.controller.playOrStop());
		
		movieMenu.getItems().add(playItem);
		
		return movieMenu;
	}
	
	private Menu fontMenu() {
		Menu fontMenu = new Menu("Autograph font");
		ToggleGroup toggleGroup = new ToggleGroup();
		
		RadioMenuItem itemGreatVibes = new RadioMenuItem("Great Vibes");
		itemGreatVibes.setToggleGroup(toggleGroup);
		itemGreatVibes.selectedProperty().set(true);
		itemGreatVibes.setOnAction(this.controller.setFontGreatVibes());
		
		RadioMenuItem itemHomeMadeApple = new RadioMenuItem("HomemadeApple");
		itemHomeMadeApple.setToggleGroup(toggleGroup);
		itemHomeMadeApple.setOnAction(this.controller.setFontHomeMadeApple());
		
		RadioMenuItem itemQuikhand = new RadioMenuItem("Quikhand");
		itemQuikhand.setToggleGroup(toggleGroup);
		itemQuikhand.setOnAction(this.controller.setFontQuikHand());
		
		fontMenu.getItems().addAll(itemGreatVibes, itemHomeMadeApple, itemQuikhand);
		
		return fontMenu;
	}
	
}
