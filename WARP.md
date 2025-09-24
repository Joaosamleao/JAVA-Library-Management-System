# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

Project overview
- Java console application: “Library Management System - Testing”
- Emphasizes SOLID principles and a Chain of Responsibility pattern (per README)
- No build tool (Maven/Gradle) or test framework configured; plain javac/java workflow

Common commands (PowerShell)
Prerequisite: Ensure a JDK is installed and on PATH (javac/java available).

- Clean build (compile all sources into ./out)
  $ErrorActionPreference = 'Stop'
  if (Test-Path out) { Remove-Item -Recurse -Force out }
  New-Item -ItemType Directory -Path out | Out-Null
  Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } | Set-Content sources.txt
  javac -d out -cp src @sources.txt

- Run the program (Main is in the default package)
  java -cp out Main

- Optional: build with compiler warnings enabled
  javac -Xlint:all -d out -cp src @sources.txt

- Clean artifacts
  if (Test-Path out) { Remove-Item -Recurse -Force out }
  if (Test-Path sources.txt) { Remove-Item -Force sources.txt }

Tests and linting
- Tests: No test framework or test sources present.
- Lint: No Checkstyle/SpotBugs config in repo. To surface warnings, compile with -Xlint (see above).

High-level architecture
- Entry point: Main
  - Wires the Chain of Responsibility: Level1Support -> Level2Support -> Level3Support
  - UI placeholder (UI/UI.java) is referenced but currently empty.

- Chain of Responsibility (support handling)
  - Interfaces/Handler defines setNextHandler and handleRequest(Request)
  - Handlers/Level1Support, Level2Support, Level3Support each hold a nextHandler and will implement handleRequest routing/escalation
  - Request/Request is a placeholder for support request data; Enums/RequestType defines LOW, MEDIUM, HIGH severities

- Domain model
  - Employee hierarchy: abstract Employee with concrete Clerk, Janitor, Librarian, Manager; Enums/EmployeeType lists the roles
  - Book entity with basic metadata (name, author, publisher, ISBN)

- Managers (in-memory CRUD and display responsibilities)
  - Managers/EmployeeManager: manages a List<Employee>, console-based CRUD with validation and simple rules (e.g., only one Manager allowed)
    - Uses Exceptions/InvalidInputException for input validation errors
  - Managers/BookManager and Managers/RequestManager: stubs implementing the same set of responsibilities
  - Shared interfaces define responsibilities:
    - Interfaces/ObjectManagement: addObject, removeObject, editObject
    - Interfaces/ObjectDisplay: listObjects, printObject
    - Interfaces/ObjectRetrieval: getObject(String id)

- UI layer
  - UI/UI is currently empty; intended as a console interaction layer to gather input and invoke managers/handlers

Operational notes
- Persistence: No database or files used; all state is in-memory for this console application.
- Documentation: Documentation/Documentation.md exists but is currently empty; README highlights SOLID and CoR patterns.
