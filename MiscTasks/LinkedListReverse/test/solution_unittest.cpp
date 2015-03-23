#include "gtest/gtest.h"
#include "../src/solution.h"
#include <limits.h>

TEST(Solution, ThreeElementsList) {
    Node A;
    A.c = 'a';
    Node B;
    B.c = 'b';
    Node C;
    C.c = 'c';
    A.next = &B;
    B.next = &C;
    C.next = NULL;

    EXPECT_EQ(&C, reverse(&A));
}
