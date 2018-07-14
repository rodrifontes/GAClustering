package br.ufs.AGC;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

	private List<DataPoint> data;
	private DataPoint centroide;
	
	public Cluster() {
		data = new ArrayList<>();
	}

	public List<DataPoint> getData() {
		return data;
	}

	public void setData(List<DataPoint> data) {
		this.data = data;
	}

	public DataPoint getCentroide() {
		return centroide;
	}

	public void setCentroide(DataPoint centroide) {
		this.centroide = centroide;
	}
	
}
