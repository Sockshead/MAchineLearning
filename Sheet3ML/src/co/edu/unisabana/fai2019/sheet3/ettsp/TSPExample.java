package co.edu.unisabana.fai2019.sheet3.ettsp;

import Annealing.Anneal;
import java.util.Random;

import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSP;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortList;

public class TSPExample {
	public static void main(String[] args) {
		
		/* create a new problem instance */
		ProblemCreator pc = new ProblemCreator();
		EnhancedTTSP problemInstance = pc.createProblem(10, 0);
		SolutionEvaluator se = pc.getSolutionEvaluator(problemInstance);

		/* some method to create a new solution */
		ShortList list = new ShortArrayList();
		list.add((short) 7);
		list.add((short) 2);
		list.add((short) 3);
		list.add((short) 4);
		list.add((short) 8);
		list.add((short) 5);
		list.add((short) 1);
		list.add((short) 6);
		list.add((short) 9);
		list.add((short) 0); // this MUST be the last element, because we always start in 0
		
		/* show quality of solution */
		double cost = se.evaluate(list);
		System.out.println(cost);
		
		/* now use a solver to create a solution */
		ATSPSolver solver = new Anneal(problemInstance);
		ShortList solution = solver.solve(100000);
		double costOfComputedSolution = se.evalSolution(solution);
		System.out.println(costOfComputedSolution);
		System.out.println("Number of evaluations: " + solver.solutionEvaluator.getNumberOfConductedEvaluations());
	}
}
