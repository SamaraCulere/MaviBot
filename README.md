# MaviBot - Chatbot baseado em regras

### 1. DESCRIÇÃO

A MaviBot é um chatbot baseado em regras desenvolvido em Java para a disciplina de Desenvolvimento de ChatBot, do curso de Ciência da Computação - Bacharelado, na UNIDERP.

O projeto foi desenvolvido com foco na área de cobrança e recuperação de crédito. A ideia surgiu a partir de uma situação que faz parte da minha rotina de trabalho na área de assessoria e recuperação de crédito, onde existem atendimentos relacionados a negociações de dívidas, boletos, acordos e encaminhamento para atendentes.

A proposta da MaviBot é funcionar como um primeiro atendimento ao cliente, ajudando a resolver algumas solicitações mais comuns antes que seja necessário encaminhar o atendimento para uma pessoa. Dessa forma, o chatbot pode auxiliar em dúvidas e solicitações simples e deixar para o atendente os casos que precisam de uma análise ou atendimento mais específico.

Para este trabalho foi utilizado um exemplo com a UCDB - Universidade Católica Dom Bosco como credora. Os dados utilizados no programa são simulados e foram colocados diretamente no código apenas para demonstrar o funcionamento do chatbot.

O projeto foi desenvolvido utilizando regras, palavras-chave, estruturas condicionais, laços de repetição, tratamento de erros e funções em Java.

### 2. COMO EXECUTAR

Para executar o projeto é necessário ter o Java instalado no computador.

2.1. Abra a pasta do projeto no Visual Studio Code.

2.2. Abra o arquivo `MaviBot.java`.

2.3. Execute o programa pelo próprio Visual Studio Code ou pelo terminal.

Caso seja utilizado o terminal, primeiro compile o arquivo:

```bash
javac MaviBot.java
```

Depois execute:

```bash
java MaviBot
```

2.4. Após iniciar, o chatbot apresentará uma mensagem inicial e o menu principal.

2.5. O usuário poderá escolher uma das opções disponíveis ou escrever algumas palavras relacionadas ao que deseja.

2.6. Para encerrar a conversa, pode ser utilizada a opção `5` ou palavras como `sair`, `tchau` ou `encerrar`.

### 3. FUNCIONALIDADES DO CHATBOT

A MaviBot possui algumas funções voltadas para situações comuns de atendimento na área de cobrança.

#### 3.1. Negociação de dívida

A opção de negociação permite consultar uma dívida utilizando um CPF de teste cadastrado no próprio programa.

Depois de localizar os dados, o chatbot apresenta as mensalidades, os valores originais e o total da dívida.

O usuário pode escolher entre as formas de negociação disponíveis:

* Pagamento à vista;
* Pagamento parcelado;
* Pagamento com cartão de crédito;
* Não realizar a negociação;
* Voltar para o menu.

Na opção à vista, o sistema utiliza 20% do valor da dívida como valor para pagamento, representando um desconto de 80%.

Na opção parcelada, é solicitada uma entrada de 25% e o restante pode ser dividido em até 7 parcelas.

Na opção de cartão de crédito, o valor à vista recebe um acréscimo de 12% e pode ser dividido em até 6 parcelas.

Depois da escolha, o chatbot apresenta os valores calculados e pergunta se o usuário deseja confirmar a negociação.

Caso a negociação seja confirmada, são solicitados os dados de contato para que o atendimento possa continuar.

#### 3.2. Segunda via de boleto

A segunda via permite consultar os boletos de um CPF cadastrado.

O usuário pode escolher entre consultar todas as parcelas ou informar uma ou mais datas específicas.

Quando uma data específica é informada, o chatbot verifica se existe uma parcela correspondente àquela data.

Depois da seleção, o sistema apresenta os dados das parcelas encontradas e solicita um canal de contato para o envio da segunda via.

Os canais disponíveis são WhatsApp e e-mail.

#### 3.3. Boleto em atraso

A opção de boleto em atraso permite consultar parcelas vencidas e calcular um novo valor atualizado.

O cálculo considera:

* Valor original da parcela;
* Multa de 10%;
* Juros de 1% ao mês, calculados de forma proporcional aos dias de atraso.

O usuário pode consultar todas as parcelas em atraso ou informar datas específicas.

Depois de apresentar os valores atualizados, o chatbot pergunta se o usuário deseja atualizar o boleto.

Quando a atualização é confirmada, o sistema calcula uma nova data de vencimento utilizando 7 dias a partir da data atual.

Após a atualização, o usuário pode escolher o canal de contato para receber o boleto.

