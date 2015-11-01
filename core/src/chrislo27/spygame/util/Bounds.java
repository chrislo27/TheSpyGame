package chrislo27.spygame.util;


public class Bounds {

	public float x = 0;
	public float y = 0;
	public float width = 1, height = 1;
	
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
