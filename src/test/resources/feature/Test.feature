Feature: Automation Test

  Background: 
    Given valid when an user accesses to the page "Angular Strangerlist"

  @Assignment
  Scenario: Assignment
    When an item is created
      | Image                             | Text              |
      | src/test/resources/data/Image.png | New item for test |
    And an item is edited "Will is hunted by a strange entity whereas his friends search for him only to find a lonely girl in the woods."
      | Image2                             | Text2                                    |
      | src/test/resources/data/Image2.png | This is the text for the item edited :D. |
    And the created item is deleted "New item for test"
    And the max long in description text is checked
      | Validation                               |
      |                                          |
      | Check a valid long in description        |
      | Check a invalid long in description (more of 300 characters). Check a invalid long in description (more of 300 characters). Check a invalid long in description (more of 300 characters). Check a invalid long in description (more of 300 characters). Check a invalid long in description (more of 300 characters). |
    And an item is validated in the list with the text "Creators: Matt Duffer, Ross Duffer"
   
