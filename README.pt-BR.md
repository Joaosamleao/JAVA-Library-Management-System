# Sistema de Gerenciamento de Biblioteca (Demo Console) - Em Desenvolvimento

**🇧🇷 Português (Brasil) | [🇺🇸 English](README.md)**

Uma aplicação Java simples de console que demonstra os princípios SOLID e o padrão de design Chain of Responsibility (CoR) através de um pequeno domínio de "gerenciamento de biblioteca" em memória.

Este projeto prioriza arquitetura limpa e componentes testáveis ao invés de uma interface de usuário completa. Feito por um estudante para testar seus conhecimentos atuais em Java.

## Destaques
- Princípios SOLID nas classes de domínio e gerenciadores
- Chain of Responsibility para escalação de solicitações (Nível1 -> Nível2 -> Nível3)
- Separação clara de responsabilidades via interfaces pequenas e focadas
- Estruturas de dados em memória (sem banco de dados), fácil de executar em qualquer lugar com JDK

## Estrutura do Projeto
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

## Primeiros Passos

Pré-requisitos:
- JDK instalado e disponível no PATH (javac e java). Qualquer LTS moderno (ex: 17+) deve funcionar.

Compilar e executar (PowerShell):

```
$ErrorActionPreference = 'Stop'
if (Test-Path out) { Remove-Item -Recurse -Force out }
New-Item -ItemType Directory -Path out | Out-Null
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } | Set-Content sources.txt
javac -d out -cp src @sources.txt

# Executar o programa (ponto de entrada: Main no pacote padrão)
java -cp out Main
```

## Uso
Atualmente, o Main configura o Chain of Responsibility (Nível1 -> Nível2 -> Nível3) e instancia uma UI de placeholder. A camada interativa do console ainda está para ser implementada com todas as funcionalidades do sistema.

## Visão Geral do Design
- Chain of Responsibility
  - Interfaces/Handler define setNextHandler e handleRequest(Request)
  - Handlers/Level1Support, Level2Support, Level3Support cada um mantém um próximo handler para escalação
  - Enums/RequestType enumera a severidade das solicitações (ex: LOW, MEDIUM, HIGH)
- Modelo de domínio
  - Hierarquia de Employee com funções concretas: Clerk, Janitor, Librarian, Manager
  - Entidade Book com metadados básicos
- Gerenciadores
  - EmployeeManager, BookManager, RequestManager fornecem CRUD em memória e responsabilidades de exibição
  - Interfaces compartilhadas: ObjectManagement, ObjectDisplay, ObjectRetrieval
- Tratamento de erros
  - InvalidInputException usado para falhas de validação simples

## LISTA DE TAREFAS
- Implementar RequestManager.java
- Implementar Chain of Responsibility (CoR)
- Implementar UI/UI.java para coletar entrada e invocar gerenciadores/handlers

## Recursos em Análise
- Usar API Gemini para lidar com as solicitações e ordená-las por tipo (ex: LOW, MEDIUM, HIGH)

## Licença
Nenhum arquivo de licença presente.