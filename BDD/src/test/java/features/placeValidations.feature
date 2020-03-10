Feature: Validating place APIs

@Addplace
Scenario Outline: Verify if place is successfully added using AddplaceAPI
     Given AddPlace payload "<name>" "<language>" "<address>"
     When user calls "AddPlaceAPI" http "Post" request
     Then API call got success with status code 200 
     And "status" in response body is "OK"
     And "scope" in response body is "APP"
     And Verify the PlaceID is maps with "<name>" using "GetPlaceAPI"
        
  Examples:
     |name|language|address|
    |leela|english|bangalore|
   # |Girish|Hindi|mysore|
    #|Anitha|Telugu|ananthpuram|
    
    @deletePlace
    Scenario: Verify the deletePlaceAPI is sucessfully working
    Given DeletePlace Paylaod
    When user calls "DeletePlaceAPI" http "Post" request
    Then API call got success with status code 200 
    And "status" in response body is "OK"
    