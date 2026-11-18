class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        int[] indegree = new int[numCourses];
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build graph
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            
            adj.get(b).add(a);
        }
        
        // Calculate indegree
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int v = adj.get(i).get(j);
                indegree[v]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        // Add courses with indegree 0
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        
        int count = 0;
        
        // BFS
        while (!q.isEmpty()) {
            
            int u = q.poll();
            count++;
            
            for (int j = 0; j < adj.get(u).size(); j++) {
                int v = adj.get(u).get(j);
                
                indegree[v]--;
                
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        
        return count == numCourses;
    }
}
