package Annealing;

import ai.libs.jaicore.problems.enhancedttsp.EnhancedTTSP;
import co.edu.unisabana.fai2019.sheet3.ettsp.ATSPSolver;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.lang.Math;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Anneal extends ATSPSolver {

    public Anneal(EnhancedTTSP problem) {
        super(problem);
        //solutionEvaluator.evalSolution(sol);
    }

    @Override
    public ShortList solve(int maxEvaluations) {
        int t = 0;

        /* compute initial solution */
        ShortList currentOrder = new ShortArrayList();
        int n = problem.getLocations().size() - 1;
        for (int i = 1; i <= n; i++) {
            currentOrder.add((short) i);
        }
        //System.out.println(currentOrder);
        Collections.shuffle(currentOrder);
        //System.out.println(currentOrder);
        currentOrder.add((short) 0);
        double fCurr = solutionEvaluator.evalSolution(currentOrder);

        ShortList bestOrder = new ShortArrayList(currentOrder);
        double fBest = fCurr;

        while (maxEvaluations != 0) {
            int At = this.getA(t);
            int Tt = this.getT(t);
            for (int i = 0; i < At; i++) {
                ShortList next = this.GetNextArrangement(currentOrder);
                //System.out.println(next);
                double fNext = solutionEvaluator.evalSolution(next);
                double prob = Math.exp(-Math.max(fNext - fCurr, 0) / Tt);
                Random r = new Random();
                double nextD = r.nextDouble();
                if (nextD < prob) {
                    currentOrder = next;
                    fCurr = fNext;
                }
                if (fCurr < fBest) {
                    bestOrder = currentOrder;
                    fBest = fCurr;
                }
                maxEvaluations = maxEvaluations - 1;
                if (maxEvaluations == 0) {
                    return bestOrder;
                }
            }
            t = t + 1;
        }
        return bestOrder;
    }

    private ShortList GetNextArrangement(ShortList currentOrder) {
        Random r = new Random();
        int index1 = r.nextInt(currentOrder.size() - 1);
        int index2 = r.nextInt(currentOrder.size() - 1);
        while (index1 == index2) {
            index2 = r.nextInt(currentOrder.size() - 1);
        }

        short i1 = currentOrder.getShort(index1);
        short i2 = currentOrder.getShort(index2);
        ShortList l = new ShortArrayList();
        l.addAll(currentOrder);
        l.set(index1, i2);
        l.set(index2, i1);
        return l;
    }

    private int getA(int t) {
        return 7;
    }

    private int getT(int t) {
        return 4;
    }
}
