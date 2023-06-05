package core;

import java.util.LinkedList;

public class Manager {
  private double startSimTime = 0.0;
  private double endSimTime = Double.MAX_VALUE;
  private double currentSimTime = startSimTime;
  private final double timeStep;

  public double getTimeStep() {
    return timeStep;
  }

  private static Manager simMngr; // Singleton
  private boolean simulationStarted = false;
  // Lista workerów, którzy są składowymi kroku symulacji
  private final LinkedList<SimStep> simStepWorkers = new LinkedList<>();

  public static Manager getInstance(double startSimTime, double timeStep) {
    if (simMngr == null) {
      simMngr = new Manager(startSimTime, timeStep);
    }
    return simMngr;
  }

  private Manager(double startSimTime, double timeStep) {
    if (startSimTime > 0.0)
      this.startSimTime = startSimTime;
    this.timeStep = timeStep;
  }

  public void registerSimStep(SimStep step) {
    if (step != null)
      simStepWorkers.add(step);
  }

  public final double simTime() {
    return currentSimTime;
  }

  public final void stopSimulation() {
    simulationStarted = false;
  }

  public final void startSimulation() {
    currentSimTime = startSimTime;
    simulationStarted = true;
    runStepByStep();
  }

  public void setEndSimTime(double endSimTime) {
    this.endSimTime = endSimTime;
  }

  private void runStepByStep() {
    while (simulationStarted && currentSimTime <= endSimTime) {
      for (SimStep worker : simStepWorkers) {
        worker.createNewClient();
      }
      currentSimTime += timeStep;
    }
    simulationStarted = false;
  }
}
