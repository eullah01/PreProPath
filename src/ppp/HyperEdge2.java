package ppp;

/**
 * This class represents a hyperedge with a weight range.
 *
 * @author Ehsan Ullah
 */
public class HyperEdge2 extends HyperEdge {

   /**
    * Minimum edge weight
    */
   private double minWeight;
   /**
    * Maximum edge weight.
    */
   private double maxWeight;

   /**
    * Creates a new instance of HyperEdge2 with the given name.
    *
    * @param name Name of the hyperedge.
    */
   public HyperEdge2(String name) {
      super(name);
   }

   /**
    * Returns minimum edge weight.
    *
    * @return Minimum edge weight.
    */
   public double getMinWeight() {
      return this.minWeight;
   }

   /**
    * Sets minimum edge weight.
    *
    * @param minWeight Minimum edge weight.
    */
   public void setMinWeight(double minWeight) {
      this.minWeight = minWeight;
   }

   /**
    * Returns maximum edge weight.
    *
    * @return Maximum edge weight.
    */
   public double getMaxWeight() {
      return this.maxWeight;
   }

   /**
    * Sets maximum edge weight.
    *
    * @param maxWeight Maximum edge weight.
    */
   public void setMaxWeight(double maxWeight) {
      this.maxWeight = maxWeight;
   }

   /**
    * Returns string representation of the hyperedge.
    *
    * @return String representation of the hyperedge.
    */
   @Override
   public String toString() {
      return this.name + " (" + this.minWeight + "," + this.maxWeight + ") : " + this.getEquation();
   }
}
