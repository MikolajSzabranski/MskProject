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
      int max = Shop.CASH_BOXES.get(0).getMaxLength();
      Shop.CASH_BOXES.forEach(cashBox -> {
        if (cashBox.getQueue().size() < max) {
          ref.selected = cashBox;
        }
      });
    } else {
      int max = Shop.FAST_CASH_BOXES.get(0).getMaxLength();
      Shop.FAST_CASH_BOXES.forEach(cashBox -> {
        if (cashBox.getQueue().size() < max) {
          ref.selected = cashBox;
        }
      });
    }
    return ref.selected;
  }
}
