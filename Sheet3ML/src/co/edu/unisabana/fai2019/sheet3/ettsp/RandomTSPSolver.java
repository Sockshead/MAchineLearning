package co.edu.unisabana.fai2019.sheet3.ettsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSP;
import ai.libs.jaicore.problems.enhancedttsp.Location;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortList;

public class RandomTSPSolver extends ATSPSolver {

	private final Random random;
	
	public RandomTSPSolver(EnhancedTTSP problem, Random random) {
		super(problem);
		this.random = random;
	}

	@Override
	public ShortList solve(int maxEvaluations) {
		List<Location> locationCopy = new ArrayList<>(problem.getLocations());
		locationCopy.removeIf(l -> l.getId() == 0);
		Collections.shuffle(locationCopy, random);
		ShortList suggestion = new ShortArrayList(locationCopy.stream().map(l -> l.getId()).collect(Collectors.toList()));
		suggestion.add((short)0); // reinsert 0 at end
		return suggestion;
	}

}
