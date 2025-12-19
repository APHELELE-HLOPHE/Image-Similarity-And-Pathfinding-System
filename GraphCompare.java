

public class GraphCompare {
	
	public static double compare(GraphADT graph1,GraphADT graph2,int k) {
		if(graph1.getNodes().isEmpty() || graph2.getNodes().isEmpty()) {
			return 0.0;
		
		}
		
		// Build k-NN graphs for both images
        graph1.buildKNN(k);
        graph2.buildKNN(k);

        // Calculate similarity based on node distances
        double totalSimilarity = 0.0;
		int count = 0;
        
        for (Pixel p1 : graph1.getNodes()) {
            Pixel closest = findNearestPixel(p1, graph2.getNodes());
            if (closest != null) {
                double distance = calculateDistance(p1, closest);
                totalSimilarity += 1.0 / (1.0 + distance);
                count++;
            }
        }
        
        
        if(count > 0 ) {
        	return totalSimilarity / count;
        }else {
        	return 0.0;
        }
	}
	
	private static Pixel findNearestPixel(Pixel pixel , PositionList<Pixel> pixels) {
		if(pixels.isEmpty()) {
			return null;
		}
		
		Pixel closest = pixels.first();
		double shortestDist = calculateDistance(pixel,closest);
		
		for(Pixel p : pixels) {
			double distance = calculateDistance(pixel,p);
			if(distance < shortestDist) {
				shortestDist = distance;
				closest = p;
			}
		}
		return closest;
	}
	
	private static double calculateDistance(Pixel a, Pixel b) {
		double value = Math.sqrt(Math.pow(a.getX()-b.getX(), 2)+Math.pow(a.getY()-b.getY(), 2)+Math.pow(a.getIntensity()-b.getIntensity(),2));
			return value;
	}
}
