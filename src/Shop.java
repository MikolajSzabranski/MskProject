import java.util.List;

class Shop {
  public static List<CashBox> cashBoxes = null;
  public static List<CashBox> fastCashBoxes = null;
  public static int cashBoxesNumber = 5;
  public static int fastCashBoxesNumber = 3;


  public Shop() {
  }

  public void createClient() {
    Client newClient = new Client();  //TODO - wołanie tej metody co losowy czas
    newClient.cashBoxSelection().addToQueue(newClient);
  }

  public static void startShop() {
    initCashBoxes();

    //TODO
  }

  private static void initCashBoxes() {
    for (int i = 0; i < cashBoxesNumber; i++) {
      cashBoxes.add(new CashBox(CashBoxType.STANDARD, 10));
    }
    for (int i = 0; i < fastCashBoxesNumber; i++) {
      fastCashBoxes.add(new CashBox(CashBoxType.FAST, 10));
    }
  }
}
