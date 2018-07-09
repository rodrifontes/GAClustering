package br.ufs.AGC;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

	private List<Data> data;
	private Data centroide;
	
	public Cluster() {
		data = new ArrayList<>();
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public Data getCentroide() {
		return centroide;
	}

	public void setCentroide(Data centroide) {
		this.centroide = centroide;
	}
	
}
