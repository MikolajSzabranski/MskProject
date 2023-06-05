import core.Manager;
import core.SimStep;
import monitors.MonitoredVar;

import java.util.List;

class Shop extends SimStep {
  public static List<CashBox> CASH_BOXES = null;
  public static List<CashBox> FAST_CASH_BOXES = null;
  public static int CASH_BOXES_NUMBER = 5;
  public static int FAST_CASH_BOXES_NUMBER = 3;
  public MonitoredVar state = new MonitoredVar();
  private final double step;
  static double lastValue;
  double lastST = 0.0;

  public Shop(Manager manager, double step) {
    super(manager);
    this.step = manager.getTimeStep();
  }

  public void createClient() {
    Client newClient = new Client();  //TODO - wołanie tej metody co losowy czas
    newClient.cashBoxSelection().addToQueue(newClient);
  }

  public static void startShop() {
    initCashBoxes();

    //TODO
  }

  @Override
  public void createNewClient() {
    double st = simTime();
    double currentValue = st == 0.0 ? 1.0 : lastValue + step * (lastValue - Math.pow(lastST, 2));
    state.setValue(currentValue, st);
    lastValue = currentValue;
    lastST = st;
    System.out.println("\t\t" + st + " - " + currentValue);
  }

  private static void initCashBoxes() {
    for (int i = 0; i < CASH_BOXES_NUMBER; i++) {
      CASH_BOXES.add(new CashBox(CashBoxType.STANDARD, 10));
    }
    for (int i = 0; i < FAST_CASH_BOXES_NUMBER; i++) {
      FAST_CASH_BOXES.add(new CashBox(CashBoxType.FAST, 10));
    }
  }
}
