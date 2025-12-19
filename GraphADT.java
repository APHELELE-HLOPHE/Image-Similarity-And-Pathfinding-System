/**
 * 
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;




/**
 * 
 */
public class GraphADT {
	
	private PositionList<Pixel> nodes;
	private PositionList<Edge> edges;
	
	
	/**
	 * @return the nodes
	 */
	public PositionList<Pixel> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(PositionList<Pixel> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the edges
	 */
	public PositionList<Edge> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(PositionList<Edge> edges) {
		this.edges = edges;
	}

	public GraphADT() {
		nodes = new PositionList<>();
		edges = new PositionList<>();
	}
	
	public void addNode(Pixel p) {
		if(p != null) {
			nodes.AddLast(p);
		}
	}
	
	public void addEdge(Edge e) {
		if(e !=null) {
			edges.AddLast(e);
		}
	}
	
	public PositionList<Edge> buildMST() {
	    PositionList<Edge> mstEdges = new PositionList<>();
	    if (nodes.isEmpty()) {
	    	return mstEdges;
	    }


	    Map<Pixel, List<Edge>> adjList = new HashMap<>();
	    for (Edge e : edges) {
	        adjList.computeIfAbsent(e.getSource(), k -> new ArrayList<>()).add(e);
	        adjList.computeIfAbsent(e.getTarget(), k -> new ArrayList<>()).add(e);
	    }

	    Set<Pixel> visited = new HashSet<>();
	    PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWieght));

	    // Start with the first node
	    Pixel start = nodes.first();
	    visited.add(start);
	    pq.addAll(adjList.getOrDefault(start, Collections.emptyList()));

	    while (!pq.isEmpty() && visited.size() < nodes.size()) {
	        Edge edge = pq.poll();
	        Pixel next = visited.contains(edge.getSource()) ? edge.getTarget() : edge.getSource();

	        if (!visited.contains(next)) {
	            visited.add(next);
	            mstEdges.AddLast(edge);
	            pq.addAll(adjList.getOrDefault(next, Collections.emptyList()));
	        }
	    }
	    return mstEdges;
	}
	
	public PositionList<Pixel> findShortestPath(Pixel start, Pixel end) {
        Map<Pixel, Double> distances = new HashMap<>();
        Map<Pixel, Pixel> previous = new HashMap<>();
        PriorityQueue<Pixel> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Pixel node : nodes) {
            distances.put(node, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Pixel current = pq.poll();
            if (current.equals(end)) {
            	break;
            }

            for (Edge edge : edges) {
                if (edge.getSource().equals(current)) {
                    double newDist = distances.get(current) + edge.getWieght();
                    if (newDist < distances.get(edge.getTarget())) {
                        distances.put(edge.getTarget(), newDist);
                        previous.put(edge.getTarget(), current);
                        pq.add(edge.getTarget());
                    }
                }
            }
        }

        PositionList<Pixel> path = new PositionList<>();
        for (Pixel at = end; at != null; at = previous.get(at)) {
            path.AddLast(at);
        }
       
        return path;
    }
	
	public void buildKNN(int k) {
		for(Pixel p : nodes) {
			//creates a priority queue that prioritizes the distance (shortest first))
			PriorityQueue<Pixel> pNodes = new PriorityQueue<>(Comparator.comparingDouble(n -> calculateDistance(p,n)));
			
			for(Pixel otherpixel:nodes) {
				if(p != otherpixel) {
					pNodes.add(otherpixel);
				}
			}
			
			for(int i =0 ; i <k && !pNodes.isEmpty() ;i++) {
				Pixel neighbor = pNodes.poll();
				Edge edge = new Edge(p,neighbor,calculateDistance(p,neighbor));
				edges.AddLast(edge);
			}
		}
		
		
	}
	
	private static double calculateDistance(Pixel a, Pixel b) {
		double value = Math.sqrt(Math.pow(a.getX()-b.getX(), 2)+Math.pow(a.getY()-b.getY(), 2)+Math.pow(a.getIntensity()-b.getIntensity(),2));
		return value;
	}
}
