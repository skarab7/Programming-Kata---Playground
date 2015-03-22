GTEST_DIR=tools/googletest
USER_DIR = src
USER_TEST_DIR=test

# Flags passed to the preprocessor
CPPFLAGS+=-isystem $(GTEST_DIR)/include

# Flags passsed to the C++ compiler
CXXFLAGS += -g -Wall -Wextra -pthread

# All thest produced by this Makefile
TESTS=solution_unittest

# all Google Test headers
GTEST_HEADERS = $(GTEST_DIR)/include/gtest/*.h \
                $(GTEST_DIR)/include/gtest/internal/*.h

clean:
	rm -f $(TESTS) gtest.a gtest_main.a *.o

# Builds gtest.a and gtest_main.a.

# Usually you shouldn't tweak such internal variables, indicated by a
# trailing _.
GTEST_SRCS_ = $(GTEST_DIR)/src/*.cc $(GTEST_DIR)/src/*.h $(GTEST_HEADERS)

# For simplicity and to avoid depending on Google Test's
# implementation details, the dependencies specified below are
# conservative and not optimized.  This is fine as Google Test
# compiles fast and for ordinary users its source rarely changes.
gtest-all.o : $(GTEST_SRCS_)
	$(CXX) $(CPPFLAGS) -I$(GTEST_DIR) $(CXXFLAGS) -c \
	$(GTEST_DIR)/src/gtest-all.cc

gtest_main.o : $(GTEST_SRCS_)
	$(CXX) $(CPPFLAGS) -I$(GTEST_DIR) $(CXXFLAGS) -c \
	$(GTEST_DIR)/src/gtest_main.cc

gtest.a : gtest-all.o
	$(AR) $(ARFLAGS) $@ $^

gtest_main.a : gtest-all.o gtest_main.o
	$(AR) $(ARFLAGS) $@ $^


# Builds a sample test.  A test should link with either gtest.a or
# gtest_main.a, depending on whether it defines its own main()
# function.
solution.o : $(USER_DIR)/solution.cpp $(GTEST_HEADERS)
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $(USER_DIR)/solution.cpp

solution_unittest.o: $(USER_TEST_DIR)/solution_unittest.cpp $(USER_DIR)/solution.cpp $(GTEST_HEADERS)
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $(USER_TEST_DIR)/solution_unittest.cpp

solution_unittest : solution.o solution_unittest.o gtest_main.a
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -lpthread $^ -o $@

#solution.o: src/solution.cpp
#	g++ $(CPPFLAGS) -o build/solution.o -c src/solution.cpp

setup_google_test:
	mkdir -p tools ;
	if [ ! -d $(GTEST_DIR) ]; then \
		svn checkout http://googletest.googlecode.com/svn/trunk/ $(GTEST_DIR); \
	fi;

#solution_unittest.o : $(SRC_DIR)/solution_unittest.cpp \
#                     $(SRC_DIR)/sample1.h $(GTEST_HEADERS)
#	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $(USER_DIR)/sample1_unittest.cc
#solution_unittest : solution.o solution_unittest.o gtest_main.a
#	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -lpthread $^ -o $@