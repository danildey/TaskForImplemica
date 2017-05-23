package Task2;
/*
 * Edge of graph
 */
public class Neighbour implements Cloneable{
    private int fromCityId;
    private int toCityId;
    private int price;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try{return super.clone();}
        catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }

    public Neighbour(int fromCityId, int toCityId, int price) {

        this.fromCityId = fromCityId;
        this.toCityId = toCityId;
        this.price = price;
    }
//    determines the neighbouring City of supplied City,
//    based on the two cities connection by this price.
    public  int getNeighbourCityId( int cityId){
        if (this.fromCityId==cityId){
            return this.toCityId;
        }else {
            return this.fromCityId;
        }
    }

    public int getFromCityId() {
        return fromCityId;
    }

    public void setFromCityId(int fromCityId) {
        this.fromCityId = fromCityId;
    }

    public int getToCityId() {
        return toCityId;
    }

    public void setToCityId(int toCityIndex) {
        this.toCityId = toCityIndex;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




}
