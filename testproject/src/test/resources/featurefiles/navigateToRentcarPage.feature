Feature: Navigate to rent car page and test that rent functionality

Scenario Outline: Enter data in the fields and validate the results with  missing inputs

Given Open Chrome and launch the application
When  Enter the Mendetory Fields for car serach
Then  Click on search button and select the first searched car


Given Enter madetory fields 
When  Enter the fields for car serach "<name>" "<lastName>" "<cardNumber>" "<email>"
Then  Click on rent button and check the validations "<errorType>"


Examples:

|name|lastName|cardNumber|email    |errorType|
|john|      |1213456789|test@gm.com|name|
|    |miller|1213456789|test@gm.com|lastName|
|john|miller|1213456789|           |email|
|john|miller|1213456789|testgm.com |invalidEmail|
|john|miller|          |test@gm.com|cardNumber|
|john|miller|1213456789|test@gm.com|valid|
|eric|miller|123456789 |testError@gmcom|valid|



