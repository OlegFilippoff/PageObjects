

Feature: Login and Transfer Money between cards

  Scenario: Login user plus money transfer of 5000 rub from secondToFirstCard
    Given login page is opened "http://localhost:9999"
    When the field login is filled with "vasya" and password "qwerty123"
    And user inputs a valid code from SMS "12345"
    Then the user gets in a personal account
    When the user transfers money in amount of 5000 RUB from his card 5559000000000002 to his first card
    Then The balance of the card "5559000000000001" is 15000 RUB after popUp


#Feature: Open Browser
#
#  Scenario: Open Chrome on mail.ru
#    Given Open Browser