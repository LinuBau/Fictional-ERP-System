# ERP System for a Music Distribution Company

## This Project

This project was developed as part of a university course (Programming 3, Group 4).  
It is a fictional ERP (Enterprise Resource Planning) system for a company that sells music in both physical and digital formats.

## Features

- Management of customer data, employees, invoices, payments, and music catalog
- Role-based login system:
  - **Employee Login:**  
    Username: `mitarbeiter`  
    Password: `mitarbeiter`
  - **User Login:**  
    Username: `whateveryouwant`  
    Password: `whateveryouwant`
- Java Swing-based graphical user interface
- Load song data from a CSV file
- Able to change languages at runtime
- Change catalog to see the development of data
- Create invoices linked to customers and songs
- Export invoices as TXT files

## Getting Started

1. Run the `.jar` file.
2. If the song list is empty upon startup:
   - Log in as an employee.
   - In the menu bar, go to **File → Open**.
   - Select the `Songs.csv` file from the project folder `Programmieren 3 Gruppe 4`.

## Technologies Used
- Java
- Java Swing

## Project Structure 

- `src/ActionListner`: Core ERP logic
- `src/gui`: JavaSwing-based user interface
- `Doku.txt`: Project documentation
- `P3G4.vpp`:  UML diagrams

## Documentation

Detailed documentation including use case descriptions, UML diagrams, GUI screenshots, and technical details is available in the file  
`Doku P3.txt` (included in the repository).  
**Note:** All documentation is written in **German**.

## Disclaimer

This project may contain an unknown number of bugs. These issues are known but will not be fixed, as the project was completed solely for academic purposes.
