package mx.dijkstra;

/**
 * ·��
 * @author Dewey
 */
public class Dist{
	/**
	 * ·�̵Ŀ��� 
	 */
	private int weight;
	/**
	 * ·�̵� 
	 */
	private Point point;
	/**
	 * ǰһ����
	 */
	private Dist preDist;
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public Dist getPreDist() {
		return preDist;
	}
	public void setPreDist(Dist preDist) {
		this.preDist = preDist;
	}
}
