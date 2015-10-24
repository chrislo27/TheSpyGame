package chrislo27.spygame.util;


public class Bounds {

	public float x, y, width, height;
	
	public Bounds(){
		
	}
	
	public Bounds(float x, float y, float w, float h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public static interface Boundable{
		
		public Bounds getBounds();
		
	}
	
}
