#include "gtest/gtest.h"
#include "../src/solution.h"
#include <limits.h>

TEST(Solution, TwoStrings) {
	PriorityQueue pq = PriorityQueue();
	std::string x = "xxx";
	std::string w = "www";
	
	pq.insert(10, w);
	pq.insert(20, x);
    EXPECT_EQ(x, pq.next());
	EXPECT_EQ(w, pq.next());
    //How to handle when there are no elements? EXPECT_TRUE(NULL == pq.next);
}

TEST(Solution, TwoSamePrioString) {
	PriorityQueue pq = PriorityQueue();
	std::string x = "xxx";
	std::string w = "www";
    std::string y = "yyy";

	pq.insert(20, x);
	pq.insert(20, w);
    pq.insert(10, y);	
	
    std::string call1 = pq.next();
    EXPECT_TRUE(call1 == w || call1 == x );
	std::string call2 = pq.next();
    EXPECT_TRUE(call2 == w || call2 == x );
	//How to handle when there are no elements? EXPECT_TRUE(NULL == pq.next());
    EXPECT_EQ(y, pq.next());
}


TEST(Solution, NextOnEmptyQueue) {
	
	EXPECT_TRUE(1 == 0);
}
