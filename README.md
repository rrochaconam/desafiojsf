
# desafiojsf

O objetivo dessa aplicação é servir de base para estudo e avaliação de desenvolvimento de soluções para web em java.

### Tecnologias utilizadas na implementação:

 - **JSF**: utilizamos o framework JavaServer Faces, seguindo o modelo arquitetural MVC e o uso de componentes visuais para a construção das interfaces gráficas (front-end);
 - **PRIMEFACES**: biblioteca de componentes de interface de usuário para aplicativos web baseados em Java Server Faces (JS
 - **JPA**: API alto nível, padrão da tecnologia Java, para definir o mapeamento objeto relacional (ORM);
 - **Hibernate**: provedor JPA, responsável por resolver ORM;

###  Pré-requisitos:

 - JDK - versão 1.8 do Java;
 - Eclipse - recomendamos o Eclipse IDE;
 - Maven - para build e dependências.
 - Application Server - Tomcat 8/8.5.
 - Postgres 12 ou mais recente.

###  Banco de dados
 - **Script de setup do banco de dados:** \src\main\resources\db\script.sql
 - **Configuração o pool de conexão:** você precisará adicionar algumas linhas ao arquivo de configuração "context.xml" do seu aplicativo. 

    	<Resource name="jdbc/conamDS" auth="Container" type="javax.sql.DataSource"
           maxActive="100" maxIdle="30" maxWait="10000" maxWaitMillis="-1"
           username="postgres" password="postgres" driverClassName="org.postgresql.Driver"
           url="jdbc:postgresql://localhost:5432/desafio"/>
#### Configuração do tomcat para acesso ao postgres
 - Baixe o driver JDBC do PostgreSQL: você pode baixá-lo diretamente do site oficial do PostgreSQL - https://jdbc.postgresql.org/download/postgresql-42.6.0.jar
 - Coloque o arquivo do driver JDBC no diretório "lib" do Tomcat: o diretório "lib" geralmente está localizado na pasta "lib" dentro do diretório de instalação do Tomcat.

###  Página inicial da aplicação
http://localhost:8080/desafiojsf/pages/hello.xhtml
