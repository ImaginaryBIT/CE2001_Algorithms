
package graphs;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
 
class Neighbor {
    public int vertexNum;
    public Neighbor next;
    public Neighbor(int vnum, Neighbor nbr) {
            this.vertexNum = vnum;
            next = nbr;
    }
}
 
class Vertex {
    String name;
    Neighbor adjList;
    Vertex(String name, Neighbor neighbors) {
            this.name = name;
            this.adjList = neighbors;
    }
}
 
public class Graph {
 
    Vertex[] adjLists;
     
    public Graph(String file) throws FileNotFoundException {
         
        Scanner sc = new Scanner(new File(file));
         
        String graphType = sc.next();
        boolean undirected=true;
        if (graphType.equals("directed")) {
            undirected=false;
        }
         
        adjLists = new Vertex[sc.nextInt()];
        //src/exampleclass4/test.txt
        // read vertices
        for (int v=0; v < adjLists.length; v++) {
            adjLists[v] = new Vertex(sc.next(), null);
        }
 
        // read edges
        while (sc.hasNext()) {
             
            // read vertex names and translate to vertex numbers
            int v1 = indexForName(sc.next());
            int v2 = indexForName(sc.next());

            // add v2 to front of v1's adjacency list and
            // add v1 to front of v2's adjacency list
            adjLists[v1].adjList = new Neighbor(v2, adjLists[v1].adjList);
            if (undirected) {
                adjLists[v2].adjList = new Neighbor(v1, adjLists[v2].adjList);
            }
        }
    }
     
    int indexForName(String name) {
        for (int v=0; v < adjLists.length; v++) {
            if (adjLists[v].name.equals(name)) {
                return v;
            }
        }
        return -1;
    }   
     
    public void print() {
        System.out.println();
        for (int v=0; v < adjLists.length; v++) {
            System.out.print(adjLists[v].name);
            for (Neighbor nbr=adjLists[v].adjList; nbr != null;nbr=nbr.next) {
                System.out.print(" --> " + adjLists[nbr.vertexNum].name);
            }
            System.out.println("\n");
        }
    }
    public void dfs(String from_city,String to_city) {
        // DFS uses Stack data structure
        Stack stack = new Stack();
        int int_from = indexForName(from_city);
        int int_to = indexForName(to_city);


        //list to store visited index
        ArrayList<Integer> visited_index = new ArrayList<>();

        //dictionary to keep previous routes
        Map<String, String> previous = new HashMap<String, String>();

        stack.push(adjLists[int_from]);
        visited_index.add(int_from);

        while(!stack.isEmpty()) {
            Vertex vtx = (Vertex)stack.peek();
            for (Neighbor nbr=vtx.adjList; nbr != null;nbr=nbr.next) {
                //
                if(!visited_index.contains(nbr.vertexNum)){
                    visited_index.add(nbr.vertexNum);
                    stack.push(adjLists[nbr.vertexNum]);
                    
                    //record/update the previous city
                    previous.put(adjLists[nbr.vertexNum].name,vtx.name);
                    
                    //check if the city is the destination
                    if (adjLists[nbr.vertexNum].name.equals(to_city)){
                        
                        //found the city backtrace the route 
                        String parent_cty = previous.get(adjLists[nbr.vertexNum].name);
                        System.out.println(adjLists[nbr.vertexNum].name);
                        while(previous.get(parent_cty) != null){
                            System.out.println("^");
                            System.out.println(parent_cty);
                            parent_cty = previous.get(parent_cty);
                        }
                        System.out.println("^");
                        System.out.println(parent_cty);
                        //empty the que and break the loop
                        stack = new Stack();
                        break;
                    }
                    
                }

            }
            //stack might be empty because destination is found
            if(!stack.isEmpty()){
                stack.pop();
            }
        }
        System.out.println("\n");
        // Clear visited property of nodes
        visited_index = new ArrayList<>();
    }
    public void bfs(String from_city,String to_city){
        // BFS uses Queue data structure
        Queue queue = new LinkedList();
        int int_from = indexForName(from_city);
        int int_to = indexForName(to_city);
        
        // list to store visited index
        ArrayList<Integer> visited_index = new ArrayList<>();
        
        //dictionary to keep previous routes
        Map<String, String> previous = new HashMap<String, String>();
        
        queue.add(adjLists[int_from]);
        visited_index.add(int_from);
        while(!queue.isEmpty()) {
            Vertex vtx = (Vertex)queue.remove();
            
            //get all childs and check if it is already visited or not
            for (Neighbor nbr=vtx.adjList; nbr != null;nbr=nbr.next) {
                //
                if(!visited_index.contains(nbr.vertexNum)){
                    visited_index.add(nbr.vertexNum);
                    queue.add(adjLists[nbr.vertexNum]);
                    
                    //record/update the previous city
                    previous.put(adjLists[nbr.vertexNum].name,vtx.name);
                    
                    //check if the city is the destination
                    if (adjLists[nbr.vertexNum].name.equals(to_city)){
                        
                        //found the city backtrace the route 
                        String parent_cty = previous.get(adjLists[nbr.vertexNum].name);
                        System.out.println(adjLists[nbr.vertexNum].name);
                        while(previous.get(parent_cty) != null){
                            System.out.println("^");
                            System.out.println(parent_cty);
                            parent_cty = previous.get(parent_cty);
                        }
                        System.out.println("^");
                        System.out.println(parent_cty);
                        //empty the que and break the loop
                        queue = new LinkedList();
                        break;
                    }
                    
                }

            }
              
        }
        System.out.println("\n");
        // Clear visited property of nodes
        visited_index = new ArrayList<>();
    }
    public static void main(String[] args) 
    throws IOException {
        // TODO Auto-generated method stub
    		while(true) 
    		{
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter graph input file name: ");
        String file = sc.nextLine();
        Graph graph = new Graph(file);
        //graph.print();
        
        System.out.println("Enter from city : ");
        String fromCity = sc.nextLine();
        System.out.println("Enter to city : ");
        String toCity = sc.nextLine();
        while(true)
        {
            System.out.println("enter 1 for BFS, 2 for DFS, any other to exit");
            int option = sc.nextInt();
            if(option==1)
            {
                System.out.println("---------BFS----------");
                
                long start = 0;
            
                int runs = 10000; // enough to run for 2-10 seconds. Avoid warm up time.
            
                for (int i = -10000; i < runs; i++) {
                    
                    if (i == 0)
                        start = System.nanoTime();
                    
                    // do test
                    graph.bfs(fromCity,toCity);
                    
                }
            
                long time = System.nanoTime() - start;
            
                System.out.printf("The BFS took %,d ns%n", time / runs);
            }
            else if(option==2)
            {
                System.out.println("---------DFS----------");
                
                long start = 0;
                
                int runs = 10000; // enough to run for 2-10 seconds. Avoid warm up time.
                
                for (int i = -10000; i < runs; i++) {
                    
                    if (i == 0)
                        start = System.nanoTime();
                    
                    // do test
                    graph.dfs(fromCity,toCity);
                    
                }
                
                long time = System.nanoTime() - start;
                
                System.out.printf("The DFS took %,d ns%n", time / runs);
            }
            else
            {
                System.out.println("---------Exit----------");
                break;
                
            }
        	}
    		}
    }
 
}
