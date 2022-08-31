package ru.netology.web.cucumber;
Feature: Login and Transfer Money between cards

  Scenario: Login user plus money transfer of 5000 rub from secondToFirstCard
    Given login page is opened
    When the field login is filled with "validLogin" and password "validPass"
    And user inputs a valid code from SMS "code"
    Then the user gets in a personal account
    When the user transfers money in amount of 5000 RUB from his card 5559 0000 0000 0002 to his first card
    Then The balance of the card is 15000 RUB after popUp


#Feature: Open Browser
#
#  Scenario: Open Chrome on mail.ru
#    Given Open Browser