/* * * * * * *
 * Park Ranger module for Assignment 1
 *
 * created for COMP20007 Design of Algorithms 2020
 * template by Tobias Edwards <tobias.edwards@unimelb.edu.au>
 * implementation by <Shromann Majumder>
 */

#include <stdbool.h>
#include <stdio.h>

#include "parkranger.h"
#include "util.h"

// This function must read in a ski slope map and determine whether or not
// it is possible for the park ranger to trim all of the trees on the ski slope
// in a single run starting from the top of the mountain.
//
// The ski slope map is provided via stdin in the following format:
//
//   n m
//   from to
//   from to
//   ...
//   from to
//
// Here n denotes the number of trees that need trimming, which are labelled
// {1, ..., n}. The integer m denotes the number "reachable pairs" of trees.
// There are exactly m lines which follow, each containing a (from, to) pair
// which indicates that tree `to` is directly reachable from tree `from`.
// `from` and `to` are integers in the range {0, ..., n}, where {1, ..., n}
// denote the trees and 0 denotes the top of the mountain.
//
// For example the following input represents a ski slope with 3 trees and
// 4 reachable pairs of trees.
//
// input:            map:          0
//   3 4                          / \
//   0 1                         /  2
//   0 2                        / /
//   2 1                        1
//   1 3                          \
//                                 3
//
// In this example your program should return `true` as there is a way to trim
// all trees in a single run. This run is (0, 2, 1, 3).
//
// Your function should must:
//  - Read in this data from stdin
//  - Store this data in an appropriate data structure
//  - Run the algorithm you have designed to solve this problem
//  - Do any clean up required (e.g., free allocated memory)
//  - Return `true` or `false` (included in the stdbool.h library)
//
// For full marks your algorithm must run in O(n + m) time.
bool is_single_run_possible() {

    int n, m; 
    int u, v;
    int* path;
    

    get_values(&n, &m);                       // Get the n and m values from the text input

    graph_t *forest = create_graph(n);        // Create a graph of n + 1 size (incl. mountain)
    while(get_values(&u, &v)){
        append(forest, u, v);                 // Store the data in the adjacency list 
    }

    path = FindPath(forest);    
    return isSinglePath(forest, path);        // Finally, return the path is continious and output accordingly
    
}

// TODO: Add any additional functions or types required to solve this problem.
struct node {
    int vertex;
    node_t *next;
};

struct graph {
    int vertex_count;
    node_t **list;
};


int get_values(int *x, int *y){
    return scanf("%d %d\n", x, y) != EOF;
}

node_t* create_node(int v){
    node_t *new = (node_t *)malloc(sizeof(node_t*));
    new->vertex = v;
    new->next = NULL;
    return new;
}

graph_t* create_graph(int vertices){
    vertices++; // we increase the vertices by one to include the mountain node. 
    graph_t* graph = (graph_t*)malloc(sizeof(graph_t*));
    graph->vertex_count = vertices;
    graph->list = malloc(vertices * sizeof(struct node*));
 
    int i;
    for (i = 0; i < vertices; i++)
        graph->list[i] = NULL;
 
    return graph;
}

void append(graph_t* graph, int u, int v){
    node_t* new = create_node(v);
    new->next = graph->list[u];
    graph->list[u] = new;
}


int* create_list(int size){
    int* visited = (int*)malloc(size * sizeof(int));
    int i;
    for(i = 0; i < size; i++){
        visited[i] = false; 
    }
    return visited; 
}

int* FindPath(graph_t* graph){
    int* visited = create_list(graph->vertex_count);
    int* path = create_list(graph->vertex_count);
    int n = graph->vertex_count - 1;
    
    int v;  
    for(v = 0; v < graph->vertex_count; v++){
        if(!visited[v]){
            n = dfsFindPath(graph, v, visited, path, n);
        }
    }
    
    return path; 
}

int dfsFindPath(graph_t* graph, int v, int* visited_list, int* path, int n){

    //-- dfs : 

    visited_list[v] = true; 
    // printf("visiting: %d\n", v);
    
    node_t *child = graph->list[v];
    while(child != NULL){
        if(!visited_list[child->vertex]){
            // printf("%d -> %d\n", v, child->vertex);
            n = dfsFindPath(graph, child->vertex, visited_list, path, n);
        }
        child = child->next;
    }

    //-- FindPath:
    path[n] = v;
    return n-1;
}

bool isSinglePath(graph_t* graph, int* path){ 
    node_t *node;
    bool continous = false;
    int i; 
    for(i = 0; i < ((graph->vertex_count)-1); i++){

        node = graph->list[path[i]];
        continous = false;

        while (node != NULL){
            if(path[i+1] == node->vertex){
                continous = true;
                break;
            }
            node = node->next;
        }

        if(!continous){
            return false; 
        } 
    }
    return continous; 
}


