Feature: Feature to Create a user journey test in Komi - A Talent Profile Page 

  Scenario: Click on the Webpage and Test the content and Features in the webpage
    Given User Open the Webpage "https://testdummy.komi.io/"
   	Then User checks if all the thumbnail images are loaded and visible
    And Clicking on the section title at the top takes you to the right module
    Then User clicks on the side navigation arrow on the Music Tracks module and validates the paging and data.
    Then User clicks on “Pre-Save” button and validates the button changes to “Pre-saved”
    And User clicks on a Youtube video and asserts that it redirects successfully
    When Clicks on the More button in the Single Music module validates and redirects to all relevant links
    Then User clicks on a Subscription module and fills the form "<name>","<email>" successfully
 		And User close the webpage
    Examples:
	 | name  | email |
	 | Auto Test | autotest@komi.com|