Feature: Search interaction description by regex

Scenario: Use simple regex
As a: Programmer

Given we have one interaction in db with description containing 'default'
When we search by regex for 'default'
Then we get list with one interaction id

Scenario: Use simple regex 2

Given we have interaction in db with not matching description
When we search by regex for '12345'
Then we get empty list of interactionIds

Scenario: delete 2 Interactions according to list of ids

Given we have four interactions in db
When we delete two of them:
    |0|
    |1|
Then we have exactly two remaining interactions

Scenario: Can't delete interaction with no matching id

Given we have four interactions in db
When we try to delete interaction with id:
    |999|
Then we still have four interactions in db 