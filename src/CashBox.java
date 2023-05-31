import java.util.LinkedList;
import java.util.Queue;

class CashBox {
  private CashBoxType type;
  private LinkedList<Client> queue;
  private int timeService;
  private int maxLength;

  public CashBox(CashBoxType type, int maxLength) {
    this.type = type;
    this.queue = null;
    this.maxLength = maxLength;
    this.timeService = type == CashBoxType.STANDARD ? 30000 : 10000;
    if (type == CashBoxType.STANDARD) {
      Shop.cashBoxes.add(this);
    } else {
      Shop.fastCashBoxes.add(this);
    }
  }

  public void serviceClient() { //TODO "timer" do obsługi i wywoływanie tego co określony w timeService czas
    queue.remove(0);
  }


  public CashBoxType getType() {
    return type;
  }

  public void addToQueue(Client client) {
    queue.add(client);
  }

  public Queue<Client> getQueue() {
    return queue;
  }

  public int getTimeService() {
    return timeService;
  }

  public int getMaxLength() {
    return maxLength;
  }
}
