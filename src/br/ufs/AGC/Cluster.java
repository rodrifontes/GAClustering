package br.ufs.AGC;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

	private List<DataPoint> dataPoints;
	private DataPoint centroid;
	
	public Cluster() {
		dataPoints = new ArrayList<>();
	}
	
	public Cluster(DataPoint centroide) {
		dataPoints = new ArrayList<>();
		this.centroid = centroide;
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

	public DataPoint getCentroid() {
		return centroid;
	}

	public void setCentroid(DataPoint centroide) {
		this.centroid = centroide;
	}
	
}
