package ppp;

/**
 * This class represents a node in the graph.
 *
 * @author Ehsan Ullah
 */
public class Node {

   /**
    * Name of the node.
    */
   protected String name;

   /**
    * Creates a new node with the given name.
    *
    * @param name Name of the node.
    */
   public Node(String name) {
      this.name = name;
   }

   /**
    * Returns string representation of the node.
    *
    * @return String representation of the node.
    */
   @Override
   public String toString() {
      return this.name;
   }

   /**
    * Compares the node with the given node. Nodes are compared on basis of the
    * name.
    *
    * @param obj Node with which current node is compared with.
    * @return True if node name is same, false otherwise.
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Node)) {
         return false;
      }
      return ((Node) obj).name.equals(this.name);
   }

   /**
    * Returns hash code of the node.
    *
    * @return Hash code of the node.
    */
   @Override
   public int hashCode() {
      return this.name.hashCode();
   }
}
