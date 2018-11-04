Feature: Search interaction description by regex

Scenario: Use simple regex
As a: Programmer

Given we have one interaction in db with description containing 'default'
When we search by regex for 'default'
Then we get list with one interaction id
