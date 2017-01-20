# Pedido Venda

Para executar o projeto siga os passos: 

- 1 <strong>[Você precisará usar [git](https://git-for-windows.github.io/) neste passo] </strong> 
Clone o repositório da biblioteca: <br/>
`git clone https://github.com/codylerum/simple-email.git`

- 2 Caminhe até a pasta em que foi feito o clone: `cd simple-email`

- 3 <strong>[Você precisará ter o [maven](http://luizricardo.org/2014/06/instalando-configurando-e-usando-o-maven-para-gerenciar-suas-dependencias-e-seus-projetos-java/)  configurado corretamente no seu SO] </strong>
Instale a biblioteca no seu repositório local do maven: `mvn install -DskipTests=true`

- 4 Depois de instalado com sucesso, atualize a dependência no seu `pom.xml` para a versão `0.2.5`, 
que é a versão atual do projeto que foi clonado, ficando assim:

```
<dependency>
    <groupId>com.outjected</groupId>
    <artifactId>simple-email</artifactId>
    <version>0.2.5-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

# Autor
<h2> Lucas Barros Santos </h2>

<strong> Contato: </strong> lucas-barros28@hotmail.com / lucas.barros@facape.br <br/>+55 (87)9 9642-1975  

# Descrição

Projeto realizado com base no curso <strong> Sistemas Comerciais Java EE com CDI, JPA e PrimeFaces </strong> [AlgaWorks](http://www.algaworks.com)
 <h6> Por Thiago Faria de Andrade </h6>