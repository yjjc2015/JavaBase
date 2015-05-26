package haodong.net.cn.builder;

/**
 * 具体建造者类
 * Created by haodong on 15-5-26.
 */
public class ConcreteBuilder extends Builder {
    private Product product = new Product();
    @Override
    public void buildPart1() {
        this.product.setPart1("part1");
    }

    @Override
    public void buildPart2() {
        this.product.setPart2("part2");
    }

    /**
     * 返回建造好的产品
     * @return
     */
    @Override
    public Product retrieveResult() {
        return this.product;
    }
}
