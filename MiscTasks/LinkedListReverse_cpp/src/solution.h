#include<iostream>

struct Node {
    Node *next;
    char c;
};

Node* do_reverse(Node* node, Node** new_head);

Node *reverse(Node *head);


