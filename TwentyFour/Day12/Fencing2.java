package TwentyFour.Day12;

import java.util.*;

public class Fencing2 {

    // Directions: up, right, down, left
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private enum Dir { UP, RIGHT, DOWN, LEFT }

    public static void main(String[] args) {
        List<List<Character>> input = Input.readFile("TwentyFour/Day12/input.txt");
        boolean[][] visitedRegions = new boolean[input.size()][input.get(0).size()];
        long totalCost = 0L;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                if (visitedRegions[i][j]) continue;

                char currentPlant = input.get(i).get(j);

                // Collect all cells of this region and mark them visited
                List<int[]> regionCells = new ArrayList<>();
                floodCollect(input, currentPlant, i, j, visitedRegions, regionCells);

                // Compute area:
                long A = regionCells.size();

                // Compute boundary edges of the region
                List<Edge> boundaryEdges = getRegionBoundaryEdges(input, currentPlant, regionCells);

                // Convert edges into a corner graph and find all loops
                long S = computeSidesFromBoundary(boundaryEdges);

                System.out.println("A region of " + currentPlant + " plants with price " + A + " * " + S + " = " + (A * S) + ".");
                totalCost += A * S;
            }
        }

        System.out.println(totalCost);
    }

    // Collect all cells of a region using a flood fill
    private static void floodCollect(List<List<Character>> input, char plant, int i, int j, boolean[][] visited, List<int[]> cells) {
        int rows = input.size();
        int cols = input.get(0).size();
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{i,j});
        visited[i][j] = true;

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            cells.add(cell);
            int r = cell[0], c = cell[1];
            for (int[] d : DIRECTIONS) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nc >= 0 && nr < rows && nc < cols) {
                    if (!visited[nr][nc] && input.get(nr).get(nc) == plant) {
                        visited[nr][nc] = true;
                        stack.push(new int[]{nr,nc});
                    }
                }
            }
        }
    }

    // Represents a boundary edge of the region
    // We'll store the cell and the direction indicating which side is a boundary.
    private static class Edge {
        int r, c; 
        Dir dir;
        Edge(int r, int c, Dir dir) { this.r=r; this.c=c; this.dir=dir; }
    }

    // Find boundary edges for all cells in the region
    private static List<Edge> getRegionBoundaryEdges(List<List<Character>> input, char plant, List<int[]> regionCells) {
        Set<String> cellSet = new HashSet<>();
        for (int[] cell : regionCells) {
            cellSet.add(cell[0]+","+cell[1]);
        }

        List<Edge> edges = new ArrayList<>();
        int rows = input.size();
        int cols = input.get(0).size();

        for (int[] cell : regionCells) {
            int r = cell[0], c = cell[1];
            // Check all four sides
            for (int d = 0; d < DIRECTIONS.length; d++) {
                int nr = r + DIRECTIONS[d][0];
                int nc = c + DIRECTIONS[d][1];

                boolean isBoundary = false;
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) {
                    // Outside grid
                    isBoundary = true;
                } else {
                    // Different plant?
                    if (input.get(nr).get(nc) != plant) {
                        isBoundary = true;
                    }
                }

                if (isBoundary) {
                    edges.add(new Edge(r, c, dirFromIndex(d)));
                }
            }
        }

        return edges;
    }

    private static Dir dirFromIndex(int d) {
        switch(d) {
            case 0: return Dir.UP;
            case 1: return Dir.RIGHT;
            case 2: return Dir.DOWN;
            default:return Dir.LEFT;
        }
    }

    // A corner is defined by coordinates in a "corner grid" which is (row, col) of the grid lines
    private static class Corner {
        int r, c;
        Corner(int r, int c) { this.r=r; this.c=c; }
        @Override public boolean equals(Object o) {
            if (!(o instanceof Corner)) return false;
            Corner other = (Corner)o;
            return r==other.r && c==other.c;
        }
        @Override public int hashCode() { return Objects.hash(r,c); }
    }

    // For each boundary edge, find its start and end corners
    // UP edge: from (r, c) to (r, c+1)
    // RIGHT edge: from (r, c+1) to (r+1, c+1)
    // DOWN edge: from (r+1, c) to (r+1, c+1)
    // LEFT edge: from (r, c) to (r+1, c)
    private static class PolygonEdge {
        Corner start, end;
        PolygonEdge(Corner s, Corner e) { start=s; end=e; }
    }

    private static long computeSidesFromBoundary(List<Edge> boundaryEdges) {
        // Build graph of corners
        Map<Corner, List<Corner>> graph = new HashMap<>();

        // Convert each edge to corners
        for (Edge e : boundaryEdges) {
            Corner startCorner = null, endCorner = null;
            int r = e.r;
            int c = e.c;
            switch (e.dir) {
                case UP:
                    startCorner = new Corner(r, c);
                    endCorner = new Corner(r, c+1);
                    break;
                case RIGHT:
                    startCorner = new Corner(r, c+1);
                    endCorner = new Corner(r+1, c+1);
                    break;
                case DOWN:
                    startCorner = new Corner(r+1, c);
                    endCorner = new Corner(r+1, c+1);
                    break;
                case LEFT:
                    startCorner = new Corner(r, c);
                    endCorner = new Corner(r+1, c);
                    break;
            }

            // Add to graph (undirected)
            graph.computeIfAbsent(startCorner, k->new ArrayList<>()).add(endCorner);
            graph.computeIfAbsent(endCorner, k->new ArrayList<>()).add(startCorner);
        }

        // The graph may consist of multiple cycles (one outer boundary and possibly inner boundaries)
        // We need to find all cycles. Each connected component of this graph is a cycle because
        // every vertex on a rectilinear polygon boundary has degree 2.
        
        Set<Corner> visited = new HashSet<>();
        long totalSides = 0;

        for (Corner start : graph.keySet()) {
            if (!visited.contains(start)) {
                // Follow the cycle starting from 'start'
                totalSides += countSidesInCycle(graph, start, visited);
            }
        }

        return totalSides;
    }

    // Follow the cycle and count how many sides it has.
    // To count sides, we need directions of each edge along the cycle.
    private static long countSidesInCycle(Map<Corner, List<Corner>> graph, Corner start, Set<Corner> visited) {
        // A cycle: start at 'start', then follow unique path until we return.
        // Each corner has 2 edges. We pick one direction and keep going until we return to start.
        
        Corner current = start;
        Corner prev = null;
        List<Dir> directions = new ArrayList<>();

        while (true) {
            visited.add(current);

            // Get next corner
            List<Corner> neighbors = graph.get(current);
            Corner next = null;
            if (neighbors.size() == 1) {
                // Single edge? This would be strange, should not happen for a proper closed polygon.
                next = neighbors.get(0);
            } else {
                // Two neighbors. Pick the one that isn't 'prev'
                if (prev == null) {
                    next = neighbors.get(0); // arbitrary start direction
                } else {
                    if (neighbors.get(0).equals(prev)) next = neighbors.get(1);
                    else next = neighbors.get(0);
                }
            }

            // Determine direction from current to next
            Dir dir = getDirection(current, next);
            directions.add(dir);

            if (next.equals(start)) {
                // Cycle complete
                break;
            }

            prev = current;
            current = next;
        }

        // Now count how many times direction changes in 'directions'
        // Each segment is one direction. The number of sides is the number of times direction changes + 1.
        // But since it's a closed polygon, the number of segments equals the number of direction changes.
        // Actually, for a rectilinear polygon, the number of sides is exactly the number of edges in the cycle.
        // However, we must consider that consecutive edges in the same direction form a single side.
        // So we combine consecutive edges with the same direction.

        long sides = 1;
        for (int i = 1; i < directions.size(); i++) {
            if (directions.get(i) != directions.get(i-1)) {
                sides++;
            }
        }

        return sides;
    }

    private static Dir getDirection(Corner from, Corner to) {
        if (to.r == from.r && to.c == from.c+1) return Dir.RIGHT;
        if (to.r == from.r && to.c == from.c-1) return Dir.LEFT;
        if (to.r == from.r+1 && to.c == from.c) return Dir.DOWN;
        if (to.r == from.r-1 && to.c == from.c) return Dir.UP;
        throw new RuntimeException("Non-adjacent corners?! from:"+from.r+","+from.c+" to:"+to.r+","+to.c);
    }
}
