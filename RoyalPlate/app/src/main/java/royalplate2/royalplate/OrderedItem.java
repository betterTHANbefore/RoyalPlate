package royalplate2.royalplate;

/**
 * Created by hetu on 5/5/15.
 */
public class OrderedItem {

    private String itemName;
    private double price;
    private int noOfItems;

    public OrderedItem(){}

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}