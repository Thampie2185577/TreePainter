package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class World {
	
	private ArrayList<Tree> trees;
		
	public World() {
		// TODO Auto-generated constructor stub
		trees = new ArrayList<Tree>();
	}
	
	public void addTree(TreeType type, TreeSize size , double relX, double relY) {
		Tree tree = new Tree();
		tree.setType(type);	
		tree.setSize(size);
		tree.setRelX(relX); 
		tree.setRelY(relY);

		trees.add(tree);
		sortTrees();
	}
	

	public void moveTrees() {
	
		for (Tree tree : trees) {
			double relY = tree.getRelY();
            double moveValue = 10 * ((50 - relY) / 50);
            tree.move(moveValue);
            
		}
		
	}

	public void sortTrees() {
		Collections.sort(trees, new Comparator<Tree>() {
			@Override
			public int compare(Tree tree1, Tree tree2) {
				// TODO Auto-generated method stub
				double tr1RelY = tree1.getRelY();
				double tr2RelY = tree2.getRelY();
				
				double comp = Double.compare(tr1RelY, tr2RelY);
				
				if (comp != 0) {return (int) comp;} 

				TreeSize tr1Size = tree1.getSize();
				TreeSize tr2Size = tree2.getSize();
	              
	            return tr1Size.compareTo(tr2Size);
			}
			
		});
		
		trees.sort(Comparator.comparingDouble(Tree::getRelY));
	}
	
	public void cleartTrees() {
		this.trees = new ArrayList<Tree>();
	}
	
	public ArrayList<Tree> getTrees() {
		return trees;
	}
	
}
