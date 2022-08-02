# Rover API Visualizer
This GUI provides access to Mars Rover images and metadata using NASA API.
<br>
![build Image](https://media.discordapp.net/attachments/941490458960466063/951726669004148826/Capture.PNG?width=406&height=468)
## Installation
Note: This documentation walks through the basics of opening a JavaFX application in a development enviroment, if you do have experience with JavaFX please feel free to downlaod our files and have fun. A desktop applciation of this project is currently being developed and will be released shortly.
 - Download the ZIP of the repository, unpack, and open it in an IDE of your choice.
 ![This is an image of the file directory of the repository](https://cdn.discordapp.com/attachments/496087983779479584/954107093378539541/fileviewer.png)
 - In your IDE, create a new JavaFX project of version 11.
 ![This is an image of a new JavaFX project](https://cdn.discordapp.com/attachments/496087983779479584/954107093164626010/createproject.png)
 - When the project has been created, refactor:
   - "HelloController.java" to "Controller.java"
   - "HelloApplication.java" to "Main.java"
   - "hello-view.fxml" to "intro.fxml"
 - Afterwards, in the same folder as the new Controller and Main, create a new class called "Photo".
 ![This is an image of the path used in testing these steps](https://cdn.discordapp.com/attachments/496087983779479584/954107094095777852/inprojectfilepath.png)
 - Replace the contents of the new "Controller.java", "Main.java", "intro.fxml", and "Photo.java".
   - Copy the contents of the original "Controller.java" excluding the Package statement on line 1, then replace the contents
of the new "Controller.java" with those that you've copied, excluding the Package statement.
 ![This is an image of what's okay to replace](https://cdn.discordapp.com/attachments/496087983779479584/954107093814743060/hellocontrol_overwrite.png)
   - Copy the contents of the original "Main.java" excluding the Package statement on line 1, then replace the contents
of the new "Main.java" with those that you've copied, excluding the Package statement.
 ![This is an image of what's okay to replace](https://cdn.discordapp.com/attachments/496087983779479584/954107093579886732/helloapp_overwrite.png)
   - Replace the entirety of the new "intro.fxml" with the contents of the original "intro.fxml".
 ![This is an image of what's okay to replace](https://cdn.discordapp.com/attachments/496087983779479584/954107094389387345/intro_overwrite.png)
 - Navigate to the new "intro.fxml"s line 14 and change the path of fx:controller to fit within this project. In my
       experience, it's just changing the filename.
 - In Project Structure, add a new JAR/Directory Module and select the JSON packaged with the original classes. Enable it.
 ![This is an image of the screen in IntelliJ](https://cdn.discordapp.com/attachments/496087983779479584/954107094653624420/module_creation.png)
 - Once enabled, Build the project and navigate to the new "Controller.java". Go to the error on line 19, hover over it,
and add 'Requires JSON' directive to "module-info.java".
 - Build the program, run it, and enjoy! If you run into any issues, please create an issue on the github and we'll try to 
respond promptly!
## How To Use This Program
This tool operates by getting all photos taken on a specific date, accessible through a drop-down menu on the top-left
side of the screen.
 - Select a date using the drop-down menu to generate a list of all photos taken on that day.
 - Select an entry in the list to view the image and its relevant metadata.
   - The rover name, camera name, image id, subject, date, time in sols since launch, and mission launch date are all accessible.
 - Once an entry is selected, an image's source link can be viewed and copied or the image can be downloaded via the
"Copy" and "Download" buttons.
 
## Notes
 - Some images have been resized or adjusted due to the constraints of the UI. Images in their full resolution are available by their link, which can be copied in-program.
 - We are unable to get some images to display as their filetype is not supported by JavaFX, it seems to be tied to a switch in databases that happened during 2020.
## Credits & Sources
 - NASA and Chris Cerami, for access to their [Mars Rover Photos API](https://github.com/chrisccerami/mars-photo-api).
 - Oracle & Gluon, for their [Scene Builder](https://gluonhq.com/products/scene-builder/) which allowed us to quickly visualize the GUI.
