#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "algorithm.h"
#include "heap.h"

EDGELIST *mst_prim(GRAPH *g, int start) {
    // your code
  
    if (g == NULL) return NULL;
    int i, heapindex, u, n = g->order, T[n], parent[n];
    for (i = 0; i < n; i++) T[i] = 0;
    for (i = 0; i < n; i++) parent[i] = -1;

    HEAPDATA hn;
    HEAP *h = new_heap(4);

    T[start] = 1;
    ADJNODE *temp = g->nodes[start]->neighbor;
    while (temp) {
        hn.key = temp->weight;
        hn.value = temp->nid;
        heap_insert(h, hn);
        parent[temp->nid] = start;
        temp = temp->next;
    }

    EDGELIST *mst = new_edgelist();
    while (h->size > 0) {
        hn = heap_extract_min(h);
        i = hn.value;
        T[i] = 1;
        insert_edge_end(mst, parent[i], i, hn.key);
        temp = g->nodes[i]->neighbor;
        while (temp) {
            heapindex = heap_search_value(h, temp->nid);
            if (heapindex >= 0) {
                if (T[temp->nid] == 0 && temp->weight < h->hda[heapindex].key) {
                    heap_change_key(h, heapindex, temp->weight);
                    T[temp->nid] = i;
                }
            } else {
                if (T[temp->nid] == 0) {
                    hn.key = temp->weight;
                    hn.value = temp->nid;
                    heap_insert(h, hn);
                    parent[temp->nid] = i;
                }
            }
            temp = temp->next;
        }
    }
    return mst;
}

EDGELIST *spt_dijkstra(GRAPH *g, int start) {
    if (!g)
    return NULL;

    int i, heapindex, n = g->order, T[n], label[n], parent[n];
    EDGELIST *spt = new_edgelist();
    HEAP *h = new_heap(4);
    HEAPDATA hn;

    for (i = 0; i < n; i++)
    {
        T[i] = 0;
        label[i] = 9999;
        parent[i] = -1;
    }

    ADJNODE *temp = g->nodes[start]->neighbor;
    label[start] = 0;
    T[start] = 1;

    while (temp)
    {
        hn.key = temp->weight + label[start];
        hn.value = temp->nid;
        heap_insert(h, hn);
        parent[temp->nid] = start;
        temp = temp->next;
    }

    while (h->size > 0)
    {
        hn = heap_extract_min(h);
        i = hn.value;
        T[i] = 1;
        label[i] = hn.key;
        insert_edge_end(spt, parent[i], i, label[i] - label[parent[i]]);

        temp = g->nodes[i]->neighbor;
        while (temp)
        {
            heapindex = heap_search_value(h, temp->nid);
            if (heapindex >= 0)
            {
                if (T[temp->nid] == 0 && temp->weight + label[i] < h->hda[heapindex].key)
                {
                    heap_change_key(h, heapindex, temp->weight + label[i]);
                    parent[temp->nid] = i;
                }
            }
            else
            {
                if (T[temp->nid] == 0)
                {
                    hn.key = temp->weight + label[i];
                    hn.value = temp->nid;
                    heap_insert(h, hn);
                    parent[temp->nid] = i;
                }
            }
            temp = temp->next;
        }
    }
    return spt;
}



EDGELIST *sp_dijkstra(GRAPH *g, int start, int end) {
    // your code

    if (!g)
    return NULL;

    int i, heapindex, n = g->order, T[n], label[n], parent[n];
    EDGELIST *spt = new_edgelist();
    HEAP *h = new_heap(4);
    HEAPDATA hn;

    for (i = 0; i < n; i++)
    {
        T[i] = 0;
        label[i] = 9999;
        parent[i] = -1;
    }

    ADJNODE *temp = g->nodes[start]->neighbor;
    label[start] = 0;
    T[start] = 1;

    while (temp)
    {
        hn.key = temp->weight + label[start];
        hn.value = temp->nid;
        heap_insert(h, hn);
        parent[temp->nid] = start;
        temp = temp->next;
    }

    while (h->size > 0)
    {
        hn = heap_extract_min(h);
        i = hn.value;
        T[i] = 1;
        label[i] = hn.key;
        insert_edge_end(spt, parent[i], i, label[i] - label[parent[i]]);

        temp = g->nodes[i]->neighbor;
        while (temp)
        {
            heapindex = heap_search_value(h, temp->nid);
            if (heapindex >= 0)
            {
                if (T[temp->nid] == 0 && temp->weight + label[i] < h->hda[heapindex].key)
                {
                    heap_change_key(h, heapindex, temp->weight + label[i]);
                    parent[temp->nid] = i;
                }
            }
            else
            {
                if (T[temp->nid] == 0)
                {
                    hn.key = temp->weight + label[i];
                    hn.value = temp->nid;
                    heap_insert(h, hn);
                    parent[temp->nid] = i;
                }
            }
            temp = temp->next;
        }
        
    }
    EDGELIST *sp = new_edgelist();
    i = end;
    while(1) {
        if(i == start)break;
        insert_edge_start(sp, parent[i], i, label[i] - label[parent[i]]);
        i = parent[i];
    }

    return sp;  
}