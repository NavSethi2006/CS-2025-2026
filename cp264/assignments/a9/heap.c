/*
 * your program signature
 */ 

 #include <stdio.h>
 #include <stdlib.h>
 #include <string.h> 
 #include "heap.h"
 
 HEAP *new_heap(int capacity)
 {
   HEAP *hp = (HEAP*) malloc(sizeof(HEAP));
   if (hp == NULL) return NULL;
   hp->hda = (HEAPDATA *) malloc(sizeof(HEAPDATA) * capacity);
   if ( hp->hda == NULL) { free(hp); return NULL; };
   hp->capacity = capacity;
   hp->size = 0;
   return hp;
 }
 
 // you may add this function to be used other functions.
 int heapify_up(HEAPDATA *hda, int index) {
 // your code

  int parent = (index - 1) / 2;

  while (index > 0 && hda[index].key < hda[parent].key) {
      // swap child and parent
      HEAPDATA temp = hda[index];
      hda[index] = hda[parent];
      hda[parent] = temp;

      index = parent;
      parent = (index - 1) / 2; 
  }

  return index; // final position
 }
 
 // you may add this function to be used other functions.
 int heapify_down(HEAPDATA *hda, int n, int index) {
 // your code
  while(1) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;
    int smallest = index;

    if(left < n && hda[left].key < hda[smallest].key)
      smallest = left;
    if(right < n && hda[right].key < hda[smallest].key)
      smallest = right;

    if(smallest == index)
      break; 

    HEAPDATA temp = hda[index];
    hda[index] = hda[smallest];
    hda[smallest] = temp;

    index = smallest;
  }
  return index;
 }
 
 void heap_insert(HEAP *heap, HEAPDATA new_node)
 {
 // your code
  if(heap->size == heap->capacity) {
    heap->capacity *= 2;
    heap->hda = realloc(heap->hda, sizeof(HEAPDATA) * heap->capacity);
  }  
  
  if(heap == NULL) {
    heap->hda[0] = new_node;
    heap->size++;
    return;
  }

  heap->hda[heap->size] = new_node;
  heap->size++;
  

 }
 
 HEAPDATA heap_find_min(HEAP *heap)
 {
 // your code
  return heap->hda[0];
 }
 
 HEAPDATA heap_extract_min(HEAP *heap)
 {
 // your code
  HEAPDATA min = heap->hda[0];

  heap->hda[0] = heap->hda[heap->size - 1];
  heap->size--;

  heapify_down(heap->hda, heap->size, 0);

  return min;

 }
 
 int heap_change_key(HEAP *heap, int index, KEYTYPE new_key)
 {
 // your code
  if(index < 0 || index >= heap->size)
    return -1;

  KEYTYPE old_key = heap->hda[index].key;
  heap->hda[index].key = new_key;

  if(new_key < old_key) {
    return heapify_up(heap->hda,index);
  }
  else if (new_key > old_key) {
    return heapify_down(heap->hda, heap->size, index);
  }

  return index;

 }
 
 int heap_search_value(HEAP *heap, VALUETYPE data) {
 // your code
  
  for(int i = 0; i < heap->size; i++) {
    if(heap->hda[i].value == data) {
      return i;
    }
  }
  return -1;
 }
 
 void heap_sort(HEAPDATA *arr, int n){
 // your code
  for(int i = (n/2) - 1; i >= 0; i--) {
    heapify_down(arr, n, i);
  } 

  for(int end = n - 1; end > 0; end--) {
    HEAPDATA temp = arr[0];
    arr[0] = arr[end];
    arr[end] = temp;
    
    heapify_down(arr, end, 0);
  }

 }
 
 void heap_clean(HEAP **heapp) {
   if (heapp) {
     HEAP *heap = *heapp;
     if (heap->capacity > 0) {
       heap->capacity = 0;
       heap->size = 0;
       free(heap->hda);
       free(heap);
     }
     *heapp = NULL;
   }
 }