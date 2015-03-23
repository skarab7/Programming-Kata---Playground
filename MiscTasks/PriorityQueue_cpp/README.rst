======================
PriorityQueue in C++
======================

As in the title, based on ProjectTemplate_cpp (you can find it in this repo).


How to setup
----------------

1. Get the googletest framework:

  ::
 
    make setup_google_test
  
  This command will checkout googletest in *tools/googletest*
  
2. Do TDD development in *solution.cpp*, *solution.h*, and *test/solution_unittest*

3. To test your code run:

.. code::bash

  make solution_unittest && ./solution_unittest


You also have a **make infinititest** command, so you can do TDD with tests running on every change. 

