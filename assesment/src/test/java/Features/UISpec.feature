Feature: To validate the Home Page content of https://reqres.in/.


	Scenario: Verification of Home Page
		Given Open the ChromeBrowser and launch the application			
		When User should able to list different request types, end points	
		Then User should display "sample request and response" details after selecting a specific option
		And User should able to view button to navigate to support page
	
	Scenario: Verify Support page
		Given Open the ChromeBrowser and launch the application			
		When User click on support button
		Then User should list options for one-time & monthly support
		And User should able to provide Upgrade details