/* * * * * * *
 * Hashing module for Assignment 2.
 *
 * created for COMP20007 Design of Algorithms 2020
 * template by Tobias Edwards <tobias.edwards@unimelb.edu.au>
 * implementation by <Shromann Majumder>
 */

#include "hash.h"

// Implements a solution to Problem 1 (a), which reads in from stdin:
//   N M
//   str_1
//   str_2
//   ...
//   str_N
// And outputs (to stdout) the hash values of the N strings 1 per line.
// Computes the hash value using Horner's Rule.

void problem_1_a() {

  int n, M;
  string str;

  // get n and M values
  scanf("%d %d\n", &n, &M);

  // get n strings and output thier hashvalue thru the hash_function. 
  int i;
  for (i = INITIAL; i < n; i++){
    scanf("%s\n", str);
    printf("%d\n", hash_function(str, M));
  }
}

// Implements a solution to Problem 1 (b), which reads in from stdin:
//   N M K
//   str_1
//   str_2
//   ...
//   str_N
// Each string is inputed (in the given order) into a hash table with size
// M. The collision resolution strategy must be linear probing with step
// size K. If an element cannot be inserted then the table size should be
// doubled and all elements should be re-hashed (in index order) before
// the element is re-inserted.
//
// This function must output the state of the hash table after all insertions
// are performed, in the following format
//   0: str_k
//   1:
//   2: str_l
//   3: str_p
//   4:
//   ...
//   (M-2): str_q
//   (M-1):
void problem_1_b() {

  int N, M, K, i;
  scanf("%d %d %d\n", &N, &M, &K);

  string *hashtable = make_hashtable(M);
  string text;
  for (i = 0; i < N; i++){
    scanf("%s\n", text);
    hashtable = insert(hashtable, text, &M, K);
  }

  for (i = 0; i < M; i++){
    printf("%d: %s\n", i, hashtable[i]);
  }

  free(hashtable); 

}


/* ___________________________ Helper Functions ______________________________*/

// returns if the string is nothing or not 
int is_NULL(string str){
    if (strcmp(str, NULLCHAR) == 0){
        return TRUE;
    }    
    return FALSE;
}

// Makes a new hashtable
string *make_hashtable(int M){
    string *hashtable = (string*)malloc(M*sizeof(string));
    assert(hashtable);
    int i;
    for (i = 0; i < M; i++){
        strcpy(hashtable[i],NULLCHAR);
    }
    return hashtable;
}

// Inserts string into hashtable
string *insert(string *hashtable, string str ,int *M, int K){
    // if there is an empty spot at the hashvalue, 
    // insert the string there. 
    int key = hash_function(str, *M);
    if(is_NULL(hashtable[key])){
        strcpy(hashtable[key], str);
        return hashtable;
    }
    
    // find the next spot in the hashtable and try to insert it there
    int new_key;
    new_key = (key + K) % *M;
    while(new_key != key){
        if(is_NULL(hashtable[new_key])){
            strcpy(hashtable[new_key], str);
            return hashtable; 
        }   
        new_key = (new_key + K) % (*M);  
    }

    // if nothing works, then make a new hashtable double the size of the previous one
    // rehash then add the current string
    string *new_hashtable = make_hashtable((*M)*DOUBLE);
    int j;
    int old_M = *M;
    (*M) *= DOUBLE;
    for (j = 0; j < old_M; j++){
        if(!is_NULL(hashtable[j])){
            new_hashtable = insert(new_hashtable,hashtable[j], M, K); 
        }
    }

    free(hashtable);

    new_hashtable = insert(new_hashtable, str, M, K);
    return new_hashtable; 
}

// This returns 'chr' value of a charactar.
int chr(char c){
  int chr;
  if(SMALLA <= c && c <= SMALLZ){
    chr = c - SMALLA;
  } 
  if(BIGA <= c && c <= BIGZ){
    chr = c - BIGA + NUMBER_OF_ALPHABETS;
  }
  if(ZERO <= c && c <= NINE){
    chr = c - ZERO + (DOUBLE * NUMBER_OF_ALPHABETS);
  }
  return chr;  
}

// This returns the hashvalue value of a charactar under bounds of M.
int hash_function(string str, int M){
  int result = chr(str[INITIAL]); 
  int i;
  for(i = 1; i < strlen(str); i++){
    result = (result * (TWO_POWER_SIX) + chr(str[i])) % M;
  }
  return result % M; 
}
