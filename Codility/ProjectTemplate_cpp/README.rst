=========================
ProjectTemplate in C++
=========================

Project template for coding exercises 


How to setup
----------------

1. Get the googletest framework:

  ::
 
    make setup_google_test
  
  This command will checkout googletest in *tools/googletest*
  
2. Do TDD development in *solution.cpp*, *solution.h*, and *test/solution_unittest*

3. To test your code run:

  ::

    make solution_unittest && ./solution_unittest


You also have a **make infinititest** command, so you can do TDD with tests running on every change. 

Tools
-------

To lint your files:

  ::
    
    # Download linter
    # (you need to do it only once)
    make setup_google_linter

    # run the linter
    make run_googler_linter
  
