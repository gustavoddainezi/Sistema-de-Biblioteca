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
  - Como – Funcionário da biblioteca  
  - Eu quero – cadastrar um livro  
  - De maneira que – seja possível consultar e emprestar o livro
- REQ01CT01 – Cadastro de livro com sucesso
  - Dado: que o funcionário da biblioteca tem um livro não cadastrado
  - Quando: o funcionário informa os dados do livro
  - Então: o sistema valida os dados E apresenta uma mensagem confirmando o cadastro do livro
- REQ01CT02 – Cadastro de livro com ISBN já cadastrado
  - Dado que: o ISBN do livro já esteja cadastrado.
  - Quando: o usuário digita as informações obrigatórias "ISBN", "Título", "Autor" e confirma operação.
  - Então: o sistema valida os dados. E uma mensagem informando que o livro com o ISBN informado já está cadastrado.  
- REQ02 - Consultar livro
  - Como - Funcionário da biblioteca
  - Quero - Consultar Livro
  - De maneira que - Seja possível realizar a visualização um livro da base de dados
- REQ02CT01 - Consulta de livro com sucesso
  - Desde que: o livro esteja cadastrado.
  - Quando: o usuário digita uma informação para encontra-lo, podendo ser "ISBN", "Título" ou "Autor" e confirma operação.
  - Então: o sistema valida os dados. E mostra na tela os livros respectivos à consulta do usuário.
- REQ02CT02 - Cenário 2: Consulta de livro inexistente
  - Dado que: o livro não esteja cadastrado.
  - Quando: o usuário digita uma informação  para encontra-lo, podendo ser "ISBN", "Título" ou "Autor" e confirma operação.
  - Então: o sistema valida os dados. E uma mensagem informando que o livro consultado não está cadastrado é mostrada.
- REQ02CT03 - Consulta de livro emprestado
  - Dado que: o livro já tenha sido emprestado
  - Quando: o usuário digita uma informação para encontra-lo, podendo ser "ISBN", "Título" ou "Autor" e confirma operação.
  - Então: o sistema valida os dados. E uma mensagem informando que o livro consultado já está emprestado é mostrada.  
- REQ03 - Alterar livro
  - Como - Funcionário da biblioteca
  - Quero - Alterar Livro
  - De maneira que - Seja possível realizar a alteração de um livro da base de dados
- REQ03CT01 - Alteração de livro com sucesso
  - Desde que: o livro esteja cadastrado
  - Quando: o usuário digita uma informação para alterar o dado na base de dados, podendo ser "ISBN", "Título" ou "Autor" e confirma operação.
  - Então: o sistema valida os dados. E mostra na tela uma mensagem informando que a alteração foi realizada.  
- REQ04 - Excluir livro
  - Como - Funcionário da biblioteca
  - Quero - Excluir Livro
  - De maneira que - Seja possível realizar a exclusão de um livro da base de dados
- REQ04CT01 - Exclusão de livro com sucesso
  - Desde que: o livro esteja cadastrado
  - Quando: o usuário seleciona o livro desejado e confirma sua exclusão.
  - Então: o sistema valida os dados. Pergunta se o usuário realmente deseja excluir, espera a resposta do usuário. Caso a resposta for sim mostra na tela uma mensagem informando que a exclusão foi realizada, caso a resposta for não volta para tela anterior.
- REQ04CT02 -  Exclusão de livro emprestado
  - Dado que: o livro já tenha exemplares emprestados
  - Quando: o usuário digita uma informação para encontra-lo, podendo ser "ISBN", "Título" ou "Autor" e confirma operação.
  - Então: o sistema valida os dados. Pergunta se o usuário realmente deseja excluir, espera a resposta do usuário. Caso a resposta for sim mostra na tela uma mensagem informando que a exclusão não foi realizada porque há exemplares emprestados, caso a resposta for não volta para tela anterior.  
- REQ05 – Cadastrar usuário
  - Como – atendente da biblioteca
  - Eu quero – cadastrar um usuário
  - De modo que – seja possível realizar o empréstimo de um livro.
- REQ05CT01 – Cadastro de usuário com sucesso
  - Dado (pré-requisito) – que o RA do usuário não está cadastrado.
  - Quando (descrição das ações que devem ser realizadas) – o atendente digita as informações do usuário e confirma a operação.
  - Então (resultado esperado) – o sistema retorna uma mensagem confirmando que o cadastro foi realizado
- REQ05CT02 – Cadastro de usuário invalido
  - Dado (pré-requisito) – que o RA do usuário está cadastrado.
  - Quando (descrição das ações que devem ser realizadas) – o atendente digita as informações do usuário e confirma a operação.
  - Então (resultado esperado) – o sistema retorna uma mensagem informando que o usuário já está cadastrado
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
