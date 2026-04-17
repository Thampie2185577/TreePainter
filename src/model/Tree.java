package model;

public class Tree {
	
	private TreeSize size;
	private TreeType type;
	
	private double relX;
	private double relY;
		
	public void move(double relX) {
		this.relX += relX;
	}
	
	public double getRelX() {
		return relX;
	}
	
	public double getRelY() {
		return relY;
	}
	
	public TreeSize getSize() {
		return size;
	}
	
	public TreeType getType() {
		return type;
	}
	
	public void setRelX(double relX) {
		this.relX = relX;
	}

	public void setRelY(double relY) {
		this.relY = relY;
	}
	
	public void setSize(TreeSize size) {
		this.size = size;
	}
	
	public void setType(TreeType type) {
		this.type = type;
	}
	
}
