package co.edu.unisabana.fai2019.sheet3.ettsp;

import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSP;
import it.unimi.dsi.fastutil.shorts.ShortList;

/**
 * A solver for the EnhancedTTSP problem 
 * 
 * @author felix
 *
 */
public abstract class ATSPSolver {
	
	public final EnhancedTTSP problem;
	public final SolutionEvaluator solutionEvaluator;
		
	public ATSPSolver(EnhancedTTSP problem) {
		super();
		this.problem = problem;
		this.solutionEvaluator = new ProblemCreator().getSolutionEvaluator(problem);
	}
	
	/**
	 * Produces a solution with a limited number of calls to the evaluation function!
	 * 
	 * @param maxEvaluations
	 * @return
	 */
	public abstract ShortList solve(int maxEvaluations);
}
