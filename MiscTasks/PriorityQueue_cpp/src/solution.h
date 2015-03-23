// Defines a priority queue of strings. Valid priorities are in the
// range [0, 99], with 99 being highest priority.
// Given the above class declaration:
// a) Implement the methods of PriorityQueue such that insert() is an O(1)
//   operation and next() is O(n). (This is Big-O notation.)
//b) Implement the methods of PriorityQueue such that next() is an O(1)
//   operation and insert() is O(n).
// c) Suggest any changes to the class declaration that you think would
//   be improvements.


#ifndef PRIORITY_QUEUE_HXX
#define PRIORITY_QUEUE_HXX

#include <vector>
#include <string>

class PriorityQueue {
public:
    PriorityQueue();
    void insert(unsigned int priority, const std::string &str);
    std::string next(void);
	bool has_next(void);
private:
	
    struct PriorityQueueNode {
        unsigned int priority;
        std::string  data;
    };
    
	// check the O(?) access an element with index k
    std::vector<PriorityQueueNode*> m_Queue;
	// keep the pointer to the last element
    std::vector<PriorityQueueNode*>::iterator it;
};
#endif
