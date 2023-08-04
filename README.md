# GOAL

This small project is the result of my investigations regarding running tests in spring boot and to launch application
context per each test but not to start it once in the beginning and hold for all the test.
I used 2 approaches:
- @SpringBootTest (spring-test + jupiter)
- @RunWith (just junit)

Summary: 
- started testcontainer mongo before all tests
- the first test put some data into mongo
- the second read data from mongo to prove, this mongo is the same.