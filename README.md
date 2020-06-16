## Projeto - Sistema de Controle de Empréstimo e Devolução de Livros
### Centro Paula Souza - Fatec Ferraz - Curso de Análise e Desenvolvimento de Sistemas
### Disciplina – Programação Web – Prof. Edson Saraiva de Almeida
### Grupo 6
- Gabriel Dias Ramos Lima
- Gustavo Damaceno Dainezi
- Heracles Ferreira Dias Coelho
### Objetivo
Este projeto tem como objetivo implementar uma aplicação de empréstimo e devolução de livros, para se familiarizar com a
plataforma de desenvolvimento web JEE Spring Boot.
### Processo de Desenvolvimento
O processo de desenvolvimento segue uma adaptação do Scrum. Cada interação tem a duração de 4 aulas. Ao final da interação o
código é avaliado pelo time junto com o professor na atividade de revisão da sprint. Em seguida a reunião de restrospectiva do sprint é
realizada pelo grupo e uma ata é publicada no Teams.
![idef02](https://user-images.githubusercontent.com/14267502/84417700-6a2a2580-abec-11ea-8b39-d20213a4f15d.png)
### Backlog do produto
- REQ01 – Cadastrar Livro
Como – atendente da biblioteca
Eu quero – cadastrar um livro
De maneira que – seja possível consultar e emprestar o livro
- REQ01CT01 – cadastrar com sucesso
Dado: que o atendente da biblioteca tem um livro não cadastrado
Quando: o atendente informa os dados do livro
Então: o sistema valida os dados E apresenta uma mensagem confirmando o cadastro do livro
- REQ02 - Consultar livro
- REQ03 - Alterar livro
- REQ04 - Excluir livro
- REQ05 – Cadastrar usuário
Como – atendente da biblioteca
Eu quero – cadastrar um usuário
De modo que – seja possível realizar o empréstimo de um livro.
- REQ05CT01 – Cadastro de usuário com sucesso
Dado (pré-requisito) – que o RA do usuário não está cadastrado.
Quando (descrição das ações que devem ser realizadas) – o atendente digita as informações do usuário e confirma a operação.
Então (resultado esperado) – o sistema retorna uma mensagem confirmando que o cadastro foi realizado
- REQ05CT02 – Cadastro de usuário invalido
Dado (pré-requisito) – que o RA do usuário está cadastrado.
Quando (descrição das ações que devem ser realizadas) – o atendente digita as informações do usuário e confirma a operação.
Então (resultado esperado) – o sistema retorna uma mensagem informando que o usuário já está cadastrado
### 1. Planejamento da Sprint
Durante a fase de planejamento as funcionalidades nesta interação são selecionadas do backlog do produto.
### 2. Estratégia de desenvolvimento.
Na primeira interação a meta é criar um baseline (base de sustentação) da arquitetura do sistema a fim de definir como o código será
organizado nas próximas interações. A arquitetura se desenvolve a partir de um exame dos requisitos mais significativos (aqueles que
têm grande impacto na arquitetura do sistema) e de uma avaliação de risco. A estabilidade da arquitetura é avaliada através de um ou
mais protótipos de arquitetura. O projeto do “Sistema de Controle de Empréstimo de Livros” deve se utilizar de uma arquitetura que
permita flexibilidade na configuração do sistema de persistência (mudança do sistema de gerenciamento de banco de dados) e
manutenções na interface de usuário com poucos efeitos colaterais. A arquitetura selecionada para atender esta necessidade é a
arquitetura MVC.
A estratégia de construção e integração do software será ascendente na hierarquia de controle, ou seja, da base de dados (backend) para
a interface de interação homem máquina (frontend).
### Modelo de Domínio
![modelo de dominio](https://user-images.githubusercontent.com/14267502/84425324-bd55a580-abf7-11ea-99c7-f427b80fb7cc.png) 
