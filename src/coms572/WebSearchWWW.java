package coms572;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

// You should call this code as follows:
//
//   java WebSearch directoryName searchStrategyName
//   (or jview, in J++)
//
//   where <directoryName> is the name of corresponding intranet
//   and <searchStrategyName> is one of {breadth, depth, best, beam}.

public class WebSearchWWW {

	static LinkedList<SearchNode> OPEN;
	static HashSet<String> CLOSED;
	static final boolean DEBUGGING = false; // When set, report what's happening.
	static int beamWidth = 10;
	
	// Data Structure to keep the linked list of children nodes of a particular node.
	static LinkedList<LinkedList<SearchNode>> beamOPEN = new LinkedList<LinkedList<SearchNode>>();
	
	static final String directoryName = "https://en.wikipedia.org";
	static final String START_NODE = "/wiki/Main_Page";
	static final String GOAL_PATTERN = "Outline of social science";

	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("You must provide the searchStrategyName.  Please try again.");
		} else {
			String searchStrategyName = args[0]; // Read the search strategy to use.

			if (searchStrategyName.equalsIgnoreCase("breadth") || searchStrategyName.equalsIgnoreCase("depth")
					|| searchStrategyName.equalsIgnoreCase("best") || searchStrategyName.equalsIgnoreCase("beam")) {
				performSearch(START_NODE, directoryName, searchStrategyName);
			} else {
				System.out.println("The valid search strategies are:");
				System.out.println("  BREADTH DEPTH BEST BEAM");
			}
		}

		Utilities.waitHere("Press ENTER to exit.");
	}

	static void performSearch(String startNode, String directoryName, String searchStrategy) {
		int nodesVisited = 0;

		OPEN = new LinkedList<SearchNode>();
		CLOSED = new HashSet<String>();

		OPEN.add(new SearchNode(startNode));
		String currentURL = "";

		while (!OPEN.isEmpty()) {
			if (!searchStrategy.equalsIgnoreCase("beam")) {

				SearchNode currentNode = pop(OPEN);
				currentURL = currentNode.getNodeName();
				nodesVisited++;
				CLOSED.add(currentURL);

				// Go and fetch the contents of this file.
				if (!currentURL.isEmpty()) {
					String contentPage = "";
					if(currentURL.startsWith("http")) {
						contentPage = currentURL;
					} else {
						contentPage = directoryName + currentURL;
					}
					String contents = Utilities.getFileContents(contentPage);

					if (isaGoalNode(contents)) {
						System.out.println("Solution Path in reverse order: ");
						currentNode.reportSolutionPath();
						break;
					}
					addNewChildrenToOPEN(currentNode, contents, searchStrategy, directoryName);
				}
			} else {
				for (int j = 0; j < beamWidth; j++) {
					if (!OPEN.isEmpty()) {
						SearchNode currentNode = pop(OPEN);
						currentURL = currentNode.getNodeName();
						nodesVisited++;

						CLOSED.add(currentURL);

						if (!currentURL.isEmpty()) {
							String contentPage = "";
							if(currentURL.startsWith("http")) {
								contentPage = currentURL;
							} else {
								contentPage = directoryName + currentURL;
							}
							String contents = Utilities.getFileContents(contentPage);

							if (isaGoalNode(contents)) {
								System.out.println("Solution Path in reverse order: ");
								currentNode.reportSolutionPath();
								break;
							}
							addNewChildrenToOPEN(currentNode, contents, searchStrategy, directoryName);
						}
					}
				}

				LinkedList<SearchNode> temporaryList = new LinkedList<SearchNode>();

				Boolean isGoalReached = false;
				while (!beamOPEN.isEmpty() && !isGoalReached) {
					LinkedList<SearchNode> nodeList = beamOPEN.removeFirst();
					Collections.sort(nodeList);
					int index = 0;
					while (!nodeList.isEmpty() && index < beamWidth) {
						SearchNode node = nodeList.removeFirst();
						String contentPage = "";
						if(node.getNodeName().startsWith("http")) {
							contentPage = node.getNodeName();
						} else {
							contentPage = directoryName + node.getNodeName();
						}
						String contents = Utilities.getFileContents(contentPage);
						
						if (!isaGoalNode(contents)) {
							temporaryList.add(node);
						} else {
							isGoalReached = true;
							System.out.println("Solution Path in reverse order: ");
							node.reportSolutionPath();
							break;
						}
						index++;
					}
				}
				Collections.sort(temporaryList);
				int index = 0;
				while (index < beamWidth && !temporaryList.isEmpty() && !isGoalReached) {
					OPEN.add(pop(temporaryList));
					index++;
				}
				beamOPEN.clear();
			}
		}

		// Provide a status report.
		if (DEBUGGING)
			System.out.println("Nodes visited = " + nodesVisited + " |OPEN| = " + OPEN.size());

		System.out.println(" Visited " + nodesVisited + " nodes, starting @" + " " + directoryName
				+ startNode + ", using: " + searchStrategy + " search.");
	}

	// This method reads the page's contents and
	// collects the 'children' nodes (ie, the hyperlinks on this page).
	// The parent node is also passed in so that 'backpointers' can be
	// created (in order to later extract solution paths).
	static void addNewChildrenToOPEN(SearchNode parent, String contents, String searchStrategy, String directoryName) {
		
		StringTokenizer st = new StringTokenizer(contents);
		Boolean dfs = false; // Boolean to check whether goal node is already achieved in DFS.
		LinkedList<SearchNode> childNodes = new LinkedList<SearchNode>(); // Linked List for Children Nodes to be nested
																			// insde the beamOPEN outer linked list.

		while (st.hasMoreTokens() && dfs == false) {
			String token = st.nextToken();

			// Look for the hyperlinks on the current page.
			// At the start of some hypertext? Otherwise, ignore this token.
			if (token.equalsIgnoreCase("<A")) {
				String hyperlink; // The name of the child node.

				if (DEBUGGING)
					System.out.println("Encountered a HYPERLINK");

				// Read: HREF = page#.html >

				token = st.nextToken();
				while (!token.equalsIgnoreCase("HREF")) {
					token = st.nextToken();
					if (DEBUGGING) System.out.println("Expecting 'HREF' and got: " + token);
				}

				token = st.nextToken();
//				if (!token.equalsIgnoreCase("=")) {
//					System.out.println("Expecting '=' and got: " + token);
//				}

				// Now we should be at the name of file being linked to.
				hyperlink = st.nextToken();
				hyperlink=hyperlink.substring(1, hyperlink.length()-1);
				
				if(!hyperlink.startsWith("https://en.wikipedia") && !hyperlink.startsWith("/wiki")) {
					continue;
				}
					
				if (DEBUGGING)
					System.out.println(" - found a link to " + hyperlink);

				if (alreadyInOpen(hyperlink)) {
					if (DEBUGGING) System.out.println(" - this node is in the OPEN list.");
				} else if (CLOSED.contains(hyperlink)) { // If already in CLOSED, we'll also ignore this hyperlink.
					if (DEBUGGING)
						System.out.println(" - this node is in the CLOSED list.");
				} else {
					String hypertext = ""; // The text associated with this hyperlink.

					while(hypertext.isEmpty() || hypertext.equals(" ")) {
						token = st.nextToken();
						while (!token.equalsIgnoreCase(">")) {
							token = st.nextToken();
							if (DEBUGGING) System.out.println("Expecting '>' and got: " + token);
						}
						
						while (!token.startsWith("<")) {
							token = st.nextToken();
							hypertext+=token;
							if (DEBUGGING) System.out.println("Expecting '<' and got: " + token);
						}
					}

					if (DEBUGGING) System.out.println("   with hypertext: " + hypertext);

					hypertext = hypertext.substring(0, hypertext.indexOf("<"));
					SearchNode searchNode = new SearchNode(hyperlink);
					searchNode.setParent(parent); // Keeping track of the parent of each node for printing the solution paths.

					if (searchStrategy.equalsIgnoreCase("breadth")) {
						OPEN.add(searchNode);
					} else if (searchStrategy.equalsIgnoreCase("depth")) {
						OPEN.add(searchNode);
						String childContents = Utilities.getFileContents(directoryName + hyperlink);
						if (isaGoalNode(childContents)) {
							dfs = true;
							break;
						} else {
							// Further extending a child until goal node is not achieved.
							addNewChildrenToOPEN(searchNode, childContents, searchStrategy, directoryName);
						}
					} else if (searchStrategy.equalsIgnoreCase("best")) {
						int heuristic = calculateHeuristic(hypertext); // Calculating heuristic value based on
																					// the given content and hypertext.
						searchNode.sethValue(heuristic);
						OPEN.add(searchNode);
					} else if (searchStrategy.equalsIgnoreCase("beam")) {
						int heuristic = calculateHeuristic(hypertext);
						searchNode.sethValue(heuristic);
						childNodes.add(searchNode);
					}
				}
			}
		}
		if (searchStrategy.equalsIgnoreCase("best")) {
			Collections.sort(OPEN);
		} else if (searchStrategy.equalsIgnoreCase("beam")) {
			beamOPEN.add(childNodes); // Adding the children nodes to another outer linked list beamOPEN.
		}
	}

	static int calculateHeuristic(String hypertext) {
		int heuristic = 0;
		String hypertextcopy = hypertext.toLowerCase();
		hypertext = hypertext.toLowerCase();
		
		while (hypertext.contains("social")) {
				int index = hypertext.indexOf("social");
				hypertext = hypertext.substring(index + 6, hypertext.length());
				heuristic += 500;
		}
		while (hypertextcopy.contains("science")) {
				int index = hypertextcopy.indexOf("science");
				hypertextcopy = hypertextcopy.substring(index + 6, hypertextcopy.length());
				heuristic += 500;
		}
		return heuristic;
	}

	// A GOAL is a page that contains the goalPattern set above.
	static boolean isaGoalNode(String contents) {
		return (contents != null && contents.indexOf(GOAL_PATTERN) >= 0);
	}

	static boolean alreadyInOpen(String hyperlink) {
		int length = OPEN.size();

		for (int i = 0; i < length; i++) {
			SearchNode node = OPEN.get(i);
			String oldHyperlink = node.getNodeName();

			if (hyperlink.equalsIgnoreCase(oldHyperlink))
				return true; // Found it.
		}

		return false; // Not in OPEN.
	}

	static SearchNode pop(LinkedList<SearchNode> list) {
		SearchNode result = list.removeFirst();
		return result;
	}
}

