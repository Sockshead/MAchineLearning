package co.edu.unisabana.fai2019.sheet3.ettsp;

import org.api4.java.common.attributedobjects.IObjectEvaluator;

import it.unimi.dsi.fastutil.shorts.ShortList;

public interface SolutionEvaluator extends IObjectEvaluator<ShortList, Double> {
	
	default public Double evaluate(ShortList sol) {
		return evalSolution(sol);
	}
	
	public double evalSolution(ShortList sol);
	
	public int getNumberOfConductedEvaluations();
}
