#include "solution.h"

PriorityQueue::PriorityQueue() {
    it = m_Queue.begin();
}

void PriorityQueue::insert(unsigned int priority, const std::string &str) {
	PriorityQueueNode* pn = new PriorityQueueNode();
	pn->data = str;
    pn->priority = priority;
    it = m_Queue.insert(it, pn);
}

std::string PriorityQueue::next(void) {
    std::vector<PriorityQueueNode*>::iterator b = m_Queue.begin();
    //std::string& data = pn->data;
    //delete pn;
    //m_Queue.erase(it);
    //it--;
    return (*b)->data;
}

bool PriorityQueue::has_next(void) {
    return false;
}
