# CodeClan Capstone Project - Pickup

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about">About</a>
      <ul>
        <li><a href="#design">Design</a></li>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#reflections">Reflections</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT -->
### Pickup

Pickup is a web application built with React and Java (Spring) for the capstone project of the CodeClan software professional development 16 week course. It allows communities to organise litter picking events and allow the users to track the litter collected in a leaderboard format. It was created in February/March 2023 by Kelsie Murphy, Kyle Fenlon and Josh Montgomery.

You can view a live version of Pickup at https://www.joinpickup.co/

<!-- BRIEF -->
### MVP

#### Main Web Application
* It should allow community admins to signup, arrange events, and manage users
* It should allow users to join a community, and there should also be private communities
* Events have a location and time and before image, and are connected to a community.
* Admins can add before/after pics, update the details on the event before and after the event takes place, etc
* It should have a leaderboard to encourage healthy competition

#### Mobile App
* Users should have an app that allows them to add litter picked by name via 5 common categories and an ‘other’ category, with a quantity selector below.
* Users can choose a category, go around collecting that type, and add the quantity to the app, all saved in state until submission.

### Possible Extensions

* Authentication
* It should allow users to report litter hotspots, with a picture and a map, with Poll functionality
* Visualisation of data, ie monthly pickings


<!-- DESIGN -->
## Design
The Figma file was designed during the planning day of the project, and [can be viewed here](https://www.figma.com/file/2qpN5pvmRssTDmujRWAQuP/Litter-Picking-App?node-id=1%3A2&t=enuLPof7ZVViV8Sv-1).

<!-- BUILT WITH -->
## Built With

* React
* TailwindCSS
* Java 8.0
* Spring

<!-- GETTING STARTED -->
## Getting Started
### Prerequisites

#### Client

To run this app, you need to set up the client and server sides of the app: 
* Install the dependencies in the 'client' directory
  ```sh
  pnpm install
  ```

* Then run the client web application in development
  ```sh
  npm run dev
  ```

Note: To stop the server enter ctrl + c in your Terminal

#### Server

* Create a database called 'pickup' in your Terminal
```sh
  createdb pickup
  ```

* We recommend opening the server directory in IntelliJ IDEA, and run the ServerApplication.java file. Ensure that the resources/application.properties file is set up with the spring profile set to "dev-data-load", and the ddl-auto file is set to "create". After initially setting up the database, these values can be returned to the defaults to save reloading the data everytime you restart the server.


<!-- CONTACT -->
## Contact

* Kelsie Murphy - [GitHub](https://github.com/kelsiesmurphy)
* Kyle Fenlon - [GitHub](https://github.com/kylefenlon)
* Josh Montgomery - [GitHub](https://github.com/jomonty)

Project Link: [https://github.com/kelsiesmurphy/pickup](https://github.com/kelsiesmurphy/pickup)
