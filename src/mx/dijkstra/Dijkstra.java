package mx.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * ���� Dijkstra ̰���㷨�����·��Ѱ�� �ȳ�ʼ�� init ��ʼ��һ�� ֻ���Ե���һ�� dijkatra����.�ٴε��������³�ʼ��
 * @author Dewey
 */
public class Dijkstra {

	/**
	 * ��ļ�ֵ����,��ʶ����㵽ÿ�����Ȩ���ܺ�
	 */
	private Map<Point, Integer> pointKeys = new HashMap<Point, Integer>();
	/**
	 * �߼���,�������һ����.������ʾ��Щ�������ͨ�������Ȩ��.
	 */
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	/**
	 * ����㵽ÿ��������·������,���㷨ִ�����ʱ,���汣���Ŵ���㵽����������·��
	 */
	private Map<Point, Dist> paths = new HashMap<Point, Dist>();
	/**
	 * ��ʾ�����ĳ���
	 */
	private static final int INFINITY = 99999;

	/**
	 * ������м���
	 */
	private void reset() {
		pointKeys.clear();
		edges.clear();
		paths.clear();
	}

	/**
	 * ��ʼ��
	 * 
	 * @param source ��ļ���
	 * @param edegs �ߵļ���
	 * @param start Ѱ�������
	 */
	private void init(ArrayList<Point> source, ArrayList<Edge> edegs, Point start) {
		reset();
		this.edges = edegs;
		for (int i = 0; i < source.size(); i++) {// ȡ��ÿ����
			Point v = source.get(i);
			// ��ÿ��������ֵ����
			if (v.equals(start)) {// ��ǰ��Ϊ���
				// ��㵽���ļ�ֵΪ0
				pointKeys.put(v, 0);
			} else {
				// ��������Ϊ�����
				pointKeys.put(v, INFINITY);
			}
			// ��ʼ������㵽����·��
			Dist dist = new Dist();
			dist.setPoint(start);// ·�̵�Ϊ���
			dist.setPreDist(null);// ǰһ����Ϊnull
			dist.setWeight(0);// ·�̿���Ϊ0
			paths.put(start, dist);// ����·������
		}
	}

	/**
	 * dijkstra �㷨ʵ��
	 * @param source ��
	 * @param edegs ��
	 * @param start Ѱ�������
	 * @param end ��ҪѰ���յ�
	 * @return
	 */
	public Stack<Point> dijkstra(ArrayList<Point> source, ArrayList<Edge> edegs, Point start, Point end) {
		init(source, edegs, start);
		Integer endPointKey = null;// �ӵ�ǰ��������һ���� ��һ����ԭ������Ҫ�Ŀ���
		while (pointKeys.size() > 0) {// ��ֵ���������ڵĵ����0
			Point minkeyPoint = getMinKey(pointKeys);// ��õ�ǰ��ֵ����ӵ����С��ֵ(����)�ĵ� ��Ϊ��ǰ��
			int keyValue = pointKeys.get(minkeyPoint);// ���ӵ����С��ֵ��ļ�ֵ
			ArrayList<Edge> adjacentEdegs = getEdegs(edges, minkeyPoint);// Ѱ������������ڵı�
			for (Edge edge : adjacentEdegs) {// �������ڵı�
				int currentKey = keyValue + edge.getWeight();// ����㵽��ǰ��Ŀ��� + ͨ�������ߵ���һ����Ŀ��� ��Ϊ��ǰ����
				endPointKey = pointKeys.get(edge.getEnd());// ��ȡ����㵽��ǰ�߶����Ŀ���
				if (endPointKey == null) {// ����ȡ���� ��˵������ĵ��Ѿ��������ڼ�ֵ���� ͨ���Ǹ���·
					continue;// �����Ǹ���· �� ���ɴ�
				}
				updatePath(endPointKey, edge, currentKey);// ����·��
			}
			pointKeys.remove(minkeyPoint);
		}
		return getPaths(end);
	}

	/**
	 * ����·��
	 * @param endPointKey
	 * @param edge
	 * @param currentKey
	 */
	private void updatePath(Integer endPointKey, Edge edge, int currentKey) {
		Dist advance = null;// ÿ��Ѱ�����ߵ�·�� ����
		if (currentKey < endPointKey) {// ����ǰ���� С�� ԭ������㵽�߶����Ŀ��� ����¶Ե�ļ�ֵ
			pointKeys.put(edge.getEnd(), currentKey);// ����ԭ���ļ�ֵ
			advance = new Dist();// �����µ�·��
			advance.setPoint(edge.getEnd());// ���Ϊ ����ߵĵ�
			advance.setPreDist(paths.get(edge.getStart()));// ǰһ����Ϊ��ǰ��
			advance.setWeight(edge.getWeight());// ·�̳���Ϊ�ߵ�Ȩ��
			paths.put(edge.getEnd(), advance);// ����·������
		}
	}

	/**
	 * �㷨ִ����Ϻ� Ѱ���ص�Ϊ�������·�� ����·�����ջ ��Ϊ�յ�ᱻ����ҵ�,���Է���һ��ջ
	 * ����������,���������ȽϷ���.ֱ��ִ�г�ս�����Ϳ��Եõ���ȷ�Ĵ���㵽�յ�����Ҫ�ĵ�
	 * @param end
	 * @return
	 */
	private Stack<Point> getPaths(Point end) {
		Stack<Point> stack = null;
		Iterator<Point> iterator = paths.keySet().iterator();
		while (iterator.hasNext()) {
			stack = new Stack<Point>();
			Point c = iterator.next();
			if (!c.equals(end)) {
				continue;
			}
			Dist td = paths.get(c);
			while (td != null) {
				stack.push(td.getPoint());
				td = td.getPreDist();
			}
			break;
		}
		return stack;
	}

	/**
	 * �ڼ�����Ѱ��ֵ��С��Ԫ��
	 * @param pointKeys
	 * @return
	 */
	private Point getMinKey(Map<Point, Integer> pointKeys) {
		Iterator<Point> iterator = pointKeys.keySet().iterator();
		int minValue = INFINITY;
		Point minKeyPoint = null;
		while (iterator.hasNext()) {
			Point point = iterator.next();
			Integer value = pointKeys.get(point);
			if (value < minValue) {
				minValue = value;
				minKeyPoint = point;
			}
		}
		return minKeyPoint;
	}

	/**
	 * Ѱ������������ڵı�
	 * @param edges
	 * @param point
	 * @return
	 */
	private ArrayList<Edge> getEdegs(ArrayList<Edge> edges, Point point) {
		ArrayList<Edge> tempEdges = new ArrayList<Edge>();
		for (Edge edge : edges) {
			if (edge.getStart().equals(point)) {
				tempEdges.add(edge);
			}
		}
		return tempEdges;
	}

}
