#include "string.h" 
#include "avl.h"
#include "set_avl.h"

int set_size(SET *s) {
// your code
    return s->size;
}

int set_contain(SET *s, char *e){
// your code
    return (avl_search(s->root, e))? 1:0;
}

void set_add(SET *s, char *e)
{
// your code
    if (set_contain(s, e)==0) {
        RECORD r = {0};
        strcpy(r.name, e);
        avl_insert(&s->root, r);
        s->size++; 
    }
}

void set_remove(SET *s, char *e)
{
// your code
    avl_delete(&s->root,e);
    s->size--;


}

void set_clean(SET *s){
// your code
    avl_clean(&s->root);
    s->size = 0;

}