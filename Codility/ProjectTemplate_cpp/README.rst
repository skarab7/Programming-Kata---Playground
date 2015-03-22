==========================================
Project Template C++ for Code Challenges
==========================================

A quick (TDD) project template to write solutions for coding challenges in C++


How to setup
----------------

1. Get the googletest framework:

  ::
 
    make setup_google_test
  
  This command will checkout googletest in *tools/googletest*
  
2. Do TDD development in *solution.cpp* and *test/solution_unittest*

3. To test your code run:

.. code::bash

  make solution_unittest && ./solution_unittest


You also have a **make infinititest** command. 

