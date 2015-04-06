#include <vector>
#include "../src/solution.h"
#include "gtest/gtest.h"



using namespace std;

TEST(Solution, SmallExample) {
    Solution* s = new Solution();
    vector<int>* A = new vector<int>();
    int input[2] = {1, 1};

    for (int i = 0; i< 2; i++) {
        A->push_back(input[i]);
    }

    EXPECT_EQ(1, s->solution(*A));
    EXPECT_EQ(3, s->solution(*A));
}

TEST(Solution, OneElement) {
    Solution* s = new Solution();
    vector<int>* A = new vector<int>();
    int input[1] = {1};

    for (int i=0; i< 1; i++) {
        A->push_back(input[i]);
    }

    EXPECT_EQ(1, s->solution(*A));
    EXPECT_EQ(3, s->solution(*A));
}


// TwoElements

// LargeInput

// LargeInput2

// CornerCase1

// CornerCase2
