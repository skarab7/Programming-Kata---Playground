#include "gtest/gtest.h"
#include "../src/solution.cpp"
#include <limits.h>

TEST(Solution, Even) {
    Solution* s = new Solution();
    EXPECT_EQ(1, s->getClusters(0));
    EXPECT_EQ(3, s->getClusters(2));
}
