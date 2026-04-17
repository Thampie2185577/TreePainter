package view;

import javafx.scene.layout.Pane;
import model.Tree;

public abstract class TreePainter {
	
	public Tree tree;
	public double cordX;
	public double cordY;
	public Pane pane;
	
	public TreePainter(Pane pane, Tree tree) {
		// TODO Auto-generated constructor stub
		this.tree = tree;
		this.pane = pane;

	}
	
    public void convertToScreenCoordinates() {
    	cordX = (((pane.getWidth() / 100) * tree.getRelX() ) - 80);
    	cordY = ((pane.getHeight() / 100) * tree.getRelY()) / 1.5;
    }

    
	abstract public void showTree();
}