#### 3.4. Atendimento com agente

Quando o usuário precisa de um atendimento que não pode ser resolvido pelas opções disponíveis, ele pode solicitar o atendimento com um agente.

Nesse fluxo, a MaviBot solicita:

* Nome;
* CPF;
* Canal de contato;
* Telefone ou e-mail;
* Motivo do atendimento.

Depois de preencher essas informações, o chatbot apresenta uma confirmação de que a solicitação será encaminhada para um atendente.

### 4. REGRAS E PALAVRAS-CHAVE

A MaviBot funciona através de regras definidas diretamente no código Java.

O chatbot não utiliza inteligência artificial ou banco de dados para interpretar as mensagens. A identificação das solicitações é feita através de palavras-chave e condições definidas no programa.

#### 4.1. Menu principal

As principais opções do menu são:

```text
1 - Negociar dívida
2 - Segunda via de boleto
3 - Boleto em atraso
4 - Falar com atendente
5 - Sair
```

Além dos números, algumas opções podem ser identificadas através de palavras relacionadas.

Para negociação, são utilizadas palavras como:

* negociar;
* dívida;
* acordo;
* negociação.

Para segunda via, são utilizadas palavras como:

* segunda via;
* segunda;
* parcela.

Para boleto em atraso, são consideradas palavras como:

* boleto;
* atrasado;
* atraso;
* vencido;
* boleto atualizado.

Para atendimento, são utilizadas palavras como:

* atendente;
* atendimento.

#### 4.2. Reconhecimento de diferentes formas de escrita

O programa utiliza a classe `Normalizer` do Java para normalizar o texto digitado pelo usuário.

Com isso, o chatbot consegue trabalhar com diferentes formas de escrita, como letras maiúsculas e minúsculas e palavras com acentos.

Por exemplo, textos como:

```text
Negociação
NEGOCIAÇÃO
negociacao
```

podem ser tratados de forma semelhante pelo programa.

Isso facilita a identificação das palavras-chave utilizadas nas regras.

#### 4.3. Regras compostas

Algumas situações utilizam mais de uma palavra para identificar a intenção do usuário.

Por exemplo, para identificar um boleto em atraso, o chatbot verifica palavras relacionadas a boleto juntamente com palavras que indicam atraso, como "atrasado", "atraso" ou "vencido".

Dessa forma, o programa consegue diferenciar melhor algumas solicitações.

#### 4.4. Comando para sair

O chatbot possui comandos para encerrar a conversa.

Entre eles estão:

* `sair`;
* `tchau`;
* `encerrar`;
* opção `5`.

Quando um desses comandos é identificado, o programa encerra o laço principal e finaliza a execução.

### 5. OUTRAS REGRAS IMPLEMENTADAS

#### 5.1. Validação de CPF

O programa solicita o CPF do usuário para localizar os dados simulados.

Os CPFs utilizados no projeto são apenas dados de teste cadastrados diretamente no código.

Quando o CPF informado não está cadastrado, o chatbot informa que não encontrou os dados e permite que o usuário tente novamente dentro do limite definido pelo programa.

#### 5.2. Validação de telefone

Quando o usuário escolhe WhatsApp como canal de contato, o chatbot solicita um número de telefone.

O programa possui uma validação para verificar se o telefone possui uma quantidade adequada de números.

#### 5.3. Validação de e-mail

Quando o usuário escolhe o e-mail como canal de contato, o chatbot solicita o endereço de e-mail.

O programa realiza uma validação simples verificando características básicas de um endereço de e-mail, como a presença de `@` e `.`.

#### 5.4. Tratamento de erros

O chatbot possui tratamento para entradas inválidas.

Quando o usuário informa uma opção que não existe, o programa apresenta uma mensagem informando que a opção não foi reconhecida.

Também existe um limite de tentativas em alguns dos fluxos. Depois de três tentativas inválidas, o chatbot encerra aquele fluxo ou retorna ao menu, dependendo da situação.

Esse tratamento foi utilizado para evitar que o programa fique preso indefinidamente em uma opção incorreta.

#### 5.5. Cálculo de datas

O programa utiliza as classes de data do Java para realizar os cálculos relacionados aos vencimentos.

Foi utilizado `LocalDate` para trabalhar com datas e `ChronoUnit` para calcular a diferença de dias entre duas datas.

Esses recursos são utilizados principalmente no cálculo dos juros de boletos atrasados.

#### 5.6. Formatação dos valores

Os valores financeiros são apresentados no formato de moeda brasileira.

Para isso, o programa utiliza formatação para apresentar os valores com duas casas decimais.

