package view;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import model.Tree;
import model.TreeSize;

public class PineTreePainter extends TreePainter{

	public PineTreePainter(Pane pane, Tree tree) {
		super(pane, tree);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showTree() {
		// TODO Auto-generated method stub
		convertToScreenCoordinates();
			TreeSize size = tree.getSize();
		  switch (size) {
          case S:
        		createTree(1.0, Color.rgb(13, 205, 90), 6);
              break;
          case M:
      			createTree(1.5, Color.rgb(22, 174, 73), 5);
              break;
          case L:
        		createTree(2.0, Color.rgb(28, 140, 82), 4);
              break;
          case XL:
        		createTree(2.5, Color.rgb(30, 111, 79), 3);
              break;
          case XXL:
        		createTree(3.0, Color.rgb(31, 79, 81), 0);
              break;
          default:
              break;
		 }
					
	}
	
	public void createTree(double size, Color color, double position) {

        double scaledBranchWidth = 5 * size;
        double scaledBranchHeight = 30 * size;
        double scaledRadiusX = 45 * size;
        double scaledRadiusY = 40 * size;

        double branchX = 75 - scaledBranchWidth / 2;
        double branchY = 200 - scaledBranchHeight;
        double centerX = 80;
        double centerY = branchY - scaledRadiusY / 2;
        
        //this make tree branch
        Rectangle branch = new Rectangle(branchX + 5, branchY, scaledBranchWidth, scaledBranchHeight);  
        branch.setFill(Color.rgb(105, 38, 5));
        branch.setStroke(Color.BLACK);
        branch.setStrokeWidth(2);
        
        //this make the leaves
        Arc pine = new Arc(centerX, centerY, scaledRadiusX, scaledRadiusY, 250, 40);
        pine.setFill(color);
        pine.setType(ArcType.ROUND);
        pine.setStroke(Color.BLACK);
        pine.setStrokeWidth(2);
        
        //group the shapes
        Group shapesGroup = new Group(branch, pine);
        shapesGroup.setLayoutX(cordX);
        shapesGroup.setLayoutY(cordY - position);
        
        double scaledSize = tree.getRelY() / 120;
        shapesGroup.setScaleX(scaledSize);
        shapesGroup.setScaleY(scaledSize);
        pane.getChildren().add(shapesGroup);
	    
	   
	}

}
