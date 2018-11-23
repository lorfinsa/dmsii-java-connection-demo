# SQL Server Connection demo

## Requisitos
- Maven 3+
- Java 1.8+

## Cómo correrlo
- En la carpeta raíz del proyecto, ejecutar `mvn clean install -Dmaven.test.skip=true`
- Copiar el archivo target/sqlserver-conn-demo.jar al directorio deseado
- Agregar en el mismo directorio un archivo application.properties configurando los valores de conexión a BD
- Ejecutar `java -jar target/sqlserver-conn-demo.jar "SELECT * FROM perfil.tabla" true` para medir el tiempo de ejecución. El segundo parámetro es opcional.

## Más sobre configuración de aplicaciones Spring
[Link](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)
