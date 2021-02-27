/* * * * * * *
 * Deque module (i.e., double ended queue) for Assignment 1
 *
 * created for COMP20007 Design of Algorithms 2020
 * template by Tobias Edwards <tobias.edwards@unimelb.edu.au>
 * implementation by <Shromann Majumder>
 */

// You must not change any of the code already provided in this file, such as
// type definitions, constants or functions.
//
// You may, however, add additional functions and/or types which you may need
// while implementing your algorithms and data structures.

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "deque.h"
#include "util.h"

// Create a new empty Deque and return a pointer to it
Deque *new_deque() {
	Deque *deque = malloc(sizeof(*deque));
	assert(deque);

	deque->top = NULL;
	deque->bottom = NULL;
	deque->size = 0;

	return deque;
}

// Free the memory associated with a Deque
void free_deque(Deque *deque) {
  // Remove (and thus free) all of the nodes in the Deque.
  while (deque->size > 0) {
    deque_remove(deque);
  }

	// Free the deque struct itself
	free(deque);
}

// Create a new Node with a given piece of data
Node *new_node(Data data) {
  Node *node = malloc(sizeof(*node));
  assert(node);

  node->next = NULL;
  node->prev = NULL;
  node->data = data;

  return node;
}

// Free the memory associated with a Node
void free_node(Node *node) {
  free(node);
}

// Add an element to the top of a Deque
void deque_push(Deque *deque, Data data) {
  Node *new = new_node(data);

  if (deque->size > 0) {
    new->next = deque->top;
    deque->top->prev = new;
  } else {
    // If the Deque was initially empty then new is both the top and bottom
    deque->bottom = new;
  }

  deque->top = new;
  deque->size++;
}

// Add an element to the bottom of a Deque
void deque_insert(Deque *deque, Data data) {
  Node *new = new_node(data);

  if (deque->size > 0) {
    new->prev = deque->bottom;
    deque->bottom->next = new;
  } else {
    // If the Deque was initially empty then new is both the top and bottom
    deque->top = new;
  }

  deque->bottom = new;
  deque->size++;
}

// Remove and return the top element from a Deque
Data deque_pop(Deque *deque) {
  if (deque->size == 0) {
    exit_with_error("can't pop from empty Deque");
  }

  Data data = deque->top->data;
  Node *old_top = deque->top;

  if (deque->size == 1) {
    deque->top = NULL;
    deque->bottom = NULL;
  } else {
    deque->top = old_top->next;
    deque->top->prev = NULL;
  }

  deque->size--;

  free(old_top);

  return data;
}

// Remove and return the bottom element from a Deque
Data deque_remove(Deque *deque) {
  if (deque->size == 0) {
    exit_with_error("can't remove from empty Deque");
  }

  Data data = deque->bottom->data;
  Node *old_bottom = deque->bottom;

  if (deque->size == 1) {
    deque->top = NULL;
    deque->bottom = NULL;
  } else {
    deque->bottom = old_bottom->prev;
    deque->bottom->next = NULL;
  }

  deque->size--;

  free(old_bottom);

  return data;
}

// Return the number of elements in a Deque
int deque_size(Deque *deque) {
  return deque->size;
}

// Print the Deque on its own line with the following format:
//   [x_1, x_2, ..., x_n]
//     ^              ^
//    top           bottom
void print_deque(Deque *deque) {
  Node *current = deque->top;
  int i = 0;

  printf("[");

  while (current) {
    printf("%d", current->data);
    // Print a comma unless we just printed the final element
    if (i < deque->size - 1) {
      printf(", ");
    }
    current = current->next;
    i++;
  }

  printf("]\n");
}

/*-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_*/

// Reverse the Deque using an iterative approach
void iterative_reverse(Deque *deque) {

  // swap the top and bottom nodes
  Node* node;
  node = deque->top;
  deque->top = deque->bottom;
  deque->bottom = node;

  // // linearly traverse thru the deque and swap the next pointer with the prev pointer. 
  while(node != NULL){
    swap_direction(node);   
    node = node->prev; 
  }
  
}

/*-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_*/

// Reverse the Deque using a recursive approach
void recursive_reverse(Deque *deque) {
  // reverse the bottom with the top node
  deque->bottom = deque->top; 
  // since 'reverse_path' returns the last node of deque, use that
  // to swap with top and bottom of
  deque->top = reverse_path(deque->top);
}


// Split the Deque given a critical value k, such that the Deque contains
// all elements greater than equal to k above (i.e., closer to the top)
// the elements less than k.
//
// Within the two parts of the array (>= k and < k) the elements should
// be in their original order.
//
// This function must run in linear time.

/*-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_*/

void split_deque(Deque *deque, int k) {

  Node* new = deque->top;
  Node* next_node;
  Node* original_bottom = deque->bottom;

  // traverse thru the deque
  while(new != NULL){
    next_node = new->next;

  // if a value < k
    if(new->data < k){

      // take that node out and link up the hole
      if(!new->prev) {
        new->next->prev = NULL;
        deque->top = new->next;
      } else if(!new->next) {
        new->prev->next = NULL;
        deque->bottom = new->prev;
      } else {
        new->prev->next = new->next;
        new->next->prev = new->prev;
      }
      // put the node in the bottom of the deque
      new->next = NULL; 
      new->prev = deque->bottom;
      deque->bottom->next = new; 
      deque->bottom = new; 

    }
    // so the last node is proccessed too 
    if(new == original_bottom){
      break; 
    }
    new = next_node; 
  }
}

// TODO: Add any other functions you might need for your Deque module

// This function swaps the pointer to the next node and with the previous node. 
void swap_direction(Node* node){
  Node* next_node = node->next; 
  node->next = node->prev; 
  node->prev = next_node; 
}

// Reverse the path of the nodes
Node* reverse_path(Node* node) { 

  // if the list is empty, then return NULL 
  if (node == NULL){ 
    return NULL; 
  }

  // if its not empty then, swap the next and the prev pointers
  swap_direction(node);

  // Break Case: when the function is done traversing, return the node
  // which returns the last node of the deque. 
  if(node->prev == NULL){ 
    return node;
  }

  // else continue 
  return reverse_path(node->prev); 
} 