class SearchNode implements Comparable<SearchNode> {
	final String nodeName;
	SearchNode parent;
	double hValue;

	public double gethValue() {
		return hValue;
	}

	public void sethValue(double hValue) {
		this.hValue = hValue;
	}

	public SearchNode(String name) {
		nodeName = name;
	}

	public void reportSolutionPath() {
		SearchNode s = this;
		int count = 0;
		System.out.print("https://en.wikipedia.org"+s.getNodeName() + "(Goal Node)");
		while (!s.nodeName.equalsIgnoreCase("/wiki/Main_Page")) {
			System.out.print(" <-- https://en.wikipedia.org" + s.getParent().getNodeName());
			s = s.parent;
			count++;
		}
		System.out.println("\nSolution Arc Length: " + count);
	}

	public SearchNode getParent() {
		return parent;
	}

	public void setParent(SearchNode parent) {
		this.parent = parent;
	}

	public String getNodeName() {
		return nodeName;
	}

	@Override
	public int compareTo(SearchNode arg0) { // Comparator method to sort the Linked List collection.
		if (gethValue() > arg0.gethValue())
			return -1;
		else if (gethValue() < arg0.gethValue())
			return 1;
		else
			return 0;
	}
}

class Utilities {
	// In J++, the console window can close up before you read it,
	// so this method can be used to wait until you're ready to proceed.
	public static void waitHere(String msg) {
		System.out.println("");
		System.out.println(msg);
		try {
			System.in.read();
		} catch (Exception e) {
		} // Ignore any errors while reading.
	}

	// This method will read the contents of a webpage, returning it
	// as a string.
	public static synchronized String getFileContents(String fileName) {
		String results = null;
		try {
			Parser parser = new Parser(fileName);
			NodeList list = parser.parse(null);
			results = list.toHtml().toString();
			results = results.replaceAll("="," = ").replaceAll("<", " <").replaceAll(">", " > ");
		} catch (ParserException e) {
			e.printStackTrace();
		}

		return results;
	}
}
