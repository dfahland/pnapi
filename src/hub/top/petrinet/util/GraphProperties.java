package hub.top.petrinet.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import hub.top.petrinet.Node;

/**
 * Utility class to reason about structural properties of the net.
 * 
 * @author dfahland
 *
 */
public class GraphProperties {
  
  /**
   * @param src
   * @param targets
   * @return true iff any of the <code>target</code> nodes can be reached from the <code>src</code> node (in a path of at least length 1)
   */
  public static boolean canReach(final Node src, final Set<Node> targets) {
    LinkedList<Node> toVisit =  new LinkedList<Node>();
    Set<Node> visited =  new HashSet<Node>();
    toVisit.add(src);
    
    // do a depth-first search through the graph of the net
    while (!toVisit.isEmpty()) {
      Node n = toVisit.removeFirst();
      visited.add(n);
      for (Node m : n.getPostSet()) {
        if (targets.contains(m)) return true;  // reached source node again  
        if (!visited.contains(m)) toVisit.addFirst(m);
      }
    }
    return false;
  }
  
  /**
   * @param n
   * @return true iff there is a cycle in the net containing n
   */
  public static boolean isInACycle(final Node n) {
    final Set<Node> targets = new HashSet<Node>();
    targets.add(n);
    return canReach(n, new HashSet<Node>(targets));
  }

}
