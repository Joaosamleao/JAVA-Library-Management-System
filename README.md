# Library Management System (Console Demo) - TBC

**[🇧🇷 Português (Brasil)](README.pt-BR.md) | 🇺🇸 English**

A simple Java console application that showcases SOLID principles and the Chain of Responsibility (CoR) design pattern through a small, in-memory "library management" domain.

This project prioritizes clean architecture and testable components over a full user interface. Made by a student to test his current knowledge in Java.

## Highlights
- SOLID principles in the domain and manager classes
- Chain of Responsibility for request escalation (Level1 -> Level2 -> Level3)
- Clear separation of concerns via small, focused interfaces
- In-memory data structures (no database), easy to run anywhere with a JDK

## Project Structure
```
management-test/
├─ src/
│  ├─ Main.java
│  ├─ Book/
│  │  └─ Book.java
│  ├─ Employee/
│  │  ├─ Employee.java
│  │  ├─ Clerk.java
│  │  ├─ Janitor.java
│  │  ├─ Librarian.java
│  │  └─ Manager.java
│  ├─ Enums/
│  │  ├─ EmployeeType.java
│  │  └─ RequestType.java
│  ├─ Exceptions/
│  │  └─ InvalidInputException.java
│  ├─ Handlers/
│  │  ├─ Level1Support.java
│  │  ├─ Level2Support.java
│  │  └─ Level3Support.java
│  ├─ Interfaces/
│  │  ├─ Handler.java
│  │  ├─ ObjectDisplay.java
│  │  ├─ ObjectManagement.java
│  │  └─ ObjectRetrieval.java
│  ├─ Managers/
│  │  ├─ BookManager.java
│  │  ├─ EmployeeManager.java
│  │  └─ RequestManager.java
│  ├─ Request/
│  │  └─ Request.java
│  └─ UI/
│     └─ UI.java
└─ Documentation/
   └─ Documentation.md (placeholder)
```

## Getting Started

Prerequisites:
- JDK installed and available on PATH (javac and java). Any modern LTS (e.g., 17+) should work.

Build and run (PowerShell):

```
$ErrorActionPreference = 'Stop'
if (Test-Path out) { Remove-Item -Recurse -Force out }
New-Item -ItemType Directory -Path out | Out-Null
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } | Set-Content sources.txt
javac -d out -cp src @sources.txt

# Run the program (entry point: Main in default package)
java -cp out Main
```
```
## Usage
Currently, Main wires up the Chain of Responsibility (Level1 -> Level2 -> Level3) and instantiates a placeholder UI. The interactive console layer is yet to be implemented with all the functionalities of the system.

## Design Overview
- Chain of Responsibility
  - Interfaces/Handler defines setNextHandler and handleRequest(Request)
  - Handlers/Level1Support, Level2Support, Level3Support each hold a next handler for escalation
  - Enums/RequestType enumerates request severity (e.g., LOW, MEDIUM, HIGH)
- Domain model
  - Employee hierarchy with concrete roles: Clerk, Janitor, Librarian, Manager
  - Book entity with basic metadata
- Managers
  - EmployeeManager, BookManager, RequestManager provide in-memory CRUD and display responsibilities
  - Shared interfaces: ObjectManagement, ObjectDisplay, ObjectRetrieval
- Error handling
  - InvalidInputException used for simple validation failures

## TO-DO
- Implement RequestManager.java
- Implement Chain of Responsibility (CoF)
- Implement UI/UI.java to collect input and invoke managers/handlers

## Features In Review
- Using Gemini API to handle the requests and order them by type (e.g., LOW, MEDIUM, HIGH)

## License
No license file is present.
