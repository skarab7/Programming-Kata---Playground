#include "solution.h"

PriorityQueue::PriorityQueue() {
}


void PriorityQueue::insert(unsigned int priority, const std::string &str) {
    PriorityQueueNode* pn = new PriorityQueueNode();
    pn->data = str;
    pn->priority = priority;
    m_Queue.push_back(pn);
    bubbleUp();
}


void PriorityQueue::bubbleUp() {
     int parent_offset;
     int child_offset = m_Queue.size() - 1;

     std::vector<PriorityQueueNode*>::iterator it_parent;
     std::vector<PriorityQueueNode*>::iterator it_child;


     while (child_offset > 0) {
        parent_offset = static_cast<int>(
            floor(static_cast<double>(child_offset)/2));

        it_parent = m_Queue.begin() + parent_offset;
        it_child = m_Queue.begin() + child_offset;

        if ((*it_parent)->priority < (*it_child)->priority) {
               PriorityQueueNode* temp = (*it_parent);
               (*it_parent) = (*it_child);
               (*it_child) = temp;
        } else {
               break;
        }
        child_offset = parent_offset;
     }
}


std::string PriorityQueue::next(void) {
    if (m_Queue.size() == 0) {
        throw new std::underflow_error("No element left in the PriorityQueue");
    }

    PriorityQueueNode* max_node = *m_Queue.begin();
    *m_Queue.begin() = *(m_Queue.end() - 1);
    std::string max = max_node->data;
    m_Queue.erase(m_Queue.end() - 1);
    delete max_node;
    bubbleDown();
    return max;
}


void PriorityQueue::bubbleDown(void) {
    int parent_offset = 0;
    int child_offset;

    std::vector<PriorityQueueNode*>::iterator it_parent;
    std::vector<PriorityQueueNode*>::iterator it_child;

    while (parent_offset < m_Queue.size()) {
        child_offset = findChildOffset(parent_offset);
        if (child_offset < 0) break;

        it_parent = m_Queue.begin() + parent_offset;
        it_child = m_Queue.begin() + child_offset;

        if ((*it_parent)->priority < (*it_child)->priority) {
               PriorityQueueNode* temp = (*it_parent);
               (*it_parent) = (*it_child);
               (*it_child) = temp;
        } else {
            break;
        }
        parent_offset = child_offset;
     }
}


int PriorityQueue::findChildOffset(int parent_offset) {
    int child_offset;
    int l_child_offset = 2 * parent_offset;
    int r_child_offset = 2 * parent_offset + 1;

    std::vector<PriorityQueueNode*>::iterator it_parent =
        m_Queue.begin() + parent_offset;
    std::vector<PriorityQueueNode*>::iterator it_child;

    if (l_child_offset > m_Queue.size()) {
        return -1;
    } else {
         child_offset = l_child_offset;
    }

    if (r_child_offset < m_Queue.size()) {
       child_offset = findChildNodeWithLargerValue(
            child_offset, r_child_offset);
    }
    return child_offset;
}


int PriorityQueue::findChildNodeWithLargerValue(int ch_offset_1,
    int ch_offset_2) {
     unsigned int p1 = (*(m_Queue.begin() + ch_offset_1))->priority;
     unsigned int p2 = (*(m_Queue.begin() + ch_offset_2))->priority;
     if (p1 > p2) {
         return ch_offset_1;
     }
     return ch_offset_2;
}
