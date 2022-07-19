# Alexandra Savramis - Notes

I went through the codebase and made some observations based off of things I could improve, such as unused comments and variables, and following conventions such as using "this." before calling private class members.

After increasing the test coverage, I went and improved the codebase, verifying that the tests still passed.

At this point, I discovered a bug in a test that was not testing it properly in which it was not accounting for discounted tickets as the customer's fee.

Afterwards, I implemented the additional features that were requested.

Because I added more functionality to discounts, I eventually decomposed it for readability and added test coverage, specifically covering the edge case of when a discount ends on the hour. I removed the commented out IllegalArgumentException, as it is not necessary to throw an exception for when a discount is not applied.

For serializing the schedule into JSON, I utilized a package from Google - GSON. Since the Showing class has private members, I needed to create a custom adapter to the showing class, defining how to represent a Showing instance as JSON. After implementing this, GSON was able to serialize the Theater class into a JSON string. Since it was not required, I did not implement the deserialization adapter - that would have required more effort since showing is using different classes like Movie, etc. so building a proper deserialization capability would have required writing adapters to all of the different classes being used.

Observations
I noticed that there are still variables (customer id) and method override implementations (hashCode) that are not being used. I decided not to remove it for now as if looking at this as a real project, these fundamentals could eventually be built on.

Addiitonally, the Customer class is not being used anywhere. I could potentially remove it, but kept it for the reasons stated above.

The equals override method for Customer and Movie are not being utilized. I added test coverage to verify their implementation is correct and decided not to remove them as future usage may require them.

I removed the LocalDateProvider class as the only logic captured within is calling the java.time.LocalDate model directly, and there is no reason to capture that in a class. I observed that this class was attempting to implement a Singleton design pattern - the Singleton implemented did not follow best practices, but regardless was removed because it was not adding value.

# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format