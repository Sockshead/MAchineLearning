package co.edu.unisabana.fai2019.sheet3.ettsp;

import java.util.Random;

import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSP;
import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSPGenerator;
import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSPSolutionEvaluator;
import ai.libs.jaicore.problems.enhancedttsp.locationgenerator.ClusterBasedGenerator;
import ai.libs.jaicore.problems.enhancedttsp.locationgenerator.RandomLocationGenerator;
import it.unimi.dsi.fastutil.shorts.ShortList;

public class ProblemCreator {

	public EnhancedTTSP createProblem(int numLocations, int seed) {
		RandomLocationGenerator  gen1 = new RandomLocationGenerator(new Random(seed));
		RandomLocationGenerator  gen2 = new RandomLocationGenerator(new Random(seed + 1));
		ClusterBasedGenerator  cGen = new ClusterBasedGenerator(gen1, gen2, 0.1, 1, 2, new Random(seed));
		return new EnhancedTTSPGenerator(cGen).generate(numLocations, 10, seed);
	}
	
	public SolutionEvaluator getSolutionEvaluator(EnhancedTTSP problem) {
		return new SolutionEvaluator() {
			
			private EnhancedTTSPSolutionEvaluator evaluator = new EnhancedTTSPSolutionEvaluator(problem);
			private double bound = problem.getUpperBoundForAnyTour();
			private int n = 0;

			@Override
			public double evalSolution(ShortList sol) {
				n ++;
				return (double) evaluator.evaluate(sol) / bound;
			}

			@Override
			public int getNumberOfConductedEvaluations() {
				return n;
			}
		};
	}
}
