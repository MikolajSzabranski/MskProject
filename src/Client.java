import java.util.Random;

class Client {
  private int productsNumber;

  public Client() {
    this.productsNumber = new Random().nextInt(0,15);
  }

  public CashBoxType typeOfCashBoxSelection() {
    return productsNumber < 5 ? CashBoxType.FAST : CashBoxType.STANDARD;
  }

  public CashBox cashBoxSelection() {
    var ref = new Object() {
      CashBox selected = null;
    };
    if (typeOfCashBoxSelection() == CashBoxType.FAST) {
      int max = Shop.cashBoxes.get(0).getMaxLength();
      Shop.cashBoxes.forEach(cashBox -> {
        if (cashBox.getQueue().size() < max) {
          ref.selected = cashBox;
        }
      });
    } else {
      int max = Shop.fastCashBoxes.get(0).getMaxLength();
      Shop.fastCashBoxes.forEach(cashBox -> {
        if (cashBox.getQueue().size() < max) {
          ref.selected = cashBox;
        }
      });
    }
    return ref.selected;
  }
}
