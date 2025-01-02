# Compilador Java + AntLR

Este repositório contém o projeto final da disciplina de Compiladores (2023/1). O projeto consiste na implementação de um compilador que transpila códigos escritos na linguagem fictícia GYH, desenvolvida pela professora Tamara Angélica Baldo e baseada na linguagem ALGUMA, proposta pelo professor Daniel Lucrédio (UFSCar), para sua correspondente em linguagem C.

## Estrutura do repositório

O repositório apresenta a seguinte estrutura:
- **Analisador Léxico+Sintático:** 
    
    Diretório contendo o código desenvolvido em Java na IDE NetBeans, implementando do zero o analisador léxico e sintático do compilador.

- **Compilador Gyh:** 

    Diretório com o código desenvolvido em Java na IDE NetBeans, contendo a lógica completa do compilador. Esta versão inclui análise léxica, sintática e semântica, utilizando o gerador [ANTLR](https://www.antlr.org/)
- **Simplificação da Gramática - GYH.pdf:** 

    Documento que apresenta as regras da linguagem GYH após um processo de simplificação. Este inclui a remoção de recursões à esquerda e ajustes para facilitar a implementação do compilador.

## Autores
- Gabriel Finger Conte
- [João Vitor Garcia Carvalho](https://github.com/JoViGaCa)

## Licença

Este projeto é licenciado sob a Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
