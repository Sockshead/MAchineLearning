package Annealing;

import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSP;
import co.edu.unisabana.fai2019.sheet3.ettsp.ATSPSolver;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.lang.Math;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;

public class Anneal extends ATSPSolver {

    public Anneal(EnhancedTTSP problem) {
        super(problem);
    }

    @Override
    public ShortList solve(int maxEvaluations) {
        int iteration = -1;

        ArrayList<Integer> currentOrder = new ArrayList<Integer>();
        ArrayList<Integer> nextOrder = new ArrayList<Integer>();
        double temperature = 10000.0;
        double deltaDistance = 0;
        double coolingRate = 0.9999;
        double absoluteTemperature = 0.00001;

        double distance = GetTotalDistance(currentOrder);

        while (temperature > absoluteTemperature) {
            nextOrder = GetNextArrangement(currentOrder);

            deltaDistance = GetTotalDistance(nextOrder) - distance;

            //if the new order has a smaller distance
            //or if the new order has a larger distance but 
            //satisfies Boltzman condition then accept the arrangement
            if ((deltaDistance < 0) || (distance > 0
                    && Math.exp(-deltaDistance / temperature) > random.NextDouble())) {
                for (int i = 0; i < nextOrder.size(); i++) {
                    currentOrder.set(i, nextOrder.get(i));
                }

                distance = deltaDistance + distance;
            }

            //cool down the temperature
            temperature *= coolingRate;

            iteration++;
        }

        shortestDistance(distance);
        return null;
    }

    public double shortestDistance(double dist) {
        double shortestDistance = dist;
        return shortestDistance;
    }

    private double GetTotalDistance(ArrayList<Integer> currentOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ArrayList<Integer> GetNextArrangement(ArrayList<Integer> currentOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
