************************************   READ ME  ************************************** 

--------------------------------------------------------------------------------------
1. suiteVimeo
--------------------------------------------------------------------------------------

The test execution will recreate the actions play and stop video.

Vimeo video will be reproduced untill the value determined for the element slider.

Expected result:
BaseTest:28 - Browser Opened
BasePage:32 - Vimeo iframe List Option clicked
Listener:47 - STARTING TEST
BasePage:109 - Switch to iFrame
BasePage:49 - Playing Vimeo video
BasePage:58 - Vimeo video stopped
BasePage:128 - Video has reproduced until 3 seconds.
Listener:53 - THE TEST PASSED
BasePage:49 - Outside Vimeo iFrame
BaseTest:35 - Browser closed
Listener:18 - TEST FINISHED

----------------------------------------------------------------------------------------
2. suiteYoutube
----------------------------------------------------------------------------------------

The test execution will recreate the actions play and stop video.

Youtube video will be reproduced untill the element slider turn to an invisible. This
approach allowed me not use an thread sleep.

Expected result:
BaseTest:28 - Browser Opened
BasePage:58 - YouTube iframe List Option clicked
Listener:47 - STARTING TEST
BasePage:109 - Switch to iFrame
BasePage:48 - Playing YouTube video
BasePage:58 - YouTube video stopped
BasePage:128 - Video has reproduced until 3 seconds.
Listener:53 - THE TEST PASSED
BasePage:75 - Outside Youtube iFrame
BaseTest:35 - Browser closed
Listener:18 - TEST FINISHED