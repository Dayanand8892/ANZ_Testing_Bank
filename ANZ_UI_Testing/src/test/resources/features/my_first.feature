Feature: Login
  As a user I should able to login into my app

  Scenario: I login with valid credential
    Given Launch the url as "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    When User enters details as below
      | Application type | Number of dependants | Your annual income (before tax) | Your annual other income (optional) | Monthly living expenses | Current home loan monthly repayments | Other loan monthly repayments | Other monthly commitments | Total credit card limits |
      | Single           | 0                    | 80000                           | 10000                               | 500                     | 0                                    | 100                           | 0                         | 10000                    |
    And click on Work out how much I could borrow button
    When click on Start Over button
    When User enters details as below
      | Monthly living expenses |
      | 1                       |
    And click on Work out how much I could borrow button
    Then Error message is showing as
      | ErrorMessage                                                                                                                                                    |
      | Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500. |

