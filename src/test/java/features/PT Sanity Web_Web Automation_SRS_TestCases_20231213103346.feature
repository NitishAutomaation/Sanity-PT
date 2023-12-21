Feature: PT Sanity Web_Web Automation_SRS_TestCases_202312131033461
#Regression Type
#Correct Values = true
#Incorrect Values = false
#Illegal Values = false
#Invalid Values = false
#Boundary Values = false
#Edge Cases Values = false

@Network_List
@uida5540265
@set21
@test001
Scenario Outline: verify Network list
Given I have access to application
When I Click if Visible Close popup in login
And I entered Search keyword st in search as '<Search keyword st>'
And I selected Search Trigger sr in search
And I click random Image sr in pdp
And I click if visible Size in pdp
And I scroll down Scroll in pdp as '<Scroll>'
And I scroll and click Add To Cart sr in pdp
And I wait in seconds wait in login as '<wait>'
And I network list capture date in pdp as '<capture date>'
And I selected Go to bag in pdp
And I clicked Home in cart
And I clicked Cart Icon in cart
And I wait in seconds wait in cart as '<wait1>'
And I selected Place Order in shipping address edit
And I entered mobile number in login as '<mobile number>'
Then verify text vfy entered mobile number or not in login
When I wait in seconds wait in login as '<wait2>'
And I entered Email lg in login as '<Email lg>'
And I selected Next in login
And I wait in seconds wait in login as '<wait3>'
And I entered Password lg in login as '<Password lg>'
And I selected Next1 in login
And I wait in seconds wait in login as '<wait4>'
And I selected Place Order in shipping address edit
Then verify text Checkout login after vfy in shipping address edit
When I clicked Home in cart
And I clicked Cart Icon in cart
And I remove all items Cart in cart
Then verify text vfye empty cart in cart
When I clicked Home in cart
And I selected Home Wishlist icon in home page
And I remove all items products in home page
Then verify text empty wishlist in home page
And '<page>' is displayed with '<content>'

Examples:
|SlNo.|Search keyword st|Scroll|wait|capture date|wait1|mobile number|wait2|Email lg|wait3|Password lg|wait4|page|content|
|1|Searchkeywordst1|Scroll1|wait1|capturedate1|wait1|mobilenumber1|wait1|Emaillg1|wait1|Passwordlg1|wait1|Pantaloons Saved Items|Wishlist|


#Total No. of Test Cases : 1

