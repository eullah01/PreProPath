package ppp;

import java.util.ArrayList;

/**
 * This class represents a weighted edge.
 *
 * @author Ehsan Ullah
 */
public class HyperEdge {

   /**
    * Name of the edge.
    */
   protected String name;
   /**
    * Edge weight.
    */
   protected double weight;
   /**
    * List of source nodes.
    */
   protected ArrayList<Node> sources;
   /**
    * List of target nodes.
    */
   protected ArrayList<Node> targets;

   /**
    * Creates a hyperedge with the given name and zero weight.
    *
    * @param name Name of the hyperedge.
    */
   public HyperEdge(String name) {
      this(name, 0);
   }

   /**
    * Creates a new hyperedge with the given name and weight.
    *
    * @param name Name of the hyperedge.
    * @param weight Weight of the hyperedge.
    */
   public HyperEdge(String name, double weight) {
      this.name = name;
      this.weight = weight;
      this.sources = new ArrayList<>();
      this.targets = new ArrayList<>();
   }

   /**
    * Returns name of the edge.
    *
    * @return Name of the edge.
    */
   public String getName() {
      return this.name;
   }

   /**
    * Returns edge weight.
    *
    * @return Edge weight.
    */
   public double getWeight() {
      return this.weight;
   }

   /**
    * Sets edge weight.
    *
    * @param weight Edge weight.
    */
   public void setWeight(double weight) {
      this.weight = weight;
   }

   /**
    * Adds a source node.
    *
    * @param source Source node.
    */
   public void addSource(Node source) {
      this.sources.add(source);
   }

   /**
    * Checks if the given node is a source node of the edge.
    *
    * @param source Given node.
    * @return true if given node is a source node of the edge, false otherwise.
    */
   public boolean isSource(Node source) {
      return this.sources.contains(source);
   }

   /**
    * Returns number of source nodes.
    *
    * @return Number of source nodes.
    */
   public int getSourceCount() {
      return this.sources.size();
   }

   /**
    * Returns list of source nodes.
    *
    * @return List of source nodes.
    */
   public ArrayList<Node> getSources() {
      return this.sources;
   }

   /**
    * Adds a target node.
    *
    * @param target Target node.
    */
   public void addTarget(Node target) {
      this.targets.add(target);
   }

   /**
    * Checks if the given node is a target node of the edge.
    *
    * @param target Given node.
    * @return true if given node is a target node of the edge, false otherwise.
    */
   public boolean isTarget(Node target) {
      return this.targets.contains(target);
   }

   /**
    * Returns number of target nodes.
    *
    * @return Number of target nodes.
    */
   public int getTargetCount() {
      return this.targets.size();
   }

   /**
    * Returns list of target nodes.
    *
    * @return List of target nodes.
    */
   public ArrayList<Node> getTargets() {
      return this.targets;
   }

   /**
    * Returns chemical equation corresponding to the hyperedge.
    *
    * @return Chemical equation corresponding to the hyperedge.
    */
   public String getEquation() {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < this.sources.size(); i++) {
         if (i > 0) {
            builder.append(" + ");
         }
         builder.append(this.sources.get(i));
      }
      builder.append(" --> ");
      for (int i = 0; i < this.targets.size(); i++) {
         if (i > 0) {
            builder.append(" + ");
         }
         builder.append(this.targets.get(i));
      }
      return builder.toString();
   }

   /**
    * Returns string representation of the hyperedge.
    *
    * @return String representation of the hyperedge.
    */
   @Override
   public String toString() {
      return this.name + " (" + this.weight + ") : " + this.getEquation();
   }

   /**
    * Compares the hyperedge with the given hyperedge. Hyperedge are compared on
    * basis of the name.
    *
    * @param obj Hyperedge with which current hyperedge is compared with.
    * @return True if hyperedge name is same, false otherwise.
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof HyperEdge)) {
         return false;
      }
      return ((HyperEdge) obj).name.equals(this.name);
   }

   /**
    * Returns hash code of the hyperedge.
    *
    * @return Hash code of the hyperedge.
    */
   @Override
   public int hashCode() {
      return this.name.hashCode();
   }
}
