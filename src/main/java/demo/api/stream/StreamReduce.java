package demo.api.stream;

import java.util.HashMap;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: reduce & collect 操作
 * @Date 2020/10/11 22:27
 * @auther Draymonder
 */

@Data
@AllArgsConstructor
class Order {

  String user;
  int amount;
  double totalPrice;
}

public class StreamReduce {

  static Stream<Order> stream;

  static {
    stream = Stream.of(
        new Order("zyb", 10, 200),
        new Order("zyb", 20, 300),
        new Order("draymonder", 20, 300)
    );
  }

  public static void main(String[] args) {
    reduceProcess();
  }

  /**
   * 统计每个user所购买的物品总数及价格总和
   */
  private static void reduceProcess() {
    HashMap<String, Order> res = stream.collect(
        () -> {
          System.out.println("初始化容器");
          return new HashMap<>();
        },
        (map, order) -> {
          System.out.println("增加元素");
          Order preOrder = map.getOrDefault(order.getUser(), new Order(order.getUser(), 0, 0));
          preOrder.setAmount(preOrder.getAmount() + order.getAmount());
          preOrder.setTotalPrice(preOrder.getTotalPrice() + order.getTotalPrice());
          map.put(order.getUser(), preOrder);
        },
        (map1, map2) -> {
          map2.forEach((user, order) -> {
            map1.merge(user, order, (order1, order2) -> {
              return new Order(user, order1.getAmount() + order2.getAmount(),
                  order1.getTotalPrice() + order2.getTotalPrice());
            });
          });
        });

    System.out.println("res: " + res);
  }
}
