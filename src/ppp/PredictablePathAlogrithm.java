package ppp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * This class is implementation of favored path algorithm.
 *
 * @author Ehsan Ullah
 */
public class PredictablePathAlogrithm {

   /**
    * Finds favored path in the given graph from source node to the target node
    * favoring high weights.
    *
    * @param input Input graph.
    * @param s Source node.
    * @param t Target node.
    * @return Favored path.
    */
   public static HyperGraph getFavoredPathHighWeights(HyperGraph input, Node s, Node t) {
      return getFavoredPath(input, s, t, new Comparator<HyperEdge>() {

         @Override
         public int compare(HyperEdge e1, HyperEdge e2) {
            return (e1.getWeight() - e2.getWeight()) < 0 ? 1 : -1;
         }
      });
   }

   /**
    * Finds favored path in the given graph from source node to the target node
    * favoring low weights.
    *
    * @param input Input graph.
    * @param s Source node.
    * @param t Target node.
    * @return Favored path.
    */
   public static HyperGraph getFavoredPathLowerWeights(HyperGraph input, Node s, Node t) {
      return getFavoredPath(input, s, t, new Comparator<HyperEdge>() {

         @Override
         public int compare(HyperEdge e1, HyperEdge e2) {
            return (e1.getWeight() - e2.getWeight()) > 0 ? 1 : -1;
         }
      });
   }

   /**
    * Finds favored path in the given graph from source node to the target node
    * using the comparator for edge selection.
    *
    * @param input Input graph.
    * @param s Source node.
    * @param t Target node.
    * @param comparator Comparator for edge weights.
    * @return Favored path.
    */
   private static HyperGraph getFavoredPath(HyperGraph input, Node s, Node t, Comparator<HyperEdge> comparator) {
      HyperGraph bestUnfavoredPath = new HyperGraph();
      PriorityQueue<HyperEdge> q = new PriorityQueue<>(input.getEdgeCount(), comparator);
      for (HyperEdge edge : input.getEdges()) {
         q.add(edge);
      }
      boolean pathFound = false;
      HyperEdge unfavored;
      while (!q.isEmpty()) {
         HyperGraph g = new HyperGraph(bestUnfavoredPath);
         PriorityQueue<HyperEdge> nq = new PriorityQueue<>(q.size(), comparator);
         ArrayList<HyperEdge> buffer = new ArrayList<>();
         do {
            unfavored = q.poll();
            g.addEdge(unfavored);
            buffer = new ArrayList<>();
            buffer.add(unfavored);
            while (!q.isEmpty() && q.peek().getWeight() == unfavored.getWeight()) {
               buffer.add(q.peek());
               g.addEdge(q.peek());
               q.poll();
            }
            pathFound = pathExists(g, s, t);
            if (!pathFound) {
               for (HyperEdge edge : buffer) {
                  nq.add(edge);
               }
            }
         } while (!pathFound && !q.isEmpty());
         for (HyperEdge edge : buffer) {
            bestUnfavoredPath.addEdge(edge);
         }
         q = nq;
      }
      if (!pathFound) {
         return new HyperGraph();
      }
      return getPath(bestUnfavoredPath, s, t);
   }

   /**
    * Checks if a path exists from the source node to the target node in the
    * given graph.
    *
    * @param g Input graph.
    * @param s Source node.
    * @param t Target node.
    * @return true if path exists, false otherwise.
    */
   private static boolean pathExists(HyperGraph g, Node s, Node t) {
      return bfs(g, s).contains(t);
   }

   /**
    * Breadth first search.
    *
    * @param g Given graph.
    * @param root Starting node.
    * @return List of nodes.
    */
   private static ArrayList<Node> bfs(HyperGraph g, Node root) {
      ArrayList<Node> visited = new ArrayList<>(g.getNodeCount());
      ArrayList<Node> tbs = new ArrayList<>(g.getNodeCount());
      tbs.add(root);
      while (!tbs.isEmpty()) {
         root = tbs.remove(tbs.size() - 1);
         visited.add(root);
         ArrayList<HyperEdge> edges = g.getEdgesWithSource(root);
         for (HyperEdge edge : edges) {
            ArrayList<Node> nodes = edge.getTargets();
            for (Node node : nodes) {
               if (!visited.contains(node)) {
                  tbs.add(node);
               }
            }
         }
      }
      return visited;
   }

   /**
    * Returns path from the given source node to the target node in the given
    * graph.
    *
    * @param g Given graph.
    * @param s Source node.
    * @param t Target node.
    * @return Path from the given source node to the target node in the given
    * graph.
    */
   private static HyperGraph getPath(HyperGraph g, Node s, Node t) {
      HashMap<Node, HyperEdge> parentEdge = new HashMap<>();
      HashMap<Node, Node> parentNode = new HashMap<>();
      HyperGraph path = new HyperGraph();
      if (!bfs(g, s, parentEdge, parentNode).contains(t)) {
         return path;
      }
      ArrayList<HyperEdge> edges = new ArrayList<>();
      while (!t.equals(s)) {
         edges.add(parentEdge.get(t));
         t = parentNode.get(t);
      }
      for (HyperEdge edge : g.getEdges()) {
         if (edges.contains(edge)) {
            path.addEdge(edge);
         }
      }
      return path;
   }

   /**
    * Recursive breadth first search.
    */
   private static ArrayList<Node> bfs(HyperGraph g, Node root, HashMap<Node, HyperEdge> parentEdge, HashMap<Node, Node> parentNode) {
      ArrayList<Node> visited = new ArrayList<>(g.getNodeCount());
      ArrayList<Node> tbs = new ArrayList<>(g.getNodeCount());
      tbs.add(root);
      while (!tbs.isEmpty()) {
         root = tbs.remove(tbs.size() - 1);
         ArrayList<HyperEdge> edges = g.getEdgesWithSource(root);
         for (HyperEdge edge : edges) {
            ArrayList<Node> nodes = edge.getTargets();
            for (Node node : nodes) {
               if (!visited.contains(node)) {
                  tbs.add(node);
                  visited.add(node);
                  parentEdge.put(node, edge);
                  parentNode.put(node, root);
               }
            }
         }
      }
      return visited;
   }
}
