// Defines a priority queue of strings. Valid priorities are in the
// range [0, 99], with 99 being highest priority.
// Given the above class declaration:
// a) Implement the methods of PriorityQueue such that insert() is an O(1)
//   operation and next() is O(n). (This is Big-O notation.)
//
//   Build a priorityQueue around a linked array (std::vector):
//    
//    - O(n) search for the max
//    - O(1) having a pointer to the last element, we can simply add it 
//
//b) Implement the methods of PriorityQueue such that next() is an O(1)
//   operation and insert() is O(n).
//
//   Build a priorityQueue around a Heap:
//
//   - O(1) next(), because we get the max elem on the top
//   - O(n) is the worst case complexity of adding element to a heap 
//
// c) Suggest any changes to the class declaration that you think would
//   be improvements.
// 
//   - a proper iterator, so we know whether there are elements left

#ifndef PRIORITY_QUEUE_HXX
#define PRIORITY_QUEUE_HXX

#include <vector>
#include <string>
#include <math.h>
#include <string>
#include <stdexcept>

class PriorityQueue {
public:
    PriorityQueue();
    void insert(unsigned int priority, const std::string &str);
    std::string next(void);
    void bubbleUp(void);
    void bubbleDown(void);

private:
	
    struct PriorityQueueNode {
        unsigned int priority;
        std::string  data;
    };
    
	// check the O(?) access an element with index k
    std::vector<PriorityQueueNode*> m_Queue;
    
    int findChildNodeWithLargerValue(int ch_offset_1, int ch_offset_2); 
    int findChildOffset(int parent_offset);
};
#endif
