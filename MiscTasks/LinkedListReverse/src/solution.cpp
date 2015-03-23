#include "solution.h"

Node* do_reverse(Node* node, Node** new_head) {
   Node* new_prev;
   if(node->next != nullptr) {
       new_prev = do_reverse(node->next, new_head); 
       new_prev->next = node;
       node->next=nullptr;
   }
   else {
       *new_head = node;
       return node;
   }
   return node;   
}

Node *reverse(Node *head) {
	if(head == nullptr) return nullptr;
    Node* new_head;
    do_reverse(head, &new_head);
    return new_head;
}


