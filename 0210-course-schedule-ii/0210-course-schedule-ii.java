class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
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
        int[] indegree = new int[numCourses];
        
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
        
        int[] ans = new int[numCourses];
        int count = 0;
        
        // BFS
        while (!q.isEmpty()) {
            
            int u = q.poll();
            ans[count] = u;
            count++;
            
            for (int j = 0; j < adj.get(u).size(); j++) {
                int v = adj.get(u).get(j);
                
                indegree[v]--;
                
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        
        // Check for cycle
        if (count != numCourses) {
            return new int[0];
        }
        
        return ans;
    }
}
