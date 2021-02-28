/* * * * * * *
 * Text Analysis module for Assignment 2.
 *
 * created for COMP20007 Design of Algorithms 2020
 * template by Tobias Edwards <tobias.edwards@unimelb.edu.au>
 * implementation by <Shromann Majumder>
 */

#include "text_analysis.h"

void problem_2_a() {
  int N, i;
  scanf("%d\n", &N);

  node_t *root = init_node(START);

  char text[MAXCHARSIZE];
  for (i = 0; i < N; i++){
    scanf("%s\n", text);
    build(root, text); 
  }

  traverse(root); 
  free_trie(root);
}

void problem_2_b() {
  int N, K, i;
  scanf("%d %d\n", &N, &K);
  
  node_t *root = init_node(START);
  char text[MAXCHARSIZE];
  for (i = 0; i < N; i++){
    scanf("%s\n", text);
    build(root, text); 
  }

  prefix_print(root,text,K,0);
  free_trie(root);
}

void problem_2_c() {
  int N,i;
  char stub[MAXCHARSIZE];
  scanf("%d\n%s\n", &N, stub);

  node_t *root = init_node(START);
  char text[MAXCHARSIZE];
  for (i = 0; i < N; i++){
    scanf("%s\n", text);
    build(root, text); 
  }

  Deque *topfive = new_deque(); 
  print_deque(topfive, pr(root, stub, topfive));
  free_deque(topfive);
  free_trie(root);
}

// ____________________________ Helper Functions  ______________________________

//------------------------------------Tries-------------------------------------
// build a trie with the word give 
void build(node_t *root, char text[MAXCHARSIZE]){ 
  node_t *temp = root;
  int i, key;
  for(i=0;i<strlen(text);i++){
    key = hashf(text[i]);
    if(!temp->child[key]){
      temp->child[key] = init_node(text[i]);
    }
    temp = temp->child[key];
    temp->freq = temp->freq + 1;
  }
  temp->isWord = temp->isWord + 1;
} 
// initilise a node 
node_t *init_node(char c) { 
  node_t *new = NULL; 
  new = (node_t *)malloc(sizeof(node_t)); 
  if (new) { 
    int i; 
    for (i = 0; i < ALPHABET_SIZE; i++){ 
      new->child[i] = NULL; 
    }
      
  }
  new->isWord = 0; 
  new->data = c;
  new->freq = 0;  
  return new; 
} 
// travese pre-orderly thru the trie
void traverse(node_t *root){
  int i; 
  printf("%c\n", root->data);
  if(root->isWord){
    printf("%c\n",END);
  }
  for(i = 0; i < ALPHABET_SIZE; i++){
    if(root->child[i]){
      traverse(root->child[i]); 
    }
  }
}
// return the index of the child array for a particular charactar 
int hashf(char c){
  return (c - STARTINGLETTER);
}
// prints all prefixes of length k 
void prefix_print(node_t *root, char str[MAXCHARSIZE], int K, int level){
  if (level == K){ 
      str[level] = NULLBYTE;
      printf("%s %d\n", str, root->freq);  
  } 
  int i; 
  for (i = 0; i < ALPHABET_SIZE; i++){
    if (root->child[i]){ 
      str[level] = i + 'a'; 
      prefix_print(root->child[i], str, K,level + 1); 
    } 
  } 
} 
// makes a deque full possible words that have 'text' as the prefix 
// and thier frqueinces of the word
int pr(node_t *root,char stub[MAXCHARSIZE], Deque* topfive){
  node_t *trav = root;
  int i;
  for(i = 0; i < strlen(stub); i++){
    trav = trav->child[hashf(stub[i])];
  }
  getWords(trav, stub, topfive);  
  return trav->freq; 
}
// gets all the words that from prefix and adds them to the deque 
// in order: decreasing word frequencies and alphabetically 
void getWords(node_t *root, char stub[MAXCHARSIZE], Deque *topfive) {
	if (root->isWord || noChildren(root)){
    Node *new = new_dnode(stub, root->isWord);
    sortedInsert(&topfive, new);
  } 
	for (int i = 0; i < ALPHABET_SIZE; i++){
		if(root->child[i] != NULL){
      int original_len = strlen(stub); 
      strcat(stub, &root->child[i]->data);
			getWords(root->child[i], stub, topfive);
      stub[original_len] = NULLBYTE;
    }
  }
}
// returns true if a node has no children and false other wise
bool noChildren(node_t *root){
  int i;
  for (i = 0; i < ALPHABET_SIZE; i++){
    if(root->child[i]){
      return false; 
    }
  }
  return true; 
}
// free trie
void free_trie(node_t* root){
    int i;
    if(!root){
      return;  
    }
    // recursive case (go to end of trie)
    for (i = 0; i < ALPHABET_SIZE; i++){
      free_trie(root->child[i]);
    }
    // base case
    free(root);
}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
