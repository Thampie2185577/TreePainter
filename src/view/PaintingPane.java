package view;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Tree;
import model.TreeType;


public class PaintingPane extends Pane{
	
	private Canvas canvas;
	private ArrayList<Tree> trees;
	
	private Text text;
	
	public PaintingPane(Scene scene, MenuBar bar) {
		// TODO Auto-generated constructor stub
		this.setWidth(scene.getWidth());
		this.setHeight(scene.getHeight() - bar.getHeight());
		this.widthProperty().addListener(new widthListener());
		this.heightProperty().addListener(new heightListener());
		
		text = new Text("Thampie Anthonyrajah");
		text.setFont(new Font(20)); 
		text.layoutXProperty().bind(this.widthProperty().subtract(this.getWidth() / 3));
	    text.layoutYProperty().bind(this.heightProperty().subtract(20));
		
		canvas = new Canvas(this.getWidth(), this.getHeight());
		
		//bind canvas width and height property to pane width and height
		canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
		
        setBackgroundCanvas();
		
		this.getChildren().addAll(canvas, text);
		
	}
	
	//create background to the canvas
	public void setBackgroundCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawSky(gc, canvas.getWidth(), canvas.getHeight());
		drawGround(gc, canvas.getWidth(), canvas.getHeight(), canvas.getHeight() /2);
	}
	
	//this draws a sky
	private void drawSky(GraphicsContext gc, double canvasWidth, double canvasHeight) {
	    gc.setFill(Color.rgb(208, 255, 244));
	    gc.fillRect(0, 0, canvasWidth, canvasHeight / 2);
	}
	
	//this draws the ground
	private void drawGround(GraphicsContext gc, double canvasWidth, double canvasHeight, double posY) {
	    gc.setFill(Color.rgb(210, 144, 8));
	    gc.fillRect(0, posY, canvasWidth, canvasHeight / 2);
	}
	
	
	public void setTrees(ArrayList<Tree> trees) {
		this.trees = trees;
	}
	
	public void drawTrees() {
		clearTrees();
		if(trees != null) {
			for (Tree tree : trees) {
				if(tree.getType() == TreeType.LEAF) {
					drawLeafTree(tree);
				}
				else if(tree.getType() == TreeType.PINE) {
					drawPineTree(tree);
				}
			}
		}
	}
	
	private void drawLeafTree(Tree tree) {
		LeafTreePainter leafTreePainter = new LeafTreePainter(this, tree);
		leafTreePainter.showTree();
		resetText();
	}
		
	private void drawPineTree(Tree tree) {
		PineTreePainter pineTreePainter = new PineTreePainter(this,  tree);
		pineTreePainter.showTree();
		resetText();
	}
		
	public void clearTrees() {
		this.getChildren().clear();
		setBackgroundCanvas();
		this.getChildren().addAll(canvas, text);
	}
	
	
	private class widthListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			// TODO Auto-generated method stub
			setBackgroundCanvas();
			drawTrees();
		}
		
	}
	
	private class heightListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
			// TODO Auto-generated method stub
			setBackgroundCanvas();
			drawTrees();
		}
		
	}
	
	//this method set Text again
	public void resetText() {
		this.getChildren().remove(text);
		this.getChildren().add(text);
	}
		
	public void setTextFont(Font customFont) {
        text.setFont(customFont);
    }

}
