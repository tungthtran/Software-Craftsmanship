## Authors

Kris Tran - ttt45

# Programming Assignment 2

Programming Assignment 2 gets started with the representation of the type inference’s underlying data structures.

### Prerequisites

A Java IDE
jdk 1.8

### Compling

The codes will be compiled successfully without any errors.

## Running the tests

In this project there will be 2 classes, Dependency and TimePoint class.

The Dependency has 2 private fields, which are previous time point and the duration. These 2 are also the arguments to create a dependency instance. 
If the previous is null in the argument, the object will not be instantiated, and a message will show up saying "Previous time point cannot be null"

The class has two getter methods for the private field, and they could be tested further as they will be called inside methods' bodies of the TimePoint class

The toString() method overrided returns a string representation of the dependency

In order to test the TimePoint class, first you need to create a TimePoint instance. For example: 

TimePoint timepoint = new TimePoint();
TimePoint timepoint2 = new TimePoint();

Next, you can start testing the methods inside the class. 

timepoint.getDependencies() should return a copy of all the dependencies

timepoint.addPrevious(timepoint2, 10) should add a dependency with previous time point timepoint2 and duration 10

	if the time point is frozen or duration is negative then the method will not be executed, and a message will show up and tell users the errors

timepoint.freeze() will freeze the timepoint

timepoint.isFrozen() will return true as it has just been frozen above

timepoint.previousTimePoints() will return the set containing all the previous timepoint, here is only the timepoint 2

timepoint.inDegree() returns the number of dependencies of the timepoint, here is 1

timepoint.isIndependent() return whether this time point has a dependency. As it has 1, it is not independent, thus return false

Calling the 2 toString() method will return a string representation of the timepoint. The difference is toSimpleString() will not return the dependencies of the time point

Try testing with many different cases and many different time points as well as dependencies. Catching no errors -> all the cases have been taken under consideration


