package mx.dijkstra;


/**
 * �������ɵı�
 * @author Dewey
 *
 */
public class Edge {
	/**
	 * ���
	 */
	private Point start;
	/**
	 * ������
	 */
	private Point end;
	/**
	 * Ȩ��
	 */
	private int weight;
	
	public Edge(Point start,Point end,int weight){
		this.start=start;
		this.end=end;
		this.weight=weight;
	}
	public Point getStart() {
		return start;
	}
	public void setStart(Point start) {
		this.start = start;
	}
	public Point getEnd() {
		return end;
	}
	public void setEnd(Point end) {
		this.end = end;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
