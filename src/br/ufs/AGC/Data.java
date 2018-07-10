package br.ufs.AGC;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private List<Object> attributes;
	
	public Data() {
		attributes = new ArrayList<>();
	}

	public List<Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}

	//Euclidian Distance
	public double getDistance(Data destiny) {
		Double distancia = 0d;
		for (int i = 0; i < attributes.size(); i++) {
		    distancia += Math.pow((double)getAttributes().get(i) - (double)destiny.getAttributes().get(i), 2);
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
