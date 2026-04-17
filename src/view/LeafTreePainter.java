package view;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import model.Tree;
import model.TreeSize;

public class LeafTreePainter extends TreePainter{

	public LeafTreePainter(Pane pane, Tree tree) {
		super(pane, tree);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showTree() {
		// TODO Auto-generated method stub
		convertToScreenCoordinates();
		TreeSize size = tree.getSize();
		switch (size) {
		case S :
			createTree(1.0, Color.rgb(7,251,9), 4);
			break;
		case M :
			createTree(1.5, Color.rgb(1,209,0), 3);
			break;
		case L :
			createTree(2.0, Color.rgb(2,164,1), 2);
		break;
		case XL :
			createTree(2.5, Color.rgb(0,120,0), 1);
		break;
		case XXL :
			createTree(3.0, Color.rgb(1,74,1), 0);
		break;
		
		default:
			break;
		}
	}
	
	
	public void createTree(double size, Color color, double position) {

        double scaledBranchWidth = 5 * size;
        double scaledBranchHeight = 25 * size;
        double scaledRadius = 20 * size;

        double branchX = 75 - scaledBranchWidth / 2;
        double branchY = 200 - scaledBranchHeight;
        double centerX = 80;
        double centerY = branchY - scaledRadius / 2;
        
        //this make tree branch
        Rectangle branch = new Rectangle(branchX + 5, branchY, scaledBranchWidth, scaledBranchHeight);  
        branch.setFill(Color.rgb(169, 75, 12));
        branch.setStroke(Color.BLACK);
        branch.setStrokeWidth(2);
        
        //this make the leaves
        Arc leaves = new Arc(centerX, centerY, scaledRadius, scaledRadius, 0, 360);
        leaves.setFill(color);
        leaves.setStroke(Color.BLACK);
        leaves.setStrokeWidth(2);
        
        //group the shapes
        Group shapesGroup = new Group(branch, leaves);
        
        double scaledSize = tree.getRelY()/ 120;
        shapesGroup.setScaleX(scaledSize);
        shapesGroup.setScaleY(scaledSize);
        
        shapesGroup.setLayoutX(cordX);
        shapesGroup.setLayoutY(cordY - position);
        
        pane.getChildren().add(shapesGroup);
		        
	}
}
