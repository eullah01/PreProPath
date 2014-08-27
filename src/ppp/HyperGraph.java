package ppp;

import java.util.ArrayList;

/**
 * This class represents a hypergraph.
 *
 * @author Ehsan Ullah
 */
public class HyperGraph {

   /**
    * List of edges.
    */
   protected ArrayList<HyperEdge> edges;
   /**
    * List of nodes.
    */
   protected ArrayList<Node> nodes;

   /**
    * Creates an empty hypergraph.
    */
   public HyperGraph() {
      this.edges = new ArrayList<>();
      this.nodes = new ArrayList<>();
   }

   /**
    * Copy constructor of HyperGraph.
    *
    * @param graph Graph from where copy is to be created.
    */
   public HyperGraph(HyperGraph graph) {
      this.nodes = new ArrayList<>(graph.nodes.size());
      this.edges = new ArrayList<>(graph.edges.size());
      for (HyperEdge reaction : graph.edges) {
         this.addEdge(reaction);
      }
   }

   /**
    * Returns list of edges.
    *
    * @return List of edges.
    */
   public ArrayList<HyperEdge> getEdges() {
      return this.edges;
   }

   /**
    * Returns list of nodes.
    *
    * @return List of nodes.
    */
   public ArrayList<Node> getNodes() {
      return this.nodes;
   }

   /**
    * Returns number of edges in the graph.
    *
    * @return Number of edges in the graph.
    */
   public int getEdgeCount() {
      return this.edges.size();
   }

   /**
    * Returns number of nodes in the graph.
    *
    * @return Number of nodes in the graph.
    */
   public int getNodeCount() {
      return this.nodes.size();
   }

   /**
    * Adds edge to the graph.
    *
    * @param edge Edge to be added.
    */
   public final void addEdge(HyperEdge edge) {
      if (this.edges.contains(edge)) {
         return;
      }
      this.edges.add(edge);
      ArrayList<Node> nds = edge.getSources();
      for (int i = 0; i < nds.size(); i++) {
         Node node = nds.get(i);
         int index = this.nodes.indexOf(node);
         if (index < 0) {
            this.nodes.add(node);
         } else {
            nds.set(i, this.nodes.get(index));
         }
      }
      nds = edge.getTargets();
      for (int i = 0; i < nds.size(); i++) {
         Node node = nds.get(i);
         int index = this.nodes.indexOf(node);
         if (index < 0) {
            this.nodes.add(node);
         } else {
            nds.set(i, this.nodes.get(index));
         }
      }
   }

   /**
    * Returns list of edges with the given source node.
    *
    * @param source Source node.
    * @return List of edges with the given source node.
    */
   public ArrayList<HyperEdge> getEdgesWithSource(Node source) {
      ArrayList<HyperEdge> edgs = new ArrayList<>();
      for (HyperEdge reaction : this.edges) {
         if (reaction.isSource(source)) {
            edgs.add(reaction);
         }
      }
      return edgs;
   }

   /**
    * Returns list of edges with the given target node.
    *
    * @param target Target node.
    * @return List of edges with the given target node.
    */
   public ArrayList<HyperEdge> getEdgesWithTarget(Node target) {
      ArrayList<HyperEdge> edgs = new ArrayList<>();
      for (HyperEdge reaction : this.edges) {
         if (reaction.isTarget(target)) {
            edgs.add(reaction);
         }
      }
      return edgs;
   }

   /**
    * Returns node with the given name.
    *
    * @param name Node name.
    * @return Node with the given name.
    */
   public Node getNode(String name) {
      int index = this.nodes.indexOf(new Node(name));
      if (index < 0) {
         return null;
      }
      return this.nodes.get(index);
   }

   /**
    * Returns edge with the given name.
    *
    * @param name Edge name.
    * @return Edge with the given name.
    */
   public HyperEdge getEdge(String name) {
      int index = this.edges.indexOf(new HyperEdge(name));
      if (index < 0) {
         return null;
      }
      return this.edges.get(index);
   }

   /**
    * Returns string representation of the graph.
    *
    * @return String representation of the graph.
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder(1024);
      for (HyperEdge e : this.edges) {
         builder.append(e.getName());
         builder.append(", ");
      }
      return builder.toString();
   }
}
