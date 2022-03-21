Feature: Catalog Filtering

  As an ordinary Onliner user
  I want to sort items in chosen Catalog category
  So that I can choose between sorted items

  Background:
    Given Onliner home page is opened

  Scenario: Sort TVs by given parameters
      When I navigate 'Каталог' section
      And I navigate menu using 'Электроника', 'Телевидение и видео' and 'Телевизоры'
      And I filter items by following parameters
        | Producer       | Samsung             |
        | Max_Price      | 1500                |
        | Min_Diagonal   | 40"                 |
        | Max_Diagonal   | 50"                 |
        | Resolution     | 1920x1080 (Full HD) |
        Then Following search results displayed on the page are correct
          | Producer       |
          | Max_Price      |
          | Diagonal       |
          | Resolution     |