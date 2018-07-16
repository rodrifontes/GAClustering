package br.ufs.AGC;

import java.util.ArrayList;
import java.util.List;

public class DataPoint {
	
	private List<Object> attributes;
	
	public DataPoint() {
		attributes = new ArrayList<>();
	}

	public List<Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}

	//Euclidian Distance
	public double getDistance(DataPoint destiny) {
		Double distancia = 0d;
		for (int i = 0; i < attributes.size(); i++) {
		    distancia += Math.pow((Double) getAttributes().get(i) - (Double) destiny.getAttributes().get(i), 2);
		}
		return Math.sqrt(distancia);
	}
	
	@Override
	public String toString() {
		String result = "[";
		for (int i = 0; i < attributes.size(); i++) {
			if(i+1 < attributes.size())
				result += attributes.get(i).toString() + ", ";
			else
				result += attributes.get(i).toString();
		}
		result += "];";
		return result;
	}
	
}
