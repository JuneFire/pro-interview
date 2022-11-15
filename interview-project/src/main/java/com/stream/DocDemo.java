package com.stream;

/**
 * @author zkCheng
 * @date 2022/11/8 16:54
 */
public class DocDemo {
    /**
     * 1.Java泛型为Integer、Long、Double、BigDecimal求和
     *
     * Integer sum = scores.stream().reduce(Integer::sum).orElse(0);
     * Long sum = scores.stream().reduce(Long::sum).orElse(0L);
     * Double sum = scores.stream().reduce(Double::sum).orElse(0.00);
     * BigDecimal sum = scores.stream().reduce(BigDecimal::add).orElse(new BigDecimal(0.00));
     */

    /**
     * 2.泛型为实体类
     *
     * 对单个属性求和
     *  Integer sum = sales.stream().mapToInt(Sale::getOrderNum).sum();
     *  Long sum = sales.stream().mapToLong(Sale::getOrderNum).sum();
     *  Double sum = sales.stream().mapToDouble(Sale::getOrderNum).sum();
     *  BigDecimal sum = sales.stream().map(Sale::getAppleSale).reduce(BigDecimal.ZERO, BigDecimal::add);
     *
     *
     *  对多个属性求和
     *  // 类型为BigDecimal
     * Sale result = sales.stream().reduce((x, y) -> new Sale(x.getAppleSale().add(y.getAppleSale()),
     *                 x.getBananaSale().add(y.getBananaSale()), x.getGrapeSale().add(y.getGrapeSale())))
     *                 .orElse(new Sale(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
     * // 类型为Integer、Long、Double（注：orElse中需输入对应类型初始值）
     * Sale sale = sales.stream().reduce((x, y) -> new Sale(x.getAppleSale() + y.getAppleSale(),
     *                 x.getBananaSale() + y.getBananaSale(), x.getGrapeSale() + y.getGrapeSale()))
     *                 .orElse(new Sale(0.00, 0.00,0.00));
     */

/*    private volatile static  Instance   ins ;

    public static Instance  getInstance(){
        if(ins == null){
            synchronized (DocDemo.class){
                if(ins == null){
                    ins = new Instance();
                }
            }
        }
        return  ins;
    }*/

}