Exemplo:

```text
R$ 500,00
R$ 1.250,50
```

### 6. ESTRUTURA DO PROJETO

O projeto foi desenvolvido em Java utilizando um único arquivo principal.

A estrutura utilizada é:

```text
MaviBot
│
├── MaviBot.java
├── README.md
└── testes.pdf
```

O arquivo `MaviBot.java` contém as regras, funções, cálculos e fluxos de atendimento do chatbot.

O arquivo `README.md` apresenta as informações sobre o projeto, seu funcionamento e suas principais regras.

O arquivo `testes.pdf` contém os registros dos testes realizados durante o desenvolvimento do chatbot.

### 7. TESTES REALIZADOS

Durante o desenvolvimento foram realizados testes para verificar os principais fluxos do programa.

Foram testadas as opções do menu principal, incluindo negociação de dívida, segunda via de boleto, boleto em atraso, atendimento com agente e encerramento do chatbot.

Também foram realizados testes com entradas válidas e inválidas para verificar o tratamento de erros.

Na negociação foram testadas as opções de pagamento à vista, parcelamento e cartão de crédito.

Na segunda via foram testadas consultas de todas as parcelas e consultas de datas específicas.

No boleto em atraso foram testados os cálculos de multa, juros e atualização da data de vencimento.

Também foram testados os dados de contato, incluindo telefone e e-mail.

Os testes foram importantes principalmente para verificar se as regras estavam funcionando corretamente e se o chatbot retornava ao fluxo esperado depois de uma entrada inválida.

### 8. LIMITAÇÕES DO PROJETO

Por ser um trabalho acadêmico baseado em regras, a MaviBot possui algumas limitações.

Os dados utilizados no projeto são simulados e estão cadastrados diretamente no código. Em uma aplicação real, essas informações deveriam ser armazenadas em um banco de dados e consultadas de acordo com o cliente.

O chatbot também não possui integração com sistemas de cobrança, sistemas de emissão de boletos, WhatsApp ou e-mail. No projeto, esses processos são apenas simulados para demonstrar como seria o fluxo de atendimento.

Outra limitação é que o reconhecimento das mensagens depende das palavras-chave que foram programadas. Dessa forma, uma frase que não contenha uma palavra prevista nas regras pode não ser reconhecida corretamente.

Em uma aplicação real, também seria necessário implementar mecanismos mais completos de segurança, controle de acesso e proteção dos dados dos clientes.

Essas limitações fazem parte da proposta do trabalho, que tem como objetivo demonstrar a construção de um chatbot baseado em regras utilizando Java.

### 9. COMENTÁRIO PESSOAL

Escolhi desenvolver o chatbot na área de cobrança porque é uma área que faz parte da minha rotina de trabalho. No meu trabalho, existem situações em que o cliente precisa apenas de uma segunda via, atualização de um boleto, consulta de uma informação ou orientação sobre uma negociação. Muitas vezes são solicitações que não precisam necessariamente de um atendimento mais longo com um agente.

A partir disso, pensei em utilizar o chatbot como uma primeira etapa do atendimento. A ideia seria que ele pudesse identificar o que o cliente precisa e resolver algumas solicitações mais simples antes de encaminhar o atendimento para uma pessoa.

Durante o desenvolvimento, percebi que mesmo um chatbot baseado apenas em regras precisa ter vários caminhos diferentes para conseguir tratar as situações que podem acontecer durante uma conversa. Foi necessário pensar nas palavras que o usuário poderia digitar, nas opções inválidas, nas tentativas de erro e também no momento em que o usuário poderia voltar ou encerrar o atendimento.

Outra parte que exigiu atenção foram os cálculos relacionados aos boletos em atraso e às negociações, principalmente por envolver valores e datas diferentes. Os dados utilizados no projeto são simulados, mas procurei deixar os cálculos e os fluxos próximos de uma situação de atendimento.

O projeto também me ajudou a entender melhor como as regras e os fluxos de um chatbot podem ser organizados dentro de um programa Java. Apesar de ser uma implementação simples, foi possível perceber que, para transformar a ideia em um sistema realmente utilizado em uma empresa, seria necessário integrar o chatbot com banco de dados, sistemas de cobrança, emissão de boletos e canais de atendimento.

De forma geral, o desenvolvimento da MaviBot foi uma forma de relacionar o conteúdo da disciplina com uma situação que conheço na prática. Além de trabalhar com Java e regras de chatbot, o projeto ajudou a pensar em como uma automação poderia ser utilizada para facilitar algumas etapas do atendimento de cobrança.