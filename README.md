# Git-Radar

### Descripción del Proyecto
Este proyecto se estructura en dos partes clave, cada una contribuyendo de manera única a la funcionalidad integral del suggester: la obtención de métricas y la tokenización de archivos de código. Ambas fases se integran en la infraestructura de AWS para proporcionar un suggester robusto y eficiente.

### Estructura de la Infraestructura AWS
#### Buckets s3
1. **S3 de Artifacts (artifacts):**
   - Almacena los artefactos esenciales del proyecto, como los módulos necesarios para el funcionamiento de las lambdas, los archivos de CloudFormation, entre otros.

2. **S3 de Códigos (code-files):**
   - Almacena los códigos fuente en formato JSON, que incluyen información crucial como el nombre y el código del archivo. Los archivos en este bucket son la materia prima para las operaciones de tokenización y obtención de métricas.

3. **S3 de Métricas (metrics):**
   - Reserva espacio para almacenar las métricas obtenidas de los archivos en el S3 de códigos. La Lambda `code-metric` se activa en respuesta a la introducción de un nuevo archivo en el S3 de códigos, generando métricas como el número de líneas y la identación máxima.

4. **S3 de Datalake (datalake):**
   - Aquí se almacenan en formato JSON los códigos tokenizados generados por la Lambda `tokenizer`. Esta lambda se activa cuando detecta una notificación de que se ha introducido un nuevo archivo en el S3 de códigos, devolviendo el código en su versión tokenizada y almacenándolo en este bucket.

#### Lambdas
1. **Lambda Tokenizer:**
   - Activada por notificaciones del S3 de códigos, la Lambda `tokenizer` toma archivos de código, los tokeniza y almacena las versiones tokenizadas en el S3 de Datalake.

2. **Lambda Code Metric:**
   - Esta Lambda se activa en respuesta a notificaciones del S3 de códigos. Toma archivos de código, calcula métricas como el número de líneas y la indentación máxima, y almacena estas métricas en el S3 de Métricas.

3. **Lambda Token-Datamart-Builder:**
   - Activada por notificaciones del S3 de Datalake, esta Lambda toma el texto tokenizado y crea n-gramas con una ventana determinada. Los n-gramas se almacenan en una base de datos DynamoDB, proporcionando así un conjunto de datos rico en información para el suggester.

#### Base de Datos DynamoDB
- Almacena en formato JSON los diferentes n-gramas formados por la Lambda `token-datamart-builder`. La estructura incluye un campo "context" que representa las palabras dentro de la ventana y un campo "next" que indica la siguiente palabra a completar.

#### APIs



[FOTO DE LAA INFRAESTRUCTURA]





### Instrucciones de uso
