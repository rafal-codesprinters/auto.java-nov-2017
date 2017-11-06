Feature: Verify location and content for Code Sprinters page

  Background:
    Given I go to "Google" webpage

    Scenario: Verify of Google finds Code Sprinters
      When I google for "Code Sprinters"
       And I click link that contains "Code Sprinters -"
      Then I land at "http://agileszkolenia.pl" url
       And I see "szkolenia@codesprinters.com" mail